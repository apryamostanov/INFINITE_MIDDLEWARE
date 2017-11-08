package Interfaces

interface I_composite_service {

    I_http_message process_http_message(I_http_message i_http_message, String i_service_name, Boolean i_skip_validations)

}