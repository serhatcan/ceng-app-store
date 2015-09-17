package tr.edu.metu.ceng352.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tr.edu.metu.ceng352.model.App;
import tr.edu.metu.ceng352.model.Purchase;
import tr.edu.metu.ceng352.model.dbModel.HomeApp;
import tr.edu.metu.ceng352.model.dbModel.MainApp;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Serhat CAN on 30.05.2015.
 */
@Repository
@Transactional(readOnly = true)
public class PurchaseRepository {

    private static final String ALREADY_DOWNLOADED = "Select Count(*) as isDownloaded from purchase p " +
            "where  p.user_id=? and p.app_id=?";

    private static final String DOWNLOADED = "Select * from Purchase where user_id=?";

    private static final String FIND_FOR_USER_AND_APP = "Select * from Purchase where user_id=? and app_id=?";

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Purchase save(Purchase purchase) {
        entityManager.persist(purchase);
        return purchase;
    }



    public boolean isAlreadyDownloaded(Long userId, Long appId) {
        Object o = entityManager.createNativeQuery(ALREADY_DOWNLOADED)
                .setParameter(1, userId)
                .setParameter(2, appId)
                .getSingleResult();

        BigInteger count = (BigInteger) o;
        if(count.intValue() > 0) {
            return true;
        }
        return false;
    }

    public List<Purchase> downloaded(Long userId) {
        List<Purchase> purchases = entityManager.createNativeQuery(DOWNLOADED, Purchase.class)
                .setParameter(1, userId)
                .getResultList();

        return purchases;
    }

    public Purchase getPurchaseForUserAndApp(Long userId, Long appId) {
        List<Purchase> purchases = entityManager.createNativeQuery(FIND_FOR_USER_AND_APP, Purchase.class)
                .setParameter(1, userId)
                .setParameter(2, appId)
                .getResultList();
        if(purchases.size()>0) {
            return purchases.get(0);
        }
        return null;
    }

}