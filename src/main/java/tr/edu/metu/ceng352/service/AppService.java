package tr.edu.metu.ceng352.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.edu.metu.ceng352.model.App;
import tr.edu.metu.ceng352.model.Purchase;
import tr.edu.metu.ceng352.model.PurchaseTransaction;
import tr.edu.metu.ceng352.model.User;
import tr.edu.metu.ceng352.model.dbModel.HomeApp;
import tr.edu.metu.ceng352.model.dbModel.MainApp;
import tr.edu.metu.ceng352.repository.AppRepository;
import tr.edu.metu.ceng352.repository.PurchaseRepository;
import tr.edu.metu.ceng352.repository.PurchaseTransactionRepository;
import tr.edu.metu.ceng352.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Serhat CAN on 31.05.2015.
 */

@Service
public class AppService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AppRepository appRepository;

    @Autowired
    PurchaseRepository purchaseRepository;

    @Autowired
    PurchaseTransactionRepository purchaseTransactionRepository;

    public App save(App app) {
        return appRepository.save(app);
    }

    public List<HomeApp> getHomePageApps(int limit) {
        return appRepository.getHomePageApps(limit);
    }

    public List<HomeApp> getHomePageAppsForCategory(int category, int limit) {
        return appRepository.getHomePageAppsForCategory(category, limit);
    }

    public MainApp getAppById(int id) {
        return appRepository.getAppPage(id);
    }

    @Transactional
    public boolean purchase(User user, Long appId) {
        appRepository.increaseDownloadNum(appId); // increase download num

        // save purchase transaction
        App app = appRepository.findById(appId);
        Purchase purchase = new Purchase(user, app);
        purchase = purchaseRepository.save(purchase);

        // save as purchase transaction for logging purposes
        PurchaseTransaction purchaseTransaction = new PurchaseTransaction(user, purchase, app.getPrice());
        purchaseTransactionRepository.save(purchaseTransaction);

        // withdraw money from current user
        userRepository.withdrawMoney(user.getId(), app.getPrice());

        // add money to developers account
        userRepository.addMoney(app.getUser().getId(), app.getPrice());

        return false;
    }

    public  List<Purchase> downloadedApplications(Long userId) {
        return purchaseRepository.downloaded(userId);
    }

    public Boolean isAlreadyDownloaded(Long userId, Long appId) {
        return purchaseRepository.isAlreadyDownloaded(userId, appId);
    }


}
