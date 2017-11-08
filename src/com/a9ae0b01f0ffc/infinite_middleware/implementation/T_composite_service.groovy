package implementation

import Interfaces.I_composite_service
import Interfaces.I_http_message
import annotations.I_black_box
import annotations.I_fix_variable_scopes
import main.T_middleware_base_6_util

@I_fix_variable_scopes
class T_composite_service extends T_middleware_base_6_util implements I_composite_service {

    @Override
    @I_black_box("error")
    I_http_message process_http_message(I_http_message i_rest_request, String i_controller_name, Boolean i_skip_validations = GC_FALSE) {
        I_http_message l_result_http_message = new T_http_message()
        Binding l_binding = new Binding()
        l_binding.setVariable("i_rest_request", i_rest_request)
        I_http_message l_service_response = get_closure_manager().get_composite_services_groovy_script_engine().run(get_closure_manager().get_composite_service_file_name(i_controller_name), l_binding) as I_http_message
        l_service_response.set_payload_type(GC_PAYLOAD_TYPE_JSON)
        l_service_response.set_method(app_conf().GC_REQUEST_METHOD_INTERNAL)
        l_service_response.set_header(GC_HEADER_NAME_USER_AGENT, app_conf().GC_CONTENT_TYPE_EXTERNAL)
        l_service_response.set_header(GC_HEADER_NAME_ACCEPT_LANGUAGE, app_conf().GC_ACCEPT_LANGUAGE_EXTERNAL)
        l_service_response.set_header(GC_HEADER_NAME_CONTENT_TYPE, app_conf().GC_CONTENT_TYPE_EXTERNAL)
        l_service_response.set_service_name(l_service_response.get_service_name())
        return l_service_response
    }

}
