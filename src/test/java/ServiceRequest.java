import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public abstract class ServiceRequest {

    ServiceHeaders headers;
    HttpMethods httpMethod;
    Map<String, String> queryParameters;
    Object requestBody;
    String pathParameters;

    public ServiceRequest(ServiceHeaders headers, HttpMethods httpMethod, String pathParameters, Map<String, String> queryParameters, Object requestBody) {
        this.headers = headers;
        this.httpMethod = httpMethod;
        this.pathParameters = pathParameters;
        this.queryParameters = queryParameters;
        this.requestBody = requestBody;
    }
}
