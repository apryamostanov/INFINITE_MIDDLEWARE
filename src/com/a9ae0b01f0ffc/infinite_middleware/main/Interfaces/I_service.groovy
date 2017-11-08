package Interfaces

import Interfaces.I_http_message

interface I_service {

    I_http_message process_http_messages(Map<String, I_http_message> i_messages_by_name, String i_service_name, Boolean i_skip_validations)

}