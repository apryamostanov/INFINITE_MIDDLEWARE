package main

import base.T_common_base_3_utils

class T_middleware_base_4_const extends T_common_base_3_utils {

    public final static String GC_MODE_NORMAL = "normal"
    public final static String GC_MODE_RETRY = "retry"
    public final static Boolean GC_ALL_FIELDS_AS_STRINGS_YES = GC_TRUE
    public final static String GC_STATUS_NEW = "new"
    public final static String GC_STATUS_DELIVERED = "delivered"
    public final static String GC_STATUS_FAILED_NO_CONNECTION = "no_connection"
    public final static String GC_STATUS_FAILED_INVALID_RESPONSE = "invalid_response"
    public final static String GC_STATUS_FAILED_INVALID_REQUEST = "invalid_request"
    public final static String GC_STATUS_FAILED_RESPONSE = "failed_response"
    public final static String GC_STATUS_EXCEPTION = "error"
    public final static Integer GC_RESPONSE_CODE_INVALID_RESPONSE = -1
    public final static Integer GC_RESPONSE_CODE_INVALID_REQUEST = -2
    public final static Integer GC_RESPONSE_CODE_CONNECTION_REFUSED = -3
    public final static String GC_PAYLOAD_TYPE_XML = "xml"
    public final static String GC_PAYLOAD_TYPE_JSON = "json"
    public final static String GC_METHOD_POST = "POST"
    public final static String GC_METHOD_GET = "GET"
    public static final String GC_HEADER_NAME_USER_AGENT = "User-Agent"
    public static final String GC_HEADER_NAME_ACCEPT_LANGUAGE = "Accept-Language"
    public static final String GC_HEADER_NAME_CONTENT_TYPE = "Content-Type"
    public static final String GC_SOAPACTION_HEADER_NAME = "SOAPAction"
    public static final Integer GC_HTTP_RESP_CODE_SERVER_ERROR = 500
    public static final String GC_CONTEXT_NAME_UNIQUE_ID = "UniqueID"
    public static final String GC_CONTEXT_VALUE_UNIQUE_ID_DEFAULT = "unknown_unique_id"
    public static final String GC_PARAMETER_NAME_UNIQUE_ID = "UniqueID"
    public static final String GC_JSON_TEMPLATE_VAR_NAME = "json"
    public static final String GC_URL_TEMPLATE_VAR_NAME = "url"
    public static final String GC_CONF_TEMPLATE_VAR_NAME = "config"
    public static final String GC_UNKNOWN_UNIQUE_ID = "unknown_unique_id"
    public static final String GC_MIDDLEWARE_CONTEXT = "middleware_context"
    public static final String GC_CONTEXT_VAR_NAME_CONFIG_FILE_NAME = "config_file_name"
    public static final String GC_ERROR_FOUND_FIELD = "ErrorFound"
    public static final String GC_DATA_TYPE_STRING = "AN"
    public static final String GC_DATA_TYPE_NUMBER = "N"
    public static final String GC_DATA_TYPE_BOOLEAN = "B"
    public static final String GC_DATA_TYPE_DATETIME = "DT"
    public static final String GC_JSON_NULL = "null"
    public static final String GC_ARRAY_TEMPLATE_BIND_VAR_NAME = "array_element"
    public static final Integer GC_JWT_VALIDITY_OK = 1
    public static final Integer GC_JWT_VALIDITY_EXPIRED = 2
    public static final Integer GC_JWT_VALIDITY_INVALID = 3
    public static final Integer GC_HTTP_RESP_CODE_FORBIDDEN_403 = 403
    public static final Integer GC_HTTP_RESP_CODE_UNAUTHORIZED_401 = 401
    public static final String GC_JAVA_PROP_KEYSTORE_TYPE = "javax.net.ssl.keyStoreType"
    public static final String GC_JAVA_PROP_KEYSTORE_PATH = "javax.net.ssl.keyStore"
    public static final String GC_JAVA_PROP_KEYSTORE_PASSWORD = "javax.net.ssl.keyStorePassword"
    public static final String GC_JAVA_PROP_DEBUG_MODE = "javax.net.debug"
    public static final String GC_JWT_AUTHORIZATION_HEADER_NAME = "authorization"
    public static final String GC_E_OTHERS_TEMPLATE_VAR_NAME = "e_others"
    public static final String GC_TRACE_ERROR_TEMPLATE_FILE_NAME = "TestErrorTrace"
    public static
    final String GC_TRACE_ERROR_TEMPLATE_FILE_NAME_APP_EXCEPTION = "TestErrorTraceApplicationException"

}
