package implementation

import Interfaces.I_conversion_module
import Interfaces.I_http_message
import annotations.I_black_box
import annotations.I_fix_variable_scopes
import groovy.transform.ToString
import base.T_middleware_base_6_util

@I_fix_variable_scopes
@ToString(includeNames = true, includeFields = true, includeSuper = false)
class T_rest2soap_template_converter extends T_middleware_base_6_util implements I_conversion_module {

    @I_black_box("error")
    @Override
    I_http_message convert_http_messages(Map<String, I_http_message> i_messages_by_name, String i_template_name) {
        I_http_message l_result_http_message = new T_http_message()
        l_result_http_message.set_service_name(i_template_name)
        l_result_http_message.set_payload(get_template_manager().get_xml_template(i_template_name).make(i_messages_by_name).toString())
        l_result_http_message.set_header(GC_HEADER_NAME_CONTENT_TYPE, app_conf().GC_CONTENT_TYPE_INTERNAL)
        l_result_http_message.set_header(GC_SOAPACTION_HEADER_NAME, app_conf().GC_SOAPACTION_HEADER_PREFIX + i_template_name)
        l_result_http_message.set_payload_type(GC_PAYLOAD_TYPE_XML)
        l_result_http_message.set_method(GC_METHOD_POST)
        l_result_http_message.set_uri(app_conf().GC_ENDPOINT_INTERNAL)
        return l_result_http_message
    }

}
