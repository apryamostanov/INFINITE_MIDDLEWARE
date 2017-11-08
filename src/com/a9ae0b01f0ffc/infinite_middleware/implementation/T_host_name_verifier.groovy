package implementation

import annotations.I_black_box
import annotations.I_fix_variable_scopes
import groovy.transform.ToString
import main.T_middleware_base_6_util

import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSession

@I_fix_variable_scopes
@ToString(includeNames = true, includeFields = true, includeSuper = false)
class T_host_name_verifier extends T_middleware_base_6_util implements HostnameVerifier {

    @Override
    @I_black_box("error")
    boolean verify(String l_host_name, SSLSession l_ssl_session) {
        return GC_TRUE
    }
}
