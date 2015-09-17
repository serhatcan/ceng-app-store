/**
 * Created by Serhat CAN
 */
package tr.edu.metu.ceng352.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tr.edu.metu.ceng352.form.AppForm;
import tr.edu.metu.ceng352.form.ReviewForm;
import tr.edu.metu.ceng352.model.*;
import tr.edu.metu.ceng352.model.dbModel.AppReview;
import tr.edu.metu.ceng352.model.dbModel.MainApp;
import tr.edu.metu.ceng352.repository.OsVersionRepository;
import tr.edu.metu.ceng352.service.AppService;
import tr.edu.metu.ceng352.service.CurrentUser;
import tr.edu.metu.ceng352.service.ReviewService;
import tr.edu.metu.ceng352.service.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by Serhat CAN on 01.06.2015.
 */
@Controller
public class AppController extends MainController{

    @Autowired
    AppService appService;

    @Autowired
    UserService userService;

    @Autowired
    CurrentUser currentUser;

    @Autowired
    ReviewService reviewService;

    @Autowired
    OsVersionRepository osVersionRepository;

    @RequestMapping(value = {"/app/{id}/*", "/app/{id}"}, method = RequestMethod.GET)
    public String getApp(@PathVariable("id") int id, Model model) {
        MainApp app = appService.getAppById(id);
        model.addAttribute("app", app);
        User user = currentUser.getUser();
        Double currentMoney = userService.getCurrentUsersMoney(user.getId());
        Boolean hasEnoughMoney = hasEnoughMoney(currentMoney, app.getPrice());
        model.addAttribute("hasEnoughMoney", hasEnoughMoney);
        model.addAttribute("usersMoney", currentMoney);
        Boolean isAlreadyDownloaded = appService.isAlreadyDownloaded(user.getId(), (long) id);
        model.addAttribute("isAlreadyDownloaded", isAlreadyDownloaded);

        Boolean isAlreadyReviewed = reviewService.isAlreadyReviewed(user.getId(), (long) app.getId());
        model.addAttribute("isAlreadyReviewed", isAlreadyReviewed);

        List<AppReview> reviews = reviewService.getReviewForApp((long)app.getId());
        model.addAttribute("reviews", reviews);

        model.addAttribute(new ReviewForm());

        return "app/app";
    }

    @RequestMapping(value = "/purchase/{id}", method = RequestMethod.GET)
    public String purchaseApp(@PathVariable("id") Long appId, RedirectAttributes ra){
        Boolean result = appService.purchase(currentUser.getUser(), appId.longValue());

        return "redirect:/app/{id}";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile (Model model){
        User user = currentUser.getUser();
        List<Purchase> purchases = appService.downloadedApplications(user.getId());
        List<App> apps = purchases.stream().map(Purchase::getApp).collect(Collectors.toList());
        model.addAttribute("apps", apps);

        return "home/homeSignedIn";
    }

    @RequestMapping(value = "/review", method = RequestMethod.POST)
    public String review(@Valid @ModelAttribute ReviewForm reviewForm, Errors errors, RedirectAttributes ra) {
        Long appId = reviewForm.getAppId();
        /*if (errors.hasErrors()) {
            return "redirect:/app/{id}";
        }*/
        String returnValue = "redirect:/app/" + appId;

        User user = currentUser.getUser();
        reviewService.review(user.getId(), appId, reviewForm.getTitle(), reviewForm.getDescription(), reviewForm.getVote());
        return returnValue;
    }

    @PreAuthorize("hasRole('ROLE_DEVELOPER')")
    @RequestMapping(value = "/developer")
    public String developerPage(Model model) {
        model.addAttribute(new AppForm());
        return "developer/developer";
    }

    @PreAuthorize("hasRole('ROLE_DEVELOPER')")
    @RequestMapping(value = "/developer", method = RequestMethod.POST)
    public String developerPageCreateApp(@Valid @ModelAttribute AppForm appForm, Errors errors, RedirectAttributes ra) {
        if (errors.hasErrors()) {
            return "developer/developer";
        }

        User user = currentUser.getUser();
        Category category = categoryRepository.findById(appForm.getCategoryId());
        OsVersion osVersion = osVersionRepository.findById(4l);
        App app = new App(appForm.getTitle(), appForm.getDescription(), appForm.getPrice(), user, category, osVersion);
        app = appService.save(app);
        String redirectURL = "redirect:/app/" + app.getId();
        return redirectURL;
    }

    private Boolean hasEnoughMoney(Double usersMoney, Double price) {
        if(usersMoney > price) {
            return true;
        }
        return  false;
    }

}
