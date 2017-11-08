package implementation

import annotations.I_black_box
import main.T_middleware_base_6_util
import other.E_application_exception

class T_closure_manager extends T_middleware_base_6_util {

    protected HashMap<String, String> p_routing_module_file_names = new HashMap<String, String>()
    protected HashMap<String, String> p_services = new HashMap<String, String>()
    protected HashMap<String, String> p_composite_service_file_names = new HashMap<String, String>()

    protected GroovyScriptEngine p_composite_services_groovy_script_engine = GC_NULL_OBJ_REF as GroovyScriptEngine
    protected GroovyScriptEngine p_routing_modules_groovy_script_engine = GC_NULL_OBJ_REF as GroovyScriptEngine
    protected GroovyScriptEngine p_services_groovy_script_engine = GC_NULL_OBJ_REF as GroovyScriptEngine

    @I_black_box("error")
    void init_routing_modules(String i_routing_modules_path) {
        p_routing_modules_groovy_script_engine = new GroovyScriptEngine(i_routing_modules_path)
        new File(i_routing_modules_path).eachFile { l_file ->
            p_routing_module_file_names.put(l_file.getName().take(l_file.getName().lastIndexOf(GC_POINT)).toString(), l_file.getName())
        }
    }

    @I_black_box("error")
    GroovyScriptEngine get_routing_modules_groovy_script_engine() {
        return p_routing_modules_groovy_script_engine
    }

    @I_black_box("error")
    GroovyScriptEngine get_services_groovy_script_engine() {
        return p_services_groovy_script_engine
    }

    @I_black_box("error")
    GroovyScriptEngine get_composite_services_groovy_script_engine() {
        return p_composite_services_groovy_script_engine
    }

    @I_black_box("error")
    void init_services(String i_services_path) {
        p_services_groovy_script_engine = new GroovyScriptEngine(i_services_path)
        new File(i_services_path).eachFile { l_file ->
            p_services.put(l_file.getName().take(l_file.getName().lastIndexOf(GC_POINT)).toString(), l_file.getName())
        }
    }

    @I_black_box("error")
    void init_composite_services(String i_composite_services_path) {
        p_composite_services_groovy_script_engine = new GroovyScriptEngine(i_composite_services_path)
        new File(i_composite_services_path).eachFile { l_file ->
            p_composite_service_file_names.put(l_file.getName().take(l_file.getName().lastIndexOf(GC_POINT)).toString(), l_file.getName())
        }
    }

    @I_black_box("error")
    static String get_service_file_name(String i_service_name) {
        if (get_closure_manager().p_services.containsKey(i_service_name)) {
            return get_closure_manager().p_services.get(i_service_name)
        } else {
            throw new E_application_exception(s.Service_not_found_Z1, i_service_name)
        }
    }

    @I_black_box("error")
    static String get_composite_service_file_name(String i_service_name) {
        if (get_closure_manager().p_composite_service_file_names.containsKey(i_service_name)) {
            return get_closure_manager().p_composite_service_file_names.get(i_service_name)
        } else {
            throw new E_application_exception(s.Composite_Service_not_found_Z1, i_service_name)
        }
    }

    @I_black_box("error")
    static String get_routing_module_file_name(String i_routing_module) {
        if (get_closure_manager().p_routing_module_file_names.containsKey(i_routing_module)) {
            return get_closure_manager().p_routing_module_file_names.get(i_routing_module)
        } else {
            throw new E_application_exception(s.Routing_module_not_found_Z1, i_routing_module)
        }
    }

}
