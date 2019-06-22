import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventParameter {

private Integer priority;
private String log_level = null;
private String source = null;

}
