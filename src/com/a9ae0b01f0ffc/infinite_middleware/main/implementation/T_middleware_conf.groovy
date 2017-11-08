package implementation

import other.T_common_conf
import static base.T_logging_base_4_const.*

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
        GC_MIDDLEWARE_CLASSES_CONF = nvl_empty_map(get_conf().middleware_classes_conf, GC_MIDDLEWARE_CLASSES_CONF)
        GC_BLACK_BOX_CONFIG = nvl_empty_map(get_conf().black_box_config, GC_BLACK_BOX_CONFIG)
        GC_THREAD_CONFIG_FILE_NAME = nvl_empty_map(get_conf().thread_config_file_name, GC_THREAD_CONFIG_FILE_NAME)
        GC_REQUEST_METHOD_INTERNAL = nvl_empty_map(get_conf().request_method_internal, GC_REQUEST_METHOD_INTERNAL)
        GC_USER_AGENT_INTERNAL = nvl_empty_map(get_conf().user_agent_internal, GC_USER_AGENT_INTERNAL)
        GC_ACCEPT_LANGUAGE_INTERNAL = nvl_empty_map(get_conf().accept_language_internal, GC_ACCEPT_LANGUAGE_INTERNAL)
        GC_CONTENT_TYPE_INTERNAL = nvl_empty_map(get_conf().content_type_internal, GC_CONTENT_TYPE_INTERNAL)
        GC_CONTENT_TYPE_EXTERNAL = nvl_empty_map(get_conf().content_type_external, GC_CONTENT_TYPE_EXTERNAL)
        GC_HOST_NAME_INTERNAL = nvl_empty_map(get_conf().host_name_internal, GC_HOST_NAME_INTERNAL)
        GC_UNSERCURE_TEST_TLS_SSL_MODE_INTERNAL = nvl_empty_map(get_conf().unsercure_test_tls_ssl_mode_internal, GC_UNSERCURE_TEST_TLS_SSL_MODE_INTERNAL)
        GC_ENDPOINT_INTERNAL = nvl_empty_map(get_conf().endpoint_internal, GC_ENDPOINT_INTERNAL)
        GC_JSON_INDENT = nvl_empty_map(get_conf().json_indent, GC_JSON_INDENT)
        GC_SOAP_USER = nvl_empty_map(get_conf().soap_user, GC_SOAP_USER)
        GC_SOAP_PASSWORD = nvl_empty_map(get_conf().soap_password, GC_SOAP_PASSWORD)
        GC_SOAP_IP = nvl_empty_map(get_conf().soap_ip, GC_SOAP_IP)
        GC_SOAP_SOURCE = nvl_empty_map(get_conf().soap_source, GC_SOAP_SOURCE)
        GC_SOAP_API_VERSION = nvl_empty_map(get_conf().soap_api_version, GC_SOAP_API_VERSION)
        GC_KEYSTORE_TYPE = nvl_empty_map(get_conf().keystore_type, GC_KEYSTORE_TYPE)
        GC_KEYSTORE_PATH = nvl_empty_map(get_conf().keystore_path, GC_KEYSTORE_PATH)
        GC_KEYSTORE_PASSWORD = nvl_empty_map(get_conf().keystore_password, GC_KEYSTORE_PASSWORD)
        GC_JAVAX_DEBUG = nvl_empty_map(get_conf().javax_debug, GC_JAVAX_DEBUG)
        GC_SOAPACTION_HEADER_PREFIX = nvl_empty_map(get_conf().soapaction_header_prefix, GC_SOAPACTION_HEADER_PREFIX)
        GC_XML_TEMPLATES_PATH = nvl_empty_map(get_conf().xml_templates_path, GC_XML_TEMPLATES_PATH)
        GC_TEMPLATE_FILENAME_EXTENSION = nvl_empty_map(get_conf().template_filename_extension, GC_TEMPLATE_FILENAME_EXTENSION)
        GC_TYPES_PATH = nvl_empty_map(get_conf().types_path, GC_TYPES_PATH)
        GC_DYNAMIC_TOKEN_CODE = nvl_empty_map(get_conf().dynamic_token_code, GC_DYNAMIC_TOKEN_CODE)
        GC_ROUTING_MODULE_NAME = nvl_empty_map(get_conf().routing_module_name, GC_ROUTING_MODULE_NAME)
        GC_ROUTING_MODULE_PATH = nvl_empty_map(get_conf().routing_module_path, GC_ROUTING_MODULE_PATH)
        GC_MIDDLEWARE_PACKAGE_TOKEN = nvl_empty_map(get_conf().middleware_package_token, GC_MIDDLEWARE_PACKAGE_TOKEN)
        GC_MIDDLEWARE_PACKAGE_NAME = nvl_empty_map(get_conf().middleware_package_name, GC_MIDDLEWARE_PACKAGE_NAME)
        GC_SERVICE_MODULES_PATH = nvl_empty_map(get_conf().service_modules_path, GC_SERVICE_MODULES_PATH)
        GC_JSON_TEMPLATES_PATH = nvl_empty_map(get_conf().json_templates_path, GC_JSON_TEMPLATES_PATH)
        GC_JWT_KEYSTORE_TYPE = nvl_empty_map(get_conf().jwt_keystore_type, GC_JWT_KEYSTORE_TYPE)
        GC_JWT_KEYSTORE_PATH = nvl_empty_map(get_conf().jwt_keystore_path, GC_JWT_KEYSTORE_PATH)
        GC_JWT_KEYSTORE_PASSWORD = nvl_empty_map(get_conf().jwt_keystore_password, GC_JWT_KEYSTORE_PASSWORD)
        GC_JWT_KEYSTORE_ALIAS = nvl_empty_map(get_conf().jwt_keystore_alias, GC_JWT_KEYSTORE_ALIAS)
        GC_NO_JWT_SERVICES = nvl_empty_map(get_conf().no_jwt_services, GC_NO_JWT_SERVICES)
        GC_JWT_TEST_MODE = nvl_empty_map(get_conf().jwt_test_mode, GC_JWT_TEST_MODE)
        GC_TEST_TRACE_MODE = nvl_empty_map(get_conf().test_trace_mode, GC_TEST_TRACE_MODE)
        GC_COMPOSITE_SERVICES_PATH = nvl_empty_map(get_conf().composite_services_path, GC_COMPOSITE_SERVICES_PATH)
    }

}
