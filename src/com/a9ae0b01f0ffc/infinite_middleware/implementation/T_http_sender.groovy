package implementation

import Interfaces.I_http_message
import annotations.I_black_box
import annotations.I_fix_variable_scopes
import groovy.transform.ToString
import main.T_middleware_base_6_util
import other.E_application_exception

import javax.net.ssl.HttpsURLConnection
import javax.net.ssl.KeyManager
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import java.security.SecureRandom

@I_fix_variable_scopes
@ToString(includeNames = true, includeFields = true, includeSuper = false)
class T_http_sender extends T_middleware_base_6_util {

    static Boolean p_is_soft = GC_FALSE

    @I_black_box("error")
    static Boolean is_soft() {
        return p_is_soft
    }

    @I_black_box("error")
    static void set_soft(Boolean i_is_soft) {
        p_is_soft = i_is_soft
    }

    @I_black_box
    static I_http_message send_http_request(I_http_message i_http_request) throws Exception {
        I_http_message l_http_response = new T_http_message()
        l_http_response.set_service_name(i_http_request.get_service_name())
        if (i_http_request.get_payload_type() == GC_PAYLOAD_TYPE_XML) {
            if (!validate_xml(i_http_request.get_payload(), p_is_soft)) {
                l_http_response.set_status(GC_RESPONSE_CODE_INVALID_REQUEST)
                return l_http_response
            }
        } else if (i_http_request.get_payload_type() == GC_PAYLOAD_TYPE_JSON) {
            if (!validate_json(i_http_request.get_payload(), p_is_soft)) {
                l_http_response.set_status(GC_RESPONSE_CODE_INVALID_REQUEST)
                return l_http_response
            }
        } else {
            throw new E_application_exception(s.Unsupported_payload_type_Z1, i_http_request.get_payload_type())
        }
        if (app_conf().GC_UNSERCURE_TEST_TLS_SSL_MODE_INTERNAL == GC_TRUE_STRING) {
            l().log_warning(s.UNSECURE_TEST_TLS_MODE_IS_USED)
            l().log_warning(s.DO_NOT_USE_ON_PRODUCTION)
            SSLContext l_ssl_context = SSLContext.getInstance("TLS")
            T_test_trust_manager[] l_test_trust_managers = new T_test_trust_manager()
            l_ssl_context.init(GC_NULL_OBJ_REF as KeyManager[], l_test_trust_managers, GC_NULL_OBJ_REF as SecureRandom)
            HttpsURLConnection.setDefaultSSLSocketFactory(l_ssl_context.getSocketFactory())
        } else {
            HttpsURLConnection.setDefaultSSLSocketFactory(SSLSocketFactory.getDefault() as SSLSocketFactory)
        }
        URL l_url = new URL(i_http_request.get_uri())
        HttpsURLConnection l_https_url_connection = (HttpsURLConnection) l_url.openConnection()
        if (app_conf().GC_UNSERCURE_TEST_TLS_SSL_MODE_INTERNAL == GC_TRUE_STRING) {
            l_https_url_connection.setHostnameVerifier(new T_host_name_verifier())
        }
        l_https_url_connection.setRequestMethod(i_http_request.get_method())
        for (l_header_name in i_http_request.get_headers().keySet()) {
            l_https_url_connection.setRequestProperty(l_header_name, i_http_request.get_header(l_header_name))
        }
        l_https_url_connection.setDoOutput(GC_TRUE)
        DataOutputStream l_data_output_stream
        try {
            l_data_output_stream = new DataOutputStream(l_https_url_connection.getOutputStream())
        } catch (ConnectException e_connection_refused) {
            if (p_is_soft) {
                l_http_response.set_status(GC_RESPONSE_CODE_CONNECTION_REFUSED)
                return l_http_response
            } else {
                throw new E_application_exception(s.Connection_exception_Z1, e_connection_refused)
            }
        }
        l_data_output_stream.writeBytes(i_http_request.get_payload())
        l_data_output_stream.flush()
        l_data_output_stream.close()
        l().log_send_http(i_http_request)
        Integer l_response_code = l_https_url_connection.getResponseCode()
        InputStream l_input_stream = get_input_stream(l_https_url_connection)
        if (l_input_stream != GC_NULL_OBJ_REF) {
            BufferedReader l_buffered_reader = new BufferedReader(new InputStreamReader(l_input_stream))
            String l_buffered_reader_line
            StringBuffer l_response = new StringBuffer()
            while ((l_buffered_reader_line = l_buffered_reader.readLine()) != GC_NULL_OBJ_REF) {
                l_response.append(l_buffered_reader_line)
            }
            l_buffered_reader.close()
            //l_http_response.set_payload_type(i_http_request.get_payload_type())
            l_http_response.set_payload_type(GC_PAYLOAD_TYPE_XML)
            l_http_response.set_payload(l_response.toString())
        }
        for (l_header_name in l_https_url_connection.getHeaderFields().keySet()) {
            l_http_response.set_header(l_header_name, l_https_url_connection.getHeaderField(l_header_name))
        }
        l_http_response.set_status(l_response_code)
        if (i_http_request.get_payload_type() == GC_PAYLOAD_TYPE_XML) {
            if (validate_xml(l_http_response.get_payload(), GC_TRUE)) {
                l().log_receive_http(format_xml(l_http_response.get_payload()))
            }
        } else {
            l().log_receive_http(l_http_response)
        }
        if (i_http_request.get_payload_type() == GC_PAYLOAD_TYPE_XML) {
            if (!validate_xml(l_http_response.get_payload(), p_is_soft)) {
                l_http_response.set_status(GC_RESPONSE_CODE_INVALID_RESPONSE)
                return l_http_response
            }
        } else if (i_http_request.get_payload_type() == GC_PAYLOAD_TYPE_JSON) {
            if (!validate_json(l_http_response.get_payload())) {
                l_http_response.set_status(GC_RESPONSE_CODE_INVALID_RESPONSE)
                return l_http_response
            }
        } else {
            throw new E_application_exception(s.Unsupported_payload_type_Z1, i_http_request.get_payload_type())
        }
        return l_http_response
    }

    @I_black_box("error")
    static InputStream get_input_stream(HttpURLConnection i_http_url_connection) {
        InputStream l_input_stream = GC_NULL_OBJ_REF as InputStream
        if (i_http_url_connection.getErrorStream() == GC_NULL_OBJ_REF) {
            if (i_http_url_connection.getResponseCode() == GC_HTTP_RESP_CODE_OK) {
                l_input_stream = i_http_url_connection.getInputStream()
            }
        } else {
            l_input_stream = i_http_url_connection.getErrorStream()
        }
        return l_input_stream
    }

}
