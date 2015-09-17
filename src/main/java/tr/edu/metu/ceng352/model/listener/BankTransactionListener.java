package tr.edu.metu.ceng352.model.listener;

import tr.edu.metu.ceng352.model.BankTransaction;
import tr.edu.metu.ceng352.model.PurchaseTransaction;

import javax.persistence.PrePersist;
import java.sql.Date;
import java.time.Instant;

/**
 * Created by Serhat CAN on 31.05.2015.
 */

public class BankTransactionListener {

    @PrePersist
    protected void prePersist(BankTransaction entity) {
        Instant instant = Instant.now();
        entity.setTimeStamp(Date.from(instant));
    }
}
