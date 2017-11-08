package Interfaces

interface I_conversion_module {

    I_http_message convert_http_messages(Map<String, I_http_message> i_messages_by_name, String i_template_name)

}