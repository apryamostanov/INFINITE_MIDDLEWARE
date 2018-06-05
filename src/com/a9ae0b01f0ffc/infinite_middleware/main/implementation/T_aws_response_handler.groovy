package implementation

import com.amazonaws.AmazonWebServiceResponse
import com.amazonaws.http.HttpResponse
import com.amazonaws.http.HttpResponseHandler
import com.amazonaws.util.IOUtils

class T_aws_response_handler implements HttpResponseHandler<AmazonWebServiceResponse<String>> {

    @Override
    AmazonWebServiceResponse<String> handle(HttpResponse response) throws IOException {

        AmazonWebServiceResponse<String> awsResponse = new AmazonWebServiceResponse<>()

        //putting response string in the result, available outside the handler
        awsResponse.setResult((String) IOUtils.toString(response.getContent()))

        return awsResponse
    }

    @Override
    boolean needsConnectionLeftOpen() {
        return false
    }



}