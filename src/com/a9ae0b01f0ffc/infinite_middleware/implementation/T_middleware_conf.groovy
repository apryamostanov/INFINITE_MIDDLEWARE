package implementation

import other.T_common_conf

class T_middleware_conf extends T_common_conf {

    static String GC_MIDDLEWARE_CLASSES_CONF
    static String GC_BLACK_BOX_CONFIG
    static String GC_THREAD_CONFIG_FILE_NAME
    static String GC_REQUEST_METHOD_INTERNAL
    static String GC_USER_AGENT_INTERNAL
    static String GC_ACCEPT_LANGUAGE_INTERNAL
    static String GC_ACCEPT_LANGUAGE_EXTERNAL
    static String GC_CONTENT_TYPE_INTERNAL
    static String GC_CONTENT_TYPE_EXTERNAL
    static String GC_HOST_NAME_INTERNAL
    static String GC_UNSERCURE_TEST_TLS_SSL_MODE_INTERNAL
    static String GC_ENDPOINT_INTERNAL
    static String GC_JSON_INDENT
    static String GC_SOAP_USER
    static String GC_SOAP_PASSWORD
    static String GC_SOAP_IP
    static String GC_SOAP_SOURCE
    static String GC_SOAP_API_VERSION
    static String GC_KEYSTORE_TYPE
    static String GC_KEYSTORE_PATH
    static String GC_KEYSTORE_PASSWORD
    static String GC_JWT_KEYSTORE_TYPE
    static String GC_JWT_KEYSTORE_PATH
    static String GC_JWT_KEYSTORE_PASSWORD
    static String GC_JWT_KEYSTORE_ALIAS
    static String GC_JAVAX_DEBUG
    static String GC_SOAPACTION_HEADER_PREFIX
    static String GC_XML_TEMPLATES_PATH
    static String GC_JSON_TEMPLATES_PATH
    static String GC_TEMPLATE_FILENAME_EXTENSION
    static String GC_TYPES_PATH
    static String GC_DYNAMIC_TOKEN_CODE
    static Closure GC_DYNAMIC_TOKEN_CODE_CLOSURE
    static String GC_ROUTING_MODULE_NAME
    static String GC_ROUTING_MODULE_PATH
    static String GC_MIDDLEWARE_PACKAGE_TOKEN
    static String GC_MIDDLEWARE_PACKAGE_NAME
    static String GC_SERVICE_MODULES_PATH
    static String GC_NO_JWT_SERVICES
    static String GC_JWT_TEST_MODE
    static String GC_TEST_TRACE_MODE
    static String GC_COMPOSITE_SERVICES_PATH

    T_middleware_conf(String i_conf_file_name) {
        super(i_conf_file_name)
        GC_MIDDLEWARE_CLASSES_CONF = get_conf().middleware_classes_conf
        GC_BLACK_BOX_CONFIG = get_conf().black_box_config
        GC_THREAD_CONFIG_FILE_NAME = get_conf().thread_config_file_name
        GC_REQUEST_METHOD_INTERNAL = get_conf().request_method_internal
        GC_USER_AGENT_INTERNAL = get_conf().user_agent_internal
        GC_ACCEPT_LANGUAGE_INTERNAL = get_conf().accept_language_internal
        GC_CONTENT_TYPE_INTERNAL = get_conf().content_type_internal
        GC_CONTENT_TYPE_EXTERNAL = get_conf().content_type_external
        GC_HOST_NAME_INTERNAL = get_conf().host_name_internal
        GC_UNSERCURE_TEST_TLS_SSL_MODE_INTERNAL = get_conf().unsercure_test_tls_ssl_mode_internal
        GC_ENDPOINT_INTERNAL = get_conf().endpoint_internal
        GC_JSON_INDENT = get_conf().json_indent
        GC_SOAP_USER = get_conf().soap_user
        GC_SOAP_PASSWORD = get_conf().soap_password
        GC_SOAP_IP = get_conf().soap_ip
        GC_SOAP_SOURCE = get_conf().soap_source
        GC_SOAP_API_VERSION = get_conf().soap_api_version
        GC_KEYSTORE_TYPE = get_conf().keystore_type
        GC_KEYSTORE_PATH = get_conf().keystore_path
        GC_KEYSTORE_PASSWORD = get_conf().keystore_password
        GC_JAVAX_DEBUG = get_conf().javax_debug
        GC_SOAPACTION_HEADER_PREFIX = get_conf().soapaction_header_prefix
        GC_XML_TEMPLATES_PATH = get_conf().xml_templates_path
        GC_TEMPLATE_FILENAME_EXTENSION = get_conf().template_filename_extension
        GC_TYPES_PATH = get_conf().types_path
        GC_DYNAMIC_TOKEN_CODE = get_conf().dynamic_token_code
        GC_ROUTING_MODULE_NAME = get_conf().routing_module_name
        GC_ROUTING_MODULE_PATH = get_conf().routing_module_path
        GC_MIDDLEWARE_PACKAGE_TOKEN = get_conf().middleware_package_token
        GC_MIDDLEWARE_PACKAGE_NAME = get_conf().middleware_package_name
        GC_SERVICE_MODULES_PATH = get_conf().service_modules_path
        GC_JSON_TEMPLATES_PATH = get_conf().json_templates_path
        GC_JWT_KEYSTORE_TYPE = get_conf().jwt_keystore_type
        GC_JWT_KEYSTORE_PATH = get_conf().jwt_keystore_path
        GC_JWT_KEYSTORE_PASSWORD = get_conf().jwt_keystore_password
        GC_JWT_KEYSTORE_ALIAS = get_conf().jwt_keystore_alias
        GC_NO_JWT_SERVICES = get_conf().no_jwt_services
        GC_JWT_TEST_MODE = get_conf().jwt_test_mode
        GC_TEST_TRACE_MODE = get_conf().test_trace_mode
        GC_COMPOSITE_SERVICES_PATH = get_conf().composite_services_path
    }

}
