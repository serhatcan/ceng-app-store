package tr.edu.metu.ceng352.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tr.edu.metu.ceng352.model.Purchase;
import tr.edu.metu.ceng352.model.PurchaseTransaction;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Serhat CAN on 30.05.2015.
 */
@Repository
@Transactional(readOnly = true)
public class PurchaseTransactionRepository {


    private static final String FIND_ALL = "select * from purchase_transaction pt where pt.user_id=?";

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public PurchaseTransaction save(PurchaseTransaction transaction) {
        entityManager.persist(transaction);
        return transaction;
    }

    public List<PurchaseTransaction> findAll(Long userId) {
        return entityManager.createNativeQuery(FIND_ALL, PurchaseTransaction.class)
                .setParameter(1, userId)
                .getResultList();
    }

}