public class GetService extends ServiceClass {

    private GetServiceResponse serviceResponse;

    public GetService(ServiceRequest request, String endPoint) {
        super(request, endPoint);
    }

    protected void mapResponse() {
        serviceResponse = restResponse.as(GetServiceResponse.class);
    }

    public GetServiceResponse getResponse() {
        return serviceResponse;
    }
}
