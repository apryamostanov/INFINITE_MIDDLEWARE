package Interfaces

import groovy.json.JsonBuilder
import groovy.util.slurpersupport.GPathResult

interface I_http_message {

    String json_field(String i_element_name, String i_datatype)

    String json_url_param(String i_element_name, String i_datatype)

    JsonBuilder get_json_builder()

    String get_payload_xml()

    String get_payload_json()

    GPathResult get_gpath_result()

    String xml_field(String i_element_name, String i_name_space)

    String xml_url_param(String i_element_name, String i_name_space)

    String get_method()

    void set_method(String i_method)

    String get_header(String i_header_name)

    void set_header(String i_header_name, String i_header_value)

    Map<String, String> get_headers()

    void set_headers(Map<String, String> i_header_map)

    String get_payload()

    void set_payload(String i_payload)

    String get_uri()

    void set_uri(String i_url)

    void set_parameters(HashMap<String, String[]> i_parameter_map)

    HashMap<String, String[]> get_parameters()

    String get_payload_type()

    void set_payload_type(String i_payload_type)

    Integer get_status()

    void set_status(Integer i_status)

    void set_service_name(String i_service_name)

    String get_service_name()

    void set_payload_json(String i_payload_json)

}