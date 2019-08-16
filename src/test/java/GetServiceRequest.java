public class GetServiceRequest extends ServiceRequest {

    public GetServiceRequest(ServiceHeaders headers) {
        super(headers, HttpMethods.GET, null, null, null);
    }
}
