package tr.edu.metu.ceng352.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import tr.edu.metu.ceng352.model.User;

/**
 * Created by Serhat CAN on 03.06.2015.
 */

@Component
@Scope(value="session", proxyMode= ScopedProxyMode.TARGET_CLASS)
public class CurrentUser {

    User user = null;

    @Autowired
    UserService userService;


    public User getUser() {
        if( user == null ) {
            user = userService.getCurrentUser();
        }
        return user;
    }
}
