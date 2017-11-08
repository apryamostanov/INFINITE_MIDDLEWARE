package implementation

import Interfaces.I_http_message
import Interfaces.I_routing_module
import annotations.I_black_box
import annotations.I_fix_variable_scopes
import base.T_middleware_base_6_util

@I_fix_variable_scopes
class T_router extends T_middleware_base_6_util implements I_routing_module {

    @Override
    @I_black_box("error")
    I_http_message dispatch_http_message(I_http_message i_http_message) {
        Binding l_binding = new Binding()
        l_binding.setVariable("i_rest_request", i_http_message)
        I_http_message l_service_response = get_closure_manager().get_routing_modules_groovy_script_engine().run(get_closure_manager().get_routing_module_file_name(app_conf().GC_ROUTING_MODULE_NAME), l_binding) as I_http_message
        return l_service_response
    }

}
