package implementation

import other.T_common_conf
import static base.T_logging_base_4_const.*

class T_middleware_conf extends T_common_conf {

    String GC_MIDDLEWARE_CLASSES_CONF
    String GC_BLACK_BOX_CONFIG
    String GC_THREAD_CONFIG_FILE_NAME
    String GC_REQUEST_METHOD_INTERNAL
    String GC_USER_AGENT_INTERNAL
    String GC_ACCEPT_LANGUAGE_INTERNAL
    String GC_ACCEPT_LANGUAGE_EXTERNAL
    String GC_CONTENT_TYPE_INTERNAL
    String GC_CONTENT_TYPE_EXTERNAL
    String GC_HOST_NAME_INTERNAL
    String GC_UNSERCURE_TEST_TLS_SSL_MODE_INTERNAL
    String GC_ENDPOINT_INTERNAL
    String GC_JSON_INDENT
    String GC_SOAP_USER
    String GC_SOAP_PASSWORD
    String GC_SOAP_IP
    String GC_SOAP_SOURCE
    String GC_SOAP_API_VERSION
    String GC_KEYSTORE_TYPE
    String GC_KEYSTORE_PATH
    String GC_KEYSTORE_PASSWORD
    String GC_JWT_KEYSTORE_TYPE
    String GC_JWT_KEYSTORE_PATH
    String GC_JWT_KEYSTORE_PASSWORD
    String GC_JWT_KEYSTORE_ALIAS
    String GC_JAVAX_DEBUG
    String GC_SOAPACTION_HEADER_PREFIX
    String GC_XML_TEMPLATES_PATH
    String GC_JSON_TEMPLATES_PATH
    String GC_TEMPLATE_FILENAME_EXTENSION
    String GC_TYPES_PATH
    String GC_DYNAMIC_TOKEN_CODE
    Closure GC_DYNAMIC_TOKEN_CODE_CLOSURE
    String GC_ROUTING_MODULE_NAME
    String GC_ROUTING_MODULE_PATH
    String GC_MIDDLEWARE_PACKAGE_TOKEN
    String GC_MIDDLEWARE_PACKAGE_NAME
    String GC_SERVICE_MODULES_PATH
    String GC_NO_JWT_SERVICES
    String GC_JWT_TEST_MODE
    String GC_TEST_TRACE_MODE
    String GC_COMPOSITE_SERVICES_PATH
    String GC_TEST_HTTP_PLAINTEXT_MODE
    String GC_AWS_SIGNATURE_ENABLED
    String GC_AWS_REGION
    String GC_AWS_ACCESS_KEY
    String GC_AWS_SECRET_KEY
    String GC_AWS_SERVICE_NAME
    String GC_AWS_RESOURCE_NAME

    T_middleware_conf(String i_conf_file_name) {
        super(i_conf_file_name)
    }

    @Override
    void refresh_config() {
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
        GC_TEST_HTTP_PLAINTEXT_MODE = nvl_empty_map(get_conf().test_http_plaintext_mode, GC_TEST_HTTP_PLAINTEXT_MODE)
        GC_AWS_SIGNATURE_ENABLED = nvl_empty_map(get_conf().aws_signature_enabled, GC_AWS_SIGNATURE_ENABLED)
        GC_AWS_REGION = nvl_empty_map(get_conf().aws_region, GC_AWS_REGION)
        GC_AWS_ACCESS_KEY = nvl_empty_map(get_conf().aws_access_key, GC_AWS_ACCESS_KEY)
        GC_AWS_SECRET_KEY = nvl_empty_map(get_conf().aws_secret_key, GC_AWS_SECRET_KEY)
        GC_AWS_SERVICE_NAME = nvl_empty_map(get_conf().aws_service_name, GC_AWS_SERVICE_NAME)
        GC_AWS_RESOURCE_NAME = nvl_empty_map(get_conf().aws_resource_name, GC_AWS_RESOURCE_NAME)
    }

}
