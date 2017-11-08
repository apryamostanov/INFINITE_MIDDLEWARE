package implementation

import annotations.I_black_box
import annotations.I_fix_variable_scopes
import groovy.transform.ToString
import base.T_middleware_base_6_util

import javax.net.ssl.X509TrustManager
import java.security.cert.X509Certificate

@I_fix_variable_scopes
@ToString(includeNames = true, includeFields = true, includeSuper = false)
class T_test_trust_manager extends T_middleware_base_6_util implements X509TrustManager {

    @Override
    @I_black_box("error")
    X509Certificate[] getAcceptedIssuers() {
        return GC_NULL_OBJ_REF as X509Certificate[]
    }

    @Override
    @I_black_box("error")
    void checkClientTrusted(X509Certificate[] certs, String authType) {
    }

    @Override
    @I_black_box("error")
    void checkServerTrusted(X509Certificate[] certs, String authType) {
    }

}
