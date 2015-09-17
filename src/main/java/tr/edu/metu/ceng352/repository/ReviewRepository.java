package tr.edu.metu.ceng352.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tr.edu.metu.ceng352.model.App;
import tr.edu.metu.ceng352.model.Review;
import tr.edu.metu.ceng352.model.dbModel.AppReview;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Serhat CAN on 03.06.2015.
 */

@Repository
@Transactional(readOnly = true)
public class ReviewRepository {

    private static final String REVIEW_FOR_APP = "Select r.title, r.description, r.time_stamp, r.vote, u.first_name, u.last_name " +
            "from review r, user u, purchase p where r.app_id = ? and r.purchase_id = p.purchase_id and " +
            "p.user_id = u.user_id order by time_stamp desc";

    private static final String ALREADY_REVIEWED = "Select Count(*) as isReviewed from Review r, Purchase p " +
            "where  r.app_id = ? and r.purchase_id = p.purchase_id and p.user_id = ?";

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Review save(Review review) {
        entityManager.persist(review);
        return review;
    }

    public List<AppReview> getReviewForApp(Long appId) {
        Query query = entityManager.createNativeQuery(REVIEW_FOR_APP)
                .setParameter(1, appId);

        List<Object[]> resultList = query.getResultList();
        List<AppReview> appReviews = resultList.stream().map(this::mapToAppReview).collect(Collectors.toList());
        return appReviews;

    }

    public boolean isAlreadyReviewedLong (Long appId, Long userId) {
        Object o = entityManager.createNativeQuery(ALREADY_REVIEWED)
                .setParameter(1, appId)
                .setParameter(2, userId)
                .getSingleResult();

        BigInteger count = (BigInteger) o;
        if(count.intValue() > 0) {
            return true;
        }
        return false;
    }

    private AppReview mapToAppReview(Object[] o) {
        AppReview appReview = new AppReview();
        appReview.setTitle((String) o[0]);
        appReview.setDescription((String) o[1]);
        appReview.setTimestamp((Timestamp) o[2]);
        appReview.setVote((int) o[3]);
        appReview.setFirstName((String) o[4]);
        appReview.setLastName((String) o[5]);
        return appReview;
    }
}
