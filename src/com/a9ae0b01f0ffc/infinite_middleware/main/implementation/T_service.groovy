package implementation

import Interfaces.I_http_message
import Interfaces.I_service
import annotations.I_black_box
import annotations.I_fix_variable_scopes
import base.T_middleware_base_6_util

@I_fix_variable_scopes
class T_service extends T_middleware_base_6_util implements I_service {

    @Override
    @I_black_box("error")
    I_http_message process_http_messages(Map<String, I_http_message> i_messages_by_name, String i_controller_name, Boolean i_skip_validations = GC_FALSE) {
        I_http_message l_result_http_message = new T_http_message()
        if (!["GenericValidator", "ForwardUnchanged"].contains(i_controller_name) && not(i_skip_validations)) {
            l_result_http_message = process_http_messages(i_messages_by_name, "GenericValidator")
            if (l_result_http_message.get_status() != GC_HTTP_RESP_CODE_OK) {
                return l_result_http_message
            }
        }
        Binding l_binding = new Binding()
        l_binding.setVariable("i_http_messages", i_messages_by_name)
        I_http_message l_service_response = get_closure_manager().get_services_groovy_script_engine().run(get_closure_manager().get_service_file_name(i_controller_name), l_binding) as I_http_message
        l_service_response.set_payload_type(GC_PAYLOAD_TYPE_JSON)
        l_result_http_message.set_method(app_conf().GC_REQUEST_METHOD_INTERNAL)
        l_result_http_message.set_header(GC_HEADER_NAME_USER_AGENT, app_conf().GC_CONTENT_TYPE_EXTERNAL)
        l_result_http_message.set_header(GC_HEADER_NAME_ACCEPT_LANGUAGE, app_conf().GC_ACCEPT_LANGUAGE_EXTERNAL)
        l_result_http_message.set_header(GC_HEADER_NAME_CONTENT_TYPE, app_conf().GC_CONTENT_TYPE_EXTERNAL)
        l_result_http_message.set_service_name(l_service_response.get_service_name())
        i_messages_by_name.put("service_response", l_service_response)
        String l_error_found = l_service_response.json_field("ErrorFound", GC_DATA_TYPE_NUMBER)
        l().log_trace(l_error_found)
        if (l_error_found == "Yes") {
            l_result_http_message.set_payload(get_template_manager().get_json_template("GenericError").make(i_messages_by_name).toString())
            l_result_http_message.set_status(nvl(l_service_response.get_status(), GC_HTTP_RESP_CODE_SERVER_ERROR) as Integer)
        } else {
            l_result_http_message.set_payload(get_template_manager().get_json_template(l_result_http_message.get_service_name()).make(i_messages_by_name).toString())
            l_result_http_message.set_status(GC_HTTP_RESP_CODE_OK)
        }
        return l_result_http_message
    }

}
