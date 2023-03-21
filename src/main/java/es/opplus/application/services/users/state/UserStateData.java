package es.opplus.application.services.users.state;

import es.opplus.application.services.broadcaster.BroadcastData;
import lombok.Data;

@Data
public class UserStateData implements BroadcastData {
    private UserState userState;

    public UserStateData(UserState userState) {
        this.userState = userState;
    }
}
