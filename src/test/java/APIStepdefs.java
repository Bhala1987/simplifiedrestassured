import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class APIStepdefs {

    APIService apiService = new APIService();

    @Given("^hit the '(.*)' uri$")
    public void hitTheUri(String endPointUri) {
        apiService.getService(endPointUri);
    }

    @Then("^the response should be (\\d+)$")
    public void theResponseShouldBe(int status) {
        apiService.getServiceStatus(status);
    }


}
