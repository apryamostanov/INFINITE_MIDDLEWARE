package implementation

import Interfaces.I_http_message
import annotations.I_black_box
import annotations.I_fix_variable_scopes
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import groovy.transform.ToString
import groovy.util.slurpersupport.GPathResult
import base.T_middleware_base_6_util
import org.json.JSONObject
import org.json.XML

@I_fix_variable_scopes
@ToString(includeNames = true, includeFields = true, includeSuper = false)
class T_http_message extends T_middleware_base_6_util implements I_http_message, Cloneable {

    HashMap<String, String> p_header_map = new HashMap<String, String>()
    String p_payload = GC_EMPTY_STRING
    String p_payload_xml = GC_EMPTY_STRING
    String p_payload_json = GC_EMPTY_STRING
    String p_method = GC_EMPTY_STRING
    String p_uri = GC_EMPTY_STRING
    String p_payload_type = GC_EMPTY_STRING
    Integer p_status = GC_ZERO
    HashMap<String, String[]> p_parameter_map = new HashMap<String, String[]>()
    String p_service_name = GC_EMPTY_STRING
    GPathResult p_gpath_result = GC_NULL_OBJ_REF as GPathResult
    JsonBuilder p_json_builder = GC_NULL_OBJ_REF as JsonBuilder

    @Override
    Object clone() throws CloneNotSupportedException {
        T_http_message l_new_http_message = new T_http_message()
        l_new_http_message.p_header_map = this.p_header_map.clone() as HashMap<String, String>
        l_new_http_message.p_payload = this.p_payload
        //l_new_http_message.p_payload_xml = this.p_payload_xml
        //l_new_http_message.p_payload_json = this.p_payload_json
        l_new_http_message.p_method = this.p_method
        l_new_http_message.p_uri = this.p_uri
        //l_new_http_message.p_payload_type = this.p_payload_type
        l_new_http_message.p_status = this.p_status
        l_new_http_message.p_parameter_map = this.p_parameter_map.clone() as HashMap<String, String[]>
        l_new_http_message.p_service_name = this.p_service_name
        //l_new_http_message.p_gpath_result = this.p_gpath_result
        //l_new_http_message.p_json_builder = this.p_json_builder
        return l_new_http_message
    }

    @I_black_box("error")
    void set_json_builder(JsonBuilder i_json_builder) {
        p_json_builder = i_json_builder
    }

    @I_black_box("error")
    JsonBuilder get_json_builder() {
        if (is_null(p_json_builder)) {
            Object l_slurped_json = new JsonSlurper().parseText(get_payload_json())
            p_json_builder = new JsonBuilder(l_slurped_json)
        }
        return p_json_builder
    }

    @I_black_box("error")
    String get_payload_xml() {
        if (is_null(p_payload_xml)) {
            if (get_payload_type() == GC_PAYLOAD_TYPE_XML) {
                p_payload_xml = p_payload
            } else {
                if (is_null(p_payload)) {
                    return GC_EMPTY_STRING
                } else {
                    p_payload_xml = XML.toString(new JSONObject(p_payload))
                }
            }
        }
        return p_payload_xml
    }

    @I_black_box("error")
    String get_payload_json() {
        if (is_null(p_payload_json)) {
            if (get_payload_type() == GC_PAYLOAD_TYPE_JSON) {
                p_payload_json = p_payload
            } else {
                if (is_null(p_payload)) {
                    return GC_EMPTY_STRING
                } else {
                    T_xml2json_converter.set_field_types_by_name(file2hashmap(app_conf().GC_TYPES_PATH + get_service_name() + app_conf().GC_TEMPLATE_FILENAME_EXTENSION))
                    JSONObject l_json_object = T_xml2json_converter.toJSONObject(strip_namespaces_from_xml(unbox_soap_xml(p_payload)), GC_ALL_FIELDS_AS_STRINGS_YES)
                    p_payload_json = l_json_object.toString(new Integer(app_conf().GC_JSON_INDENT))
                }
            }
        }
        return p_payload_json
    }

    @I_black_box("error")
    GPathResult get_gpath_result() {
        if (is_null(get_payload_xml())) {
            return GC_NULL_OBJ_REF as GPathResult
        } else {
            if (is_null(p_gpath_result)) {
                p_gpath_result = new XmlSlurper().parseText(get_payload_xml())
            } else {
                return p_gpath_result
            }
        }
    }

    @I_black_box("error")
    String xml_field(String i_element_name, String i_name_space = GC_EMPTY_STRING, String i_tag_name = GC_EMPTY_STRING) {
        if (is_null(get_gpath_result())) {
            return GC_EMPTY_STRING
        } else {
            return xml_tag(get_gpath_result(), i_element_name, i_name_space, i_tag_name)
        }
    }

    @I_black_box("error")
    String xml_url_param(String i_element_name, String i_name_space = GC_EMPTY_STRING) {
        return xml_tag(p_parameter_map, i_element_name, i_name_space)
    }

    @I_black_box("error")
    String json_field(String i_element_name, String i_datatype = GC_DATA_TYPE_NUMBER) {
        if (is_null(get_gpath_result())) {
            return GC_EMPTY_STRING
        } else {
            return json_tag(get_gpath_result(), i_element_name, i_datatype)
        }
    }

    @I_black_box("error")
    String json_url_param(String i_element_name, String i_datatype = GC_DATA_TYPE_NUMBER) {
        return json_tag(p_parameter_map, i_element_name, i_datatype)
    }

    @Override
    @I_black_box("error")
    String get_method() {
        return p_method
    }

    @Override
    @I_black_box("error")
    void set_method(String i_method) {
        p_method = i_method
    }

    @Override
    @I_black_box("error")
    String get_service_name() {
        return p_service_name
    }

    @Override
    @I_black_box("error")
    void set_service_name(String i_service_name) {
        p_service_name = i_service_name
    }

    @Override
    @I_black_box("error")
    String get_header(String i_header_name) {
        return p_header_map.get(i_header_name)
    }

    @Override
    @I_black_box("error")
    void set_header(String i_header_name, String i_header_value) {
        p_header_map.put(i_header_name, i_header_value)
    }

    @Override
    @I_black_box("error")
    Map<String, String> get_headers() {
        return p_header_map
    }

    @Override
    @I_black_box("error")
    void set_headers(Map<String, String> i_header_map) {
        p_header_map = i_header_map
    }

    @Override
    @I_black_box("error")
    String get_payload() {
        return p_payload
    }

    @Override
    @I_black_box("error")
    void set_payload(String i_payload) {
        p_payload = i_payload
    }

    @Override
    String get_uri() {
        return p_uri
    }

    @Override
    @I_black_box("error")
    void set_uri(String i_uri) {
        p_uri = i_uri
    }

    @Override
    @I_black_box("error")
    void set_parameters(HashMap<String, String[]> i_parameter_map) {
        p_parameter_map = i_parameter_map
    }

    @Override
    @I_black_box("error")
    HashMap<String, String[]> get_parameters() {
        return p_parameter_map
    }

    @Override
    @I_black_box("error")
    String get_payload_type() {
        return p_payload_type
    }

    @Override
    @I_black_box("error")
    void set_payload_type(String i_payload_type) {
        p_payload_type = i_payload_type
    }

    @Override
    @I_black_box("error")
    Integer get_status() {
        return p_status
    }

    @Override
    @I_black_box("error")
    void set_status(Integer i_status) {
        p_status = i_status
    }

    @I_black_box("error")
    void set_payload_json(String i_payload_json) {
        p_payload_json = i_payload_json
    }
}
