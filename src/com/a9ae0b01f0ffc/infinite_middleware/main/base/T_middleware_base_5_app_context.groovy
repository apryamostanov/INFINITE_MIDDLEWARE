package base

import annotations.I_fix_variable_scopes
import implementation.T_closure_manager
import implementation.T_jwt_manager
import implementation.T_middleware_conf
import implementation.T_template_manager
import other.T_thread_local

import javax.servlet.ServletContext

@I_fix_variable_scopes
class T_middleware_base_5_app_context extends T_middleware_base_4_const {

    private static ServletContext p_servlet_context = GC_NULL_OBJ_REF as ServletContext
    private static T_thread_local<T_middleware_base_5_app_context> p_middleware_base_5_app_context_thread_local = new T_thread_local<T_middleware_base_5_app_context>()
    private T_middleware_conf p_app_config = GC_NULL_OBJ_REF as T_middleware_conf
    private T_template_manager p_template_manager = GC_NULL_OBJ_REF as T_template_manager
    private T_closure_manager p_closure_manager = GC_NULL_OBJ_REF as T_closure_manager
    private T_jwt_manager p_jwt_manager = GC_NULL_OBJ_REF as T_jwt_manager

    static void init_app_context(String i_commons_conf_file_name) {
        p_middleware_base_5_app_context_thread_local.set(new T_middleware_base_5_app_context())
        get_app_context().p_app_config = new T_middleware_conf(i_commons_conf_file_name)
        get_app_context().p_template_manager = new T_template_manager()
        get_app_context().p_closure_manager = new T_closure_manager()
        get_app_context().p_jwt_manager = new T_jwt_manager()
    }

    static void init_runtime() {
        get_template_manager().init_xml_templates(app_conf().GC_XML_TEMPLATES_PATH)
        get_template_manager().init_json_templates(app_conf().GC_JSON_TEMPLATES_PATH)
        get_closure_manager().init_routing_modules(app_conf().GC_ROUTING_MODULE_PATH)
        get_closure_manager().init_services(app_conf().GC_SERVICE_MODULES_PATH)
        get_closure_manager().init_composite_services(app_conf().GC_COMPOSITE_SERVICES_PATH)
        get_jwt_manager().init_jwt_key()
    }

    static T_middleware_conf app_conf() {
        return get_app_context().p_app_config
    }

    static void mutual_context_registration(ServletContext i_servlet_context) {
        p_servlet_context = i_servlet_context
        i_servlet_context.setAttribute(GC_MIDDLEWARE_CONTEXT, new T_middleware_base_5_app_context())
    }

    static T_middleware_base_5_app_context get_app_context() {
        return nvl(p_middleware_base_5_app_context_thread_local.get(T_middleware_base_5_app_context.class), p_servlet_context?.getAttribute(GC_MIDDLEWARE_CONTEXT)) as T_middleware_base_5_app_context
    }

    static T_template_manager get_template_manager() {
        return get_app_context().p_template_manager
    }

    static T_closure_manager get_closure_manager() {
        return get_app_context().p_closure_manager
    }

    static T_jwt_manager get_jwt_manager() {
        return get_app_context().p_jwt_manager
    }
}
