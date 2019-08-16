import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetServiceResponse {

    private Integer userId;
    private Integer id;
    private String title;
    private Boolean completed;
}
