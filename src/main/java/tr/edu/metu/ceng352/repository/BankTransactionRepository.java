package tr.edu.metu.ceng352.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tr.edu.metu.ceng352.model.BankTransaction;
import tr.edu.metu.ceng352.model.PurchaseTransaction;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Serhat CAN on 30.05.2015.
 */
@Repository
@Transactional(readOnly = true)
public class BankTransactionRepository {

    private static final String FIND_ALL = "select * from bank_transaction bt where user_id=?";

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public BankTransaction save(BankTransaction transaction) {
        entityManager.persist(transaction);
        return transaction;
    }

    public List<BankTransaction> findAll(Long userId) {
        return entityManager.createNativeQuery(FIND_ALL, BankTransaction.class)
                .setParameter(1, userId)
                .getResultList();
    }

}