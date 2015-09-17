/**
 * Created by Serhat CAN
 */
package tr.edu.metu.ceng352.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import tr.edu.metu.ceng352.form.SignupForm;
import tr.edu.metu.ceng352.support.web.*;
import tr.edu.metu.ceng352.model.User;
import tr.edu.metu.ceng352.repository.UserRepository;
import tr.edu.metu.ceng352.service.UserService;

@Controller
public class SignupController {

    private static final String SIGNUP_VIEW_NAME = "signup/signup";

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "signup")
	public String signup(Model model) {
		model.addAttribute(new SignupForm());
		return SIGNUP_VIEW_NAME;
	}
	
	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public String signup(@Valid @ModelAttribute SignupForm signupForm, Errors errors, RedirectAttributes ra) {
		if (errors.hasErrors()) {
			return SIGNUP_VIEW_NAME;
		}
		User user = userRepository.save(signupForm.createAccount());
		userService.signin(user);
        // see /WEB-INF/i18n/messages.properties and /WEB-INF/views/homeSignedIn.html
        MessageHelper.addSuccessAttribute(ra, "signup.success");
		return "redirect:/";
	}
}
