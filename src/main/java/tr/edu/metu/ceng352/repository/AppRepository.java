package tr.edu.metu.ceng352.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tr.edu.metu.ceng352.model.App;
import tr.edu.metu.ceng352.model.dbModel.HomeApp;
import tr.edu.metu.ceng352.model.dbModel.MainApp;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Serhat CAN on 30.05.2015.
 */
@Repository
@Transactional(readOnly = true)
public class AppRepository {

    private static final String FIND_BY_ID = "select a from App a where a.id = :id";

    private static final String HOME_PAGE_APPS = "Select app_id as id, title, price" +
            " from app where is_approved = true order by download_num desc";

    private static final String HOME_PAGE_APPS_FOR_CATEGORY = "Select app_id as id, title, price" +
            " from app where is_approved = true and category_id = ?" +
            " order by download_num desc";

    private static final String APP_MAIN = "Select a.app_id, a.title as title, a.description, " +
            "a.price, a.time_stamp, a.updated_time, AVG(r.vote) as vote, os.os_name, u.username, cat.category_id, cat.title as categoryTitle " +
            "from app a, review r, os_version os, user u, category cat " +
            "where a.app_id = ? and a.app_id = r.app_id and a.is_approved = true and os.os_version_id = a.os_version_id and " +
            "a.user_id = u.user_id and a.category_id = cat.category_id;";

    private static String INCREASE_DOWNLOAD_NUM = "Update app set download_num = download_num+1 where app_id=?;";


    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public App save(App app) {
        entityManager.persist(app);
        return app;
    }

    public App findById(Long id) {
        try {
            return entityManager.createQuery(FIND_BY_ID, App.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (PersistenceException e) {
            return null;
        }
    }

    public List<HomeApp> getHomePageApps(int limit) {
        Query query = entityManager.createNativeQuery(HOME_PAGE_APPS)
                .setMaxResults(limit);
        List<Object[]> resultList=query.getResultList();
        List<HomeApp> homeApps = resultList.stream().map(this::mapToHomeApp).collect(Collectors.toList());
        return homeApps;
    }

    public List<HomeApp> getHomePageAppsForCategory(int category_id, int limit) {

        Query query = entityManager.createNativeQuery(HOME_PAGE_APPS_FOR_CATEGORY)
                .setParameter(1, category_id)
                .setMaxResults(limit);

        List<Object[]> resultList=query.getResultList();
        List<HomeApp> homeApps = resultList.stream().map(this::mapToHomeApp).collect(Collectors.toList());
        return homeApps ;
    }

    @Transactional
    public boolean increaseDownloadNum(Long id) {
        entityManager.createNativeQuery(INCREASE_DOWNLOAD_NUM)
                .setParameter(1, id)
                .executeUpdate();
        return true;
    }


    private HomeApp mapToHomeApp(Object[] o) {
        HomeApp homeApp = new HomeApp();
        homeApp.setId((int) o[0]);
        homeApp.setTitle((String) o[1]);
        BigDecimal price = (BigDecimal) o[2];
        homeApp.setPrice(price.doubleValue());
        return homeApp;
    }


    public MainApp getAppPage(int app_id) {
        Query query = entityManager.createNativeQuery(APP_MAIN)
                .setParameter(1, app_id);
        List<Object[]> apps = query.getResultList();
        Object[] o = apps.get(0);
        if(o[0]==null || o[1]==null || o[2]==null || o[3]==null || o[4]==null || o[8]==null) {
            return null;
        }
        MainApp mainApp = new MainApp();
        mainApp.setId((int) o[0]);
        mainApp.setTitle((String) o[1]);
        mainApp.setDescription((String) o[2]);
        BigDecimal price = (BigDecimal) o[3];
        mainApp.setPrice(price.doubleValue());
        mainApp.setTimestamp((Timestamp) o[4]);
        if(o[5] != null) {
            mainApp.setUpdatedTime((Timestamp) o[5]);
        }
        if(o[6] != null) {
            BigDecimal vote = (BigDecimal) o[6];
            mainApp.setVote(vote.doubleValue());
        }
        if(o[7] != null) {
            mainApp.setOsName((String) o[7]);
        }
        mainApp.setUsername((String) o[8]);
        mainApp.setCategoryId((int) o[9]);
        mainApp.setCategoryTitle((String) o[10]);
        return mainApp;
    }




   /* -- gets all the reviews for the given app_id by ordering desc to timestamp
    -- optional: limit might be used for pagination
    Select title, description, timestamp, vote
    from review
    where app_id = ?
    order by timestamp desc

    -- users can review the application if they have purchased it before
    Select * from review where user_id=? and app_id=?;

    Insert into Review(purchase_id, title, description, vote, timestamp)
    values( ?, 'title', 'description', 4, sysdate);

    -- users can delete reviews
    Delete from review where app_id=? and purchase_id=?*/

}
