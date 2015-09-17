/**
 * Created by Serhat CAN
 */
package tr.edu.metu.ceng352.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import tr.edu.metu.ceng352.model.dbModel.HomeApp;
import tr.edu.metu.ceng352.service.AppService;

@Controller
public class HomeController extends MainController{


	@Autowired
	AppService appService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Principal principal, Model model) {
		List<HomeApp> homeApps = appService.getHomePageApps(20);
		if(principal != null) {
			model.addAttribute("apps", homeApps);
			return "home/homeSignedIn";
		}
		return "home/homeNotSignedIn";
	}

	@RequestMapping(value = "/category/{id}*//*", method = RequestMethod.GET)
	public String getAppsForCategory(@PathVariable("id") int id, Model model) {
		model.addAttribute("apps", appService.getHomePageAppsForCategory(id, 20));
		model.addAttribute("activeCategoryId", id);
		return "home/homeSignedIn";
	}

}
