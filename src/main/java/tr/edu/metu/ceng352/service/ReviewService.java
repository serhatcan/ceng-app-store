package tr.edu.metu.ceng352.service;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.edu.metu.ceng352.model.*;
import tr.edu.metu.ceng352.model.dbModel.AppReview;
import tr.edu.metu.ceng352.model.dbModel.HomeApp;
import tr.edu.metu.ceng352.model.dbModel.MainApp;
import tr.edu.metu.ceng352.repository.*;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Serhat CAN on 31.05.2015.
 */

@Service
public class ReviewService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AppRepository appRepository;

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    PurchaseTransactionRepository purchaseTransactionRepository;

    @Autowired
    ReviewRepository reviewRepository;


    public  List<AppReview> getReviewForApp(Long appId){
        return reviewRepository.getReviewForApp(appId);
    }

    public Review review(Long userId, Long appId, String title, String description, int vote) {
        Purchase purchase = purchaseRepository.getPurchaseForUserAndApp(userId, appId);
        App app = appRepository.findById(appId);
        Review review = new Review(purchase, app, title, description, vote);
        review = reviewRepository.save(review);
        return review;
    }

    public Boolean isAlreadyReviewed(Long userId, Long appId) {
        return reviewRepository.isAlreadyReviewedLong(appId, userId);
    }

}
