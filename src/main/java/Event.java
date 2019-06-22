import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Event {

    private String event_id = null;
    private String event_date = null;
    private EventParameter parameter;


}


