package implementation

import Interfaces.I_conversion_module
import Interfaces.I_http_message
import annotations.I_black_box
import annotations.I_fix_variable_scopes
import groovy.json.JsonBuilder
import groovy.transform.ToString
import base.T_middleware_base_6_util
import org.json.JSONObject

@I_fix_variable_scopes
@ToString(includeNames = true, includeFields = true, includeSuper = false)
class T_soap2rest_automated_converter extends T_middleware_base_6_util implements I_conversion_module {

    @I_black_box("error")
    static Object fix_arrays_with_one_element(Object io_json_builder_object_to_fix) {
        Object l_fixed_json_builder_object = io_json_builder_object_to_fix
        if (is_not_null(io_json_builder_object_to_fix)) {
            if (!(io_json_builder_object_to_fix instanceof List)) {
                l_fixed_json_builder_object = new ArrayList()
                l_fixed_json_builder_object.add(io_json_builder_object_to_fix)
            }
        }
        return l_fixed_json_builder_object
    }

    @I_black_box
//("error")
    static String post_process_json(JsonBuilder i_json_builder) {
        Object l_check_json
        l_check_json = i_json_builder.content?.ListStatementDateResponse?.ListStatementDateResult?.StatementDates
        if (is_not_null(l_check_json?.StatementDateList)) {
            l_check_json.StatementDateList = fix_arrays_with_one_element(l_check_json.StatementDateList)
        }
        l_check_json = i_json_builder.content?.GetCardDetailResponse?.GetCardDetailResult?.CustomerDetail?.CustomerIDDetails
        if (is_not_null(l_check_json?.CustomerIDDetails)) {
            l_check_json.CustomerIDDetails = fix_arrays_with_one_element(l_check_json.CustomerIDDetails)
        }
        l_check_json = i_json_builder.content?.SearchWalletAccountResponse?.SearchWalletAccountResult?.WalletList
        if (is_not_null(l_check_json?.WalletList)) {
            l_check_json.WalletList = fix_arrays_with_one_element(l_check_json.WalletList)
        }
        l_check_json = i_json_builder.content?.GetTransactionHistoryResponse?.GetTransactionHistoryResult?.TransactionHistoryList
        if (is_not_null(l_check_json?.TransactionsHistory)) {
            l_check_json.TransactionsHistory = fix_arrays_with_one_element(l_check_json.TransactionsHistory)
        }
        l_check_json = i_json_builder.content?.CardSearchResponse?.CardSearchResult?.CardSearchRecord_List
        if (is_not_null(l_check_json?.CardsSearchRecord)) {
            l_check_json.CardsSearchRecord = fix_arrays_with_one_element(l_check_json.CardsSearchRecord)
        }
        l_check_json = i_json_builder.content?.UserRegistrationValidationParametersResponse?.UserRegistrationValidationParametersResult?.Question
        if (is_not_null(l_check_json?.Question)) {
            l_check_json.Question = fix_arrays_with_one_element(l_check_json.Question)
        }
        l_check_json = i_json_builder.content?.GetProductParametersResponse?.GetProductParametersResult?.AutomatedReports
        if (is_not_null(l_check_json?.AutomatedReports)) {
            l_check_json.AutomatedReports = fix_arrays_with_one_element(l_check_json.AutomatedReports)
        }
        l_check_json = i_json_builder.content?.GetProductParametersResponse?.GetProductParametersResult?.ACHOriginator
        if (is_not_null(l_check_json?.ACHOriginators)) {
            l_check_json.ACHOriginators = fix_arrays_with_one_element(l_check_json.ACHOriginators)
        }
        l_check_json = i_json_builder.content?.GetProductParametersResponse?.GetProductParametersResult?.WebSelfService
        if (is_not_null(l_check_json?.SecurityQuestions)) {
            l_check_json.SecurityQuestions = fix_arrays_with_one_element(l_check_json.SecurityQuestions)
        }
        l_check_json = i_json_builder.content?.GetProductParametersResponse?.GetProductParametersResult?.SelfServiceMenuItems
        if (is_not_null(l_check_json?.SSMenuItems)) {
            l_check_json.SSMenuItems = fix_arrays_with_one_element(l_check_json.SSMenuItems)
        }
        l_check_json = i_json_builder.content?.GetProductParametersResponse?.GetProductParametersResult?.CardHolderAlerts
        if (is_not_null(l_check_json?.CardHolderAlerts)) {
            l_check_json.CardHolderAlerts = fix_arrays_with_one_element(l_check_json.CardHolderAlerts)
        }
        l_check_json = i_json_builder.content?.GetProductParametersResponse?.GetProductParametersResult?.MerchantCategories
        if (is_not_null(l_check_json?.MerchantCategories)) {
            l_check_json.MerchantCategories = fix_arrays_with_one_element(l_check_json.MerchantCategories)
        }
        l_check_json = i_json_builder.content?.GetProductParametersResponse?.GetProductParametersResult?.LoadLimits
        if (is_not_null(l_check_json?.LoadLimits)) {
            l_check_json.LoadLimits = fix_arrays_with_one_element(l_check_json.LoadLimits)
        }
        l_check_json = i_json_builder.content?.GetProductParametersResponse?.GetProductParametersResult?.ListOfBlockedCountries
        if (is_not_null(l_check_json?.BlockedCountries)) {
            l_check_json.BlockedCountries = fix_arrays_with_one_element(l_check_json.BlockedCountries)
        }
        l_check_json = i_json_builder.content?.GetProductParametersResponse?.GetProductParametersResult?.LoadLimitSourceAndResponseCode
        if (is_not_null(l_check_json?.SourceAndResponseCodes)) {
            l_check_json.SourceAndResponseCodes = fix_arrays_with_one_element(l_check_json.SourceAndResponseCodes)
        }
        l_check_json = i_json_builder.content?.StatementInformationMultiCurrencyResponse?.StatementInformationMultiCurrencyResult?.WalletDetails
        if (is_not_null(l_check_json?.WalletInformation)) {
            l_check_json.WalletInformation = fix_arrays_with_one_element(l_check_json.WalletInformation)
        }
        l_check_json = i_json_builder.content?.StatementInformationMultiCurrencyResponse?.StatementInformationMultiCurrencyResult?.StatementTransactions
        if (is_not_null(l_check_json?.StatementTransactions)) {
            l_check_json.StatementTransactions = fix_arrays_with_one_element(l_check_json.StatementTransactions)
        }
        l_check_json = i_json_builder.content?.StatementsInformationResponse?.StatementsInformationResult?.MonetaryTransactionList
        if (is_not_null(l_check_json?.MonetaryTransaction)) {
            l_check_json.MonetaryTransaction = fix_arrays_with_one_element(l_check_json.MonetaryTransaction)
        }
        String l_json_response_body = i_json_builder.toString()
        l_json_response_body = l_json_response_body.replace("{\"nil\":true}", "null")
        l_json_response_body = l_json_response_body.replace("{\"nil\": true}", "null")
        l_json_response_body = l_json_response_body.replace("[null]", "[]")
        l_json_response_body = l_json_response_body.replace("{\"nil\":\"true\"}", "null")
        l_json_response_body = l_json_response_body.replace("{\"nil\": \"true\"}", "null")
        l_json_response_body = l_json_response_body.replace("[\"null\"]", "[]")
        l_json_response_body = l_json_response_body.replace("\"***null_number***\"", "null")
        return new JSONObject(l_json_response_body).toString(new Integer(app_conf().GC_JSON_INDENT))
    }

    @I_black_box("error")
    @Override
    I_http_message convert_http_messages(Map<String, I_http_message> i_messages_by_name, String i_primary_service_name) {
        I_http_message l_result_http_message = new T_http_message()
        l_result_http_message.set_service_name(i_primary_service_name)
        l_result_http_message.set_method(app_conf().GC_REQUEST_METHOD_INTERNAL)
        l_result_http_message.set_header(GC_HEADER_NAME_USER_AGENT, app_conf().GC_CONTENT_TYPE_EXTERNAL)
        l_result_http_message.set_header(GC_HEADER_NAME_ACCEPT_LANGUAGE, app_conf().GC_ACCEPT_LANGUAGE_EXTERNAL)
        l_result_http_message.set_header(GC_HEADER_NAME_CONTENT_TYPE, app_conf().GC_CONTENT_TYPE_EXTERNAL)
        l_result_http_message.set_uri(app_conf().GC_ENDPOINT_INTERNAL)
        l_result_http_message.set_payload(post_process_json(i_messages_by_name.get(i_primary_service_name).get_json_builder()))
        l_result_http_message.set_payload_type(GC_PAYLOAD_TYPE_JSON)
        l_result_http_message.set_status(i_messages_by_name.get(i_primary_service_name).get_status())
        return l_result_http_message
    }

}
