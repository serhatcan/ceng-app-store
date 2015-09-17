/**
 * Created by Serhat CAN
 */
package tr.edu.metu.ceng352.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import tr.edu.metu.ceng352.model.Role;
import tr.edu.metu.ceng352.model.User;
import tr.edu.metu.ceng352.repository.UserRepository;
import tr.edu.metu.ceng352.service.CurrentUser;
import tr.edu.metu.ceng352.service.UserService;

@Controller
@Secured("ROLE_USER")
class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    CurrentUser currentUser;

    @Autowired
    UserService userService;

    @RequestMapping(value = "account/current", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public User accounts(Principal principal) {
        Assert.notNull(principal);
        System.out.println(principal.getName());
        return userRepository.findByEmail(principal.getName());
    }

    @RequestMapping(value = "/becomeDeveloper", method = RequestMethod.GET)
    public String becomeDeveloper(){
        userService.becomeDeveloper(currentUser.getUser().getId());
        currentUser.getUser().setRole(Role.ROLE_DEVELOPER);
        return "redirect:/";
    }
}
