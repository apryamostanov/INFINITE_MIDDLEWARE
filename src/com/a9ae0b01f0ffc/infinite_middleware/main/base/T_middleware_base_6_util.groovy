package base

import Interfaces.I_http_message
import annotations.I_black_box
import annotations.I_fix_variable_scopes
import com.amazonaws.AmazonWebServiceResponse
import com.amazonaws.ClientConfiguration
import com.amazonaws.DefaultRequest
import com.amazonaws.Request
import com.amazonaws.Response
import com.amazonaws.auth.AWS4Signer
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.http.AmazonHttpClient
import com.amazonaws.http.ExecutionContext
import com.amazonaws.http.HttpMethodName
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import groovy.text.Template
import groovy.time.TimeCategory
import groovy.util.slurpersupport.GPathResult
import groovy.util.slurpersupport.NodeChild
import groovy.xml.XmlUtil
import implementation.T_aws_response_handler
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.apache.tools.ant.filters.StringInputStream
import org.json.JSONObject
import org.json.XML
import other.E_application_exception
import other.T_logger

import javax.servlet.http.HttpServletRequest
import javax.xml.namespace.QName
import javax.xml.stream.XMLInputFactory
import javax.xml.stream.XMLStreamConstants
import javax.xml.stream.XMLStreamReader

@I_fix_variable_scopes
class T_middleware_base_6_util extends T_middleware_base_5_app_context {

    String GC_SOAP_USER = app_conf().GC_SOAP_USER
    String GC_SOAP_PASSWORD = app_conf().GC_SOAP_PASSWORD
    String GC_SOAP_IP = app_conf().GC_SOAP_IP
    String GC_SOAP_SOURCE = app_conf().GC_SOAP_SOURCE
    String GC_SOAP_API_VERSION = app_conf().GC_SOAP_API_VERSION

    //@I_black_box("error")
    static String get_service_name(String i_uri) {
        String LC_RELATIVE_PATH = "middleware/Rest/"
        Integer l_start_index = i_uri.lastIndexOf(LC_RELATIVE_PATH) + LC_RELATIVE_PATH.length()
        Integer l_end_index
        if (i_uri.indexOf("/", l_start_index + 1) > 0) {
            l_end_index = i_uri.indexOf("/", l_start_index + 1)
        } else {
            if (i_uri.indexOf("?", l_start_index + 1) > 0) {
                l_end_index = i_uri.indexOf("?", l_start_index + 1)
            } else {
                l_end_index = i_uri.length()
            }
        }
        return i_uri.substring(l_start_index, l_end_index)
    }

    @I_black_box("error")
    static String unbox_soap_xml(String i_initial_xml) {
        return XmlUtil.serialize((GPathResult) (new XmlSlurper().parseText(i_initial_xml).Body.children().first()))
    }

    @I_black_box("error")
    static String json_object2xml_string(Object i_content) {
        if (is_null(i_content)) {
            return GC_EMPTY_STRING
        } else {
            JsonBuilder l_json_builder = new JsonBuilder()
            l_json_builder.content = i_content
            return XML.toString(new JSONObject(l_json_builder.toString()))
        }
    }

    @I_black_box("error")
    static String xml_array_field(Object i_element, String i_value, String i_namespace) {
        JsonBuilder l_json_builder = new JsonBuilder()
        l_json_builder.content = i_element
        String l_xml_string = XML.toString(new JSONObject(l_json_builder.toString()))
        GPathResult l_xml_gpath_result = new XmlSlurper().parseText("<content>" + l_xml_string + "</content>")
        ArrayList l_value = l_xml_gpath_result.depthFirst().findAll { l_element -> l_element.name() == i_value }
        if (l_value.size() == GC_EMPTY_SIZE) {
            return xml_tag(GC_NULL_OBJ_REF as String, i_value, i_namespace)
        } else {
            return xml_tag(l_value.first() as String, i_value, i_namespace)
        }
    }

    @I_black_box("error")
    static String xml_array(List i_elements, String i_element_template_name) {
        String l_result = GC_EMPTY_STRING
        Template l_element_template = get_template_manager().get_xml_template(i_element_template_name)
        for (l_element in i_elements) {
            l_result += l_element_template.make(["utils": this, "array_element": l_element]).toString()
        }
        l().log_trace(l_result)
        return l_result
    }

    @I_black_box
    static void aws_request_with_v4_sign(I_http_message i_request, I_http_message i_response) {
        Request<Void> l_aws_request = new DefaultRequest<Void>(app_conf().GC_AWS_SERVICE_NAME)
        l_aws_request.setHttpMethod(HttpMethodName.valueOf(i_request.get_method()))
        l_aws_request.setEndpoint(URI.create(i_request.get_uri()))
        l_aws_request.setContent(new com.amazonaws.util.StringInputStream(i_request.get_payload()))
        AWS4Signer signer = new AWS4Signer()
        signer.setRegionName(app_conf().GC_AWS_REGION)
        signer.setServiceName(l_aws_request.getServiceName())
        if (is_null(app_conf().GC_AWS_ACCESS_KEY) || is_null(app_conf().GC_AWS_SECRET_KEY)) {
            l().log_warning(s.One_of_the_AWS_keys_is_null)
        }
        if (is_null(app_conf().GC_AWS_SERVICE_NAME)) {
            l().log_warning(s.AWS_service_name_is_null)
        }
        signer.sign(l_aws_request, new BasicAWSCredentials(app_conf().GC_AWS_ACCESS_KEY, app_conf().GC_AWS_SECRET_KEY))
        Response<AmazonWebServiceResponse<String>> l_aws_response = new AmazonHttpClient(new ClientConfiguration())
                .requestExecutionBuilder()
                .executionContext(new ExecutionContext(true))
                .request(l_aws_request)
                .execute(new T_aws_response_handler())
        l().log_send_http(l_aws_request, i_request.get_payload())
        if (is_not_null(l_aws_response.getAwsResponse()?.getResult())) {
            i_response.set_payload_type(GC_PAYLOAD_TYPE_XML)
            i_response.set_payload(l_aws_response.getAwsResponse().getResult())
        }
        for (l_header_name in l_aws_response.httpResponse.headers.keySet()) {
            i_response.set_header(l_header_name, l_aws_response.httpResponse.headers.get(l_header_name))
        }
        i_response.set_status(l_aws_response.httpResponse.statusCode)
    }

    @I_black_box("error")
    static String format_xml(String i_xml) {
        return XmlUtil.serialize(i_xml)
    }

    @I_black_box("error")
    static String find_unique_id_json(String i_payload_json) {
        try {
            String l_xml_string = XML.toString(new JSONObject(i_payload_json))
            GPathResult l_xml_gpath_result = new XmlSlurper().parseText(l_xml_string)
            ArrayList l_unique_id_result_array_list = l_xml_gpath_result.depthFirst().findAll { l_element -> l_element.name() == GC_PARAMETER_NAME_UNIQUE_ID }
            if (l_unique_id_result_array_list.size() == GC_EMPTY_SIZE) {
                return GC_UNKNOWN_UNIQUE_ID
            } else {
                return l_unique_id_result_array_list.first()
            }
        } catch (Throwable e_others) {
            l().log_warning(s.Exception_finding_unique_id_Z1, e_others)
            return GC_UNKNOWN_UNIQUE_ID
        }
    }

    @I_black_box("error")
    static T_logger l() {
        return T_logging_base_5_context.l()
    }

    @I_black_box("error")
    static String strip_namespaces_from_xml(String i_xml_string) {
        if (is_null(i_xml_string)) {
            return GC_EMPTY_STRING
        }
        String l_simplified_xml_string = GC_EMPTY_STRING
        XMLInputFactory l_xml_input_factory = XMLInputFactory.newInstance()
        l_xml_input_factory.setProperty(l_xml_input_factory.IS_COALESCING, GC_TRUE)
        XMLStreamReader l_xml_stream_reader = l_xml_input_factory.createXMLStreamReader(new StringInputStream(i_xml_string))
        while (l_xml_stream_reader.hasNext()) {
            Integer l_next_parsing_event = l_xml_stream_reader.next()
            if (l_next_parsing_event == XMLStreamConstants.START_ELEMENT) {
                l_simplified_xml_string += "<" + l_xml_stream_reader.getName().getLocalPart()
                for (l_attr_index in GC_ZERO..l_xml_stream_reader.getAttributeCount()) {
                    QName l_q_name = l_xml_stream_reader.getAttributeName(l_attr_index)
                    if (l_q_name != GC_NULL_OBJ_REF) {
                        l_simplified_xml_string += " " + l_xml_stream_reader.getAttributeName(l_attr_index).getLocalPart() + "=\"" + l_xml_stream_reader.getAttributeValue(l_attr_index) + "\""
                    }
                }
                l_simplified_xml_string += ">"
            } else if (l_next_parsing_event == XMLStreamConstants.CHARACTERS) {
                l_simplified_xml_string += XmlUtil.escapeXml(l_xml_stream_reader.getText())
            } else if (l_next_parsing_event == XMLStreamConstants.END_ELEMENT) {
                l_simplified_xml_string += "</" + l_xml_stream_reader.getName().getLocalPart() + ">"
            }
        }
        return l_simplified_xml_string
    }

    @I_black_box("error")
    static HashMap<String, String> file2hashmap(String i_file_name) {
        HashMap<String, String> l_hash_map = new HashMap<String, String>()
        BufferedReader l_buffered_reader = new BufferedReader(new FileReader(i_file_name))
        String l_line
        while ((l_line = l_buffered_reader.readLine()) != GC_NULL_OBJ_REF) {
            String[] l_splitted_string_array = l_line.split(":")
            l_hash_map.put(l_splitted_string_array[GC_FIRST_INDEX], l_splitted_string_array[GC_SECOND_INDEX])
        }
        l_buffered_reader.close()
        return l_hash_map
    }

    @I_black_box("error")
    static Boolean validate_xml(String i_xml, Boolean i_is_soft = GC_FALSE) {
        if (is_null(i_xml)) {
            return GC_TRUE
        }
        try {
            String l_formatted_payload = format_xml(i_xml)
            return GC_TRUE
        } catch (Exception e_others2) {
            if (i_is_soft) {
                l().log_warning(s.Potentially_invalid_XML, i_xml, e_others2)
                return GC_FALSE
            } else {
                throw new E_application_exception(s.Potentially_invalid_XML, i_xml, e_others2)
            }
        }
    }

    @I_black_box("error")
    static Boolean validate_json(String i_json, Boolean i_is_soft = GC_FALSE) {
        if (is_null(i_json)) {
            return GC_TRUE
        }
        try {
            JsonSlurper l_json_slurper = new JsonSlurper()
            l_json_slurper.parseText(i_json)
            return GC_TRUE
        } catch (Exception e_others2) {
            if (i_is_soft) {
                l().log_warning(s.Potentially_invalid_JSON, i_json, e_others2)
                return GC_FALSE
            } else {
                throw new E_application_exception(s.Potentially_invalid_JSON, i_json, e_others2)
            }
        }
    }


    @I_black_box("error")
    static ArrayList<NodeChild> find_element(GPathResult i_gpath_result, String i_element_name) {
        return i_gpath_result.depthFirst().findAll { l_element -> l_element.name() == i_element_name }
    }

    @I_black_box("error")
    static String find_value(GPathResult i_gpath_result, String i_element_name) {
        ArrayList<NodeChild> l_node_children = find_element(i_gpath_result, i_element_name)
        if (l_node_children.isEmpty()) {
            return GC_EMPTY_STRING
        } else {
            GPathResult l_gpath_result = l_node_children.first()
            return l_gpath_result.text()
        }
    }

    @I_black_box("error")
    static String xml_tag(String i_value, String i_element_name, String i_name_space = GC_EMPTY_STRING, String i_tag_name = GC_EMPTY_STRING) {
        String l_element_name = nvl(i_tag_name, i_element_name)
        String l_prefix = GC_EMPTY_STRING
        if (i_name_space != GC_EMPTY_STRING) {
            l_prefix += i_name_space + ":"
        }
        if (is_null(i_value)) {
            return l_prefix + l_element_name.substring(l_element_name.lastIndexOf(".") + 1) + " i:nil=\"true\"/"
        } else {
            return l_prefix + l_element_name.substring(l_element_name.lastIndexOf(".") + 1) + ">" + i_value + "</" + l_prefix + l_element_name.substring(l_element_name.lastIndexOf(".") + 1)
        }
    }

    @I_black_box("error")
    static String xml_tag(GPathResult i_gpath_result, String i_element_name, String i_name_space = GC_EMPTY_STRING, String i_tag_name = GC_EMPTY_STRING) {
        String l_element_name = nvl(i_tag_name, i_element_name)
        ArrayList<NodeChild> l_node_children = find_element(i_gpath_result, i_element_name)
        if (l_node_children.isEmpty()) {
            l_node_children = find_element(i_gpath_result, i_tag_name)
        }
        String l_prefix = GC_EMPTY_STRING
        if (i_name_space != GC_EMPTY_STRING) {
            l_prefix += i_name_space + ":"
        }
        if (l_node_children.isEmpty()) {
            return l_prefix + l_element_name.substring(l_element_name.lastIndexOf(".") + 1) + " i:nil=\"true\"/"
        } else {
            GPathResult l_gpath_result = l_node_children.first()
            if (l_gpath_result.text() != GC_EMPTY_STRING) {
                return l_prefix + l_element_name.substring(l_element_name.lastIndexOf(".") + 1) + ">" + l_gpath_result.text() + "</" + l_prefix + l_element_name.substring(l_element_name.lastIndexOf(".") + 1)
            } else {
                return l_prefix + l_element_name.substring(l_element_name.lastIndexOf(".") + 1) + " i:nil=\"true\"/"
            }
        }
    }

    @I_black_box("error")
    static String xml_tag(HashMap<String, String[]> i_parameter_map, String i_element_name, String i_name_space = GC_EMPTY_STRING, String i_tag_name = GC_EMPTY_STRING) {
        String l_element_name = nvl(i_tag_name, i_element_name)
        String l_parameter_value = nvl(i_parameter_map.get(i_element_name)?.getAt(GC_FIRST_INDEX), GC_EMPTY_STRING)
        String l_prefix = GC_EMPTY_STRING
        if (i_name_space != GC_EMPTY_STRING) {
            l_prefix += i_name_space + ":"
        }
        if (is_not_null(l_parameter_value)) {
            return l_prefix + l_element_name.substring(l_element_name.lastIndexOf(".") + 1) + ">" + l_parameter_value + "</" + l_prefix + l_element_name.substring(l_element_name.lastIndexOf(".") + 1)
        } else {
            return l_prefix + l_element_name.substring(l_element_name.lastIndexOf(".") + 1) + " i:nil=\"true\"/"
        }
    }

    @I_black_box("error")
    static String generate_unique_id() {
        return "MDWL" + new Date().format("yymmddHHmmssSSS")
    }

    @I_black_box("error")
    static String remove_jwt_bearer(String i_jwt) {
        if (i_jwt.indexOf("Bearer") != GC_CHAR_INDEX_NOT_EXISTING) {
            return i_jwt.substring(i_jwt.indexOf(GC_SPACE) + GC_ONE_CHAR)
        } else {
            return i_jwt
        }
    }

    @I_black_box
//tracing JWT
    static Integer validate_jwt(String i_jwt) {
        try {
            Jwts.parser().setSigningKey(get_jwt_manager().get_jwt_key()).parseClaimsJws(remove_jwt_bearer(i_jwt))
            return GC_JWT_VALIDITY_OK
        } catch (ExpiredJwtException e_expired) {
            l().log_warning(s.Expired_token_Z1, e_expired)
            return GC_JWT_VALIDITY_EXPIRED
        } catch (Throwable e_others) {
            l().log_warning(s.Exception_validating_token_Z1, e_others)
            return GC_JWT_VALIDITY_INVALID
        }
    }

    @I_black_box("error")
    static String generate_jwt(String i_subject, Integer i_duration_days, Integer i_duration_hours) {
        Date l_date = new Date()
        use(TimeCategory) { l_date += i_duration_days.days + i_duration_hours.hours }
        String l_jwt_string = Jwts.builder()
                .setExpiration(l_date)
                .setSubject(i_subject)
                .signWith(SignatureAlgorithm.HS512, get_jwt_manager().get_jwt_key())
                .compact()
        return l_jwt_string
    }

    @I_black_box("error")
    static String json_tag(HashMap<String, String[]> i_parameter_map, String i_element_name, String i_data_type = GC_DATA_TYPE_NUMBER) {
        String l_parameter_value = nvl(i_parameter_map.get(i_element_name)?.getAt(GC_FIRST_INDEX), GC_EMPTY_STRING)
        if (is_null(l_parameter_value)) {
            return GC_JSON_NULL
        } else {
            if ([GC_DATA_TYPE_STRING, GC_DATA_TYPE_DATETIME].contains(i_data_type)) {
                return "\"" + l_parameter_value + "\""
            } else {
                return l_parameter_value
            }
        }
    }

    @I_black_box("error")
    static String json_tag(GPathResult i_gpath_result, String i_element_name, String i_data_type = GC_DATA_TYPE_NUMBER) {
        ArrayList<NodeChild> l_node_children = find_element(i_gpath_result, i_element_name)
        if (l_node_children.isEmpty()) {
            return GC_JSON_NULL
        } else {
            GPathResult l_gpath_result = l_node_children.first()
            if (l_gpath_result.text() != GC_EMPTY_STRING) {
                if ([GC_DATA_TYPE_STRING, GC_DATA_TYPE_DATETIME].contains(i_data_type)) {
                    return "\"" + l_gpath_result.text() + "\""
                } else {
                    return l_gpath_result.text()
                }
            } else {
                return GC_JSON_NULL
            }
        }
    }

    @I_black_box("error")
    static String get_uri(HttpServletRequest i_http_servlet_request) {
        return i_http_servlet_request.getScheme() + "://" +
                i_http_servlet_request.getServerName() +
                ("http".equals(i_http_servlet_request.getScheme()) && i_http_servlet_request.getServerPort() == 80 || "https".equals(i_http_servlet_request.getScheme()) && i_http_servlet_request.getServerPort() == 443 ? "" : ":" + i_http_servlet_request.getServerPort()) +
                i_http_servlet_request.getRequestURI() +
                (i_http_servlet_request.getQueryString() != null ? "?" + i_http_servlet_request.getQueryString() : "")
    }
}
