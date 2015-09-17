package tr.edu.metu.ceng352.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tr.edu.metu.ceng352.model.OsVersion;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

/**
 * Created by Serhat CAN on 30.05.2015.
 */
@Repository
@Transactional(readOnly = true)
public class OsVersionRepository {

    private static final String FIND_BY_ID = "select os from OsVersion os where os.id = :id";

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public OsVersion save(OsVersion osVersion) {
        entityManager.persist(osVersion);
        return osVersion;
    }

    public OsVersion findById(Long id) {
        try {
            return entityManager.createQuery(FIND_BY_ID, OsVersion.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (PersistenceException e) {
            return null;
        }
    }

}
