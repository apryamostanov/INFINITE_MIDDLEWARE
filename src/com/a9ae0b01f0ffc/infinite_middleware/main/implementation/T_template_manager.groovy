package implementation

import annotations.I_black_box
import groovy.text.SimpleTemplateEngine
import groovy.text.Template
import base.T_middleware_base_6_util
import other.E_application_exception

class T_template_manager extends T_middleware_base_6_util {

    protected HashMap<String, Template> p_xml_templates = new HashMap<String, Template>()
    protected HashMap<String, Template> p_json_templates = new HashMap<String, Template>()

    @I_black_box("error")
    void init_xml_templates(String i_template_path) {
        new File(i_template_path).eachFile { l_file ->
            p_xml_templates.put(l_file.getName().take(l_file.getName().lastIndexOf(GC_POINT)).toString(), new SimpleTemplateEngine().createTemplate(l_file))
        }
    }

    @I_black_box("error")
    void init_json_templates(String i_template_path) {
        new File(i_template_path).eachFile { l_file ->
            p_json_templates.put(l_file.getName().take(l_file.getName().lastIndexOf(GC_POINT)).toString(), new SimpleTemplateEngine().createTemplate(l_file))
        }
    }

    @I_black_box("error")
    Template get_xml_template(String i_template_name) {
        if (p_xml_templates.containsKey(i_template_name)) {
            return p_xml_templates.get(i_template_name)
        } else {
            throw new E_application_exception(s.XML_template_not_found_Z1, i_template_name)
        }
    }

    @I_black_box("error")
    Template get_json_template(String i_template_name) {
        if (p_json_templates.containsKey(i_template_name)) {
            return p_json_templates.get(i_template_name)
        } else {
            throw new E_application_exception(s.JSON_template_not_found_Z1, i_template_name)
        }
    }

}
