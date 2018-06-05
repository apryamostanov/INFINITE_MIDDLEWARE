import base.T_middleware_base_6_util
import implementation.T_http_message
import org.junit.Test

class T_tests_3 {


    @Test
    void test_01() {

        T_middleware_base_6_util.init_app_context("C:\\TPN\\conf\\WORKER\\freeway_worker_normal.conf")

        System.out.println(T_middleware_base_6_util.app_conf().GC_TEST_HTTP_PLAINTEXT_MODE)
        System.out.println(T_middleware_base_6_util.GC_TRUE_STRING)
        System.out.println(T_middleware_base_6_util.app_conf().GC_TEST_HTTP_PLAINTEXT_MODE == T_middleware_base_6_util.GC_TRUE_STRING)
        System.out.println(T_middleware_base_6_util.app_conf().get_conf_file_name())
        T_http_message z = new T_http_message()
        z.set_uri("https://ckpukg1rhf.execute-api.ap-southeast-1.amazonaws.com/uat/api/wirecard/")
        z.set_payload_type("json")
        z.set_payload("""{
  "TransactionNotificationRequest": {
    "product": {
      "productID": 2272,
      "programManager": "RHB",
      "productName": "039-RHB TRAVEL CARD",
      "productCategory": "Prepaid Card",
      "subProductType": "Consumer Travel Program"
    },
    "fees": "",
    "businessAccount": {
      "sourceBusinessName": "",
      "businessName": "",
      "businessAccountNumber": null,
      "sourceBusinessAccountNumber": null
    },
    "fleet_104_data": {
      "VAT_Rate": "0",
      "Net_NonFuel_Price": "0",
      "Other_Tax": "0",
      "Quantity": "0",
      "National_Tax": "0",
      "Net_Fuel_Price": "0",
      "Gross_Fuel_Price": "0",
      "Misc_NonFuelTax": "0",
      "Local_Tax": "0",
      "Gross_NonFuel_Price": "0",
      "Unit_Cost": "0",
      "Misc_FuelTax": "0",
      "Summ_Comm_Code": "0"
    },
    "fleet_125_data": {
      "expNonFuelCodeCost02": "0",
      "expNonFuelCodeQty04": "0",
      "expNonFuelCodeQty03": "0",
      "expNonFuelCodeCost03": "0",
      "expNonFuelCodeQty02": "0",
      "expNonFuelCodeQty01": "0",
      "expNonFuelCodeCost01": "0",
      "expNonFuelCodeCost06": "0",
      "expNonFuelCodeCost07": "0",
      "expNonFuelCodeCost04": "0",
      "expNonFuelCodeCost05": "0",
      "expNonFuelCodeCost08": "0",
      "expNonFuelCodeQty08": "0",
      "expNonFuelCodeQty07": "0",
      "expNonFuelCodeQty06": "0",
      "expNonFuelCodeQty05": "0"
    },
    "network": {
      "networkName": "VS",
      "networkProcessingCode": 0,
      "networkTransactionId": null,
      "networkRRN": 814110024198,
      "networkResponseCode": "00",
      "networkSTAN": 24198
    },
    "amounts": {
      "transactionCurrency": "SGD",
      "transactionAmountInt": 64,
      "sourcePostedAmountDec": null,
      "billingAmountInt": 64,
      "postedAmountDec": 0,
      "additionalAmountDec": null,
      "sourcePostedAmountInt": null,
      "additionalAmountCurrency": "",
      "sourcePostedCurrency": "",
      "postedCurrency": "SGD",
      "billingCurrency": "SGD",
      "billingAmountDec": 0,
      "postedAmountInt": 64,
      "additionalAmountInt": null,
      "transactionAmountDec": 0
    },
    "pos_merchant": {
      "storeNumber": "",
      "chipTransactionFlag": null,
      "posConditionCode": 0,
      "terminalId": "VL28",
      "chipCondition": 0,
      "merchantName": "CHIMES CLOTHING",
      "acquirerInstitutionIDCode": "12345678901",
      "cardAcceptorIDCode": "SCOTIABANK",
      "cardAcceptorterminalIDCode": "VL28",
      "merchantId": "SCOTIABANK",
      "merchantCountry": "SG",
      "acquirerId": null,
      "acquirerCountry": "SG",
      "merchantType": "5541",
      "paymentIndicator": "",
      "merchantCode": "5541",
      "cardholderId": 0,
      "merchantCity": "united States",
      "terminalType": "P",
      "nationalPOSGeographicData": "",
      "terminalCategory": 0,
      "posPINCaptureCode": "",
      "merchantAddress": "CHIMES CLOTHING          united StatesSG",
      "terminalEntry": 5,
      "posEntryMode": 5
    },
    "additionalData": {
      "transactionType": "Settled",
      "lastCreditDate": "21-May-2018",
      "lastCreditAmount": 1000,
      "lastTransactionDate": "21-May-2018 18:22:11 PM",
      "currentBalance": 2591,
      "settleAmount": 64,
      "settleCurrency": "SGD",
      "isDeclined": "No",
      "availableBalance": 2591
    },
    "fleet_48_data": "",
    "card": {
      "sourceCardNumber": "",
      "sourceProxyNumber": 0,
      "proxyNumber": 60075,
      "cardNumber": "407092XXXXXX7407",
      "cardSequenceNumber": 3
    },
    "account": {
      "sourceAccountBalanceInt": null,
      "AccountCurrency": "SGD",
      "AccountBalanceDec": 0,
      "AccountBalanceInt": 2591,
      "sourceAccountCurrency": "",
      "sourceAccountNumber": 0,
      "sourceAccountBalanceDec": null,
      "AccountNumber": 1003000000000104763
    },
    "transaction": {
      "postingTransactionSource": "Visa SMS",
      "messageTypeIdentifier": "0200",
      "transactionPostingDate": "21-May-2018",
      "transactionDescription": "Retail Purchase Domestic",
      "transactionCode": "9954",
      "transactionTimeStamp": "21-May-2018 14:22:11 PM",
      "transactionLocalDate": "21-May-2018",
      "transactionLocalTime": "14:22:11",
      "transactionResponseCode": "00",
      "transactionId": 27843858,
      "originalTransactionSource": "Visa SMS",
      "postingFlag": 0,
      "transactionPostingTime": "18:22:11",
      "transactionOriginalId": 0,
      "debitCreditFlag": "-",
      "transactionLifecycleState": "O",
      "postingReference": "Approved",
      "logicModule": "95"
    }
  }
}
""")
        z.set_headers(["Content-Type":"application/json"])
        z.set_method("POST")
        T_http_message q = new T_http_message()
        T_middleware_base_6_util.aws_request_with_v4_sign(z, q)
        System.out.println(q)
    }


}
