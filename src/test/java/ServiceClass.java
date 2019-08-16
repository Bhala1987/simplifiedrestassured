import io.restassured.RestAssured;
import io.restassured.builder.MultiPartSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Getter;
import lombok.Setter;
import net.serenitybdd.rest.SerenityRest;

import java.io.File;


public abstract class ServiceClass {

    @Getter
    @Setter
    protected Response restResponse;
    RequestSpecification restRequest;
    ServiceRequest request;
    String endPoint;

    public ServiceClass(ServiceRequest request, String endPoint) {
        this.request = request;
        this.endPoint = endPoint;
    }

    public void invoke() {
//        restRequest = RestAssured.given()
        restRequest = SerenityRest.given()
                .log().all().baseUri(endPoint);

        restResponse = addHeadersAndExecuteRequest().then().log().all().extract().response();

        mapResponse();
    }

    public void invokeFormData(File file) {
//        restRequest = RestAssured.given()
        restRequest = SerenityRest.given()
                .multiPart(new MultiPartSpecBuilder(file).fileName(file.getName())
                        .controlName("file")
                        .build())
                .log().all().baseUri(endPoint);

        restResponse = addHeadersAndExecuteRequest().then().log().all().extract().response();

        mapResponse();
    }

    private Response addHeadersAndExecuteRequest() {

        // Add all the headers.
        request.getHeaders().get().forEach((key, value) -> restRequest.header(key, value));

        if (request.getRequestBody() != null) {
            restRequest.body(request.getRequestBody());
        }

        // Prepare a response (cyclomatic complexity)
        Response response;

        // Rest Assured 3 could do this much more succinctly.
        switch (request.getHttpMethod()) {
            case GET:
                response = restRequest.when().get();
                break;
            case PUT:
                response = restRequest.when().put();
                break;
            case POST:
                response = restRequest.when().post();
                break;
            case DELETE:
                response = restRequest.when().delete();
                break;
            default:
                response = restRequest.when().get().then().log().all().extract().response();
                break;
        }

        // Return the response.
        return response;
    }

    protected abstract void mapResponse();

    public int getStatusCode() {
        return restResponse.getStatusCode();
    }

    public String getExceptionMessage() {
        return restResponse.getBody().prettyPrint();
    }
}
