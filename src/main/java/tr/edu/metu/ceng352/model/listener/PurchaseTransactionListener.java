package tr.edu.metu.ceng352.model.listener;

import tr.edu.metu.ceng352.model.Purchase;
import tr.edu.metu.ceng352.model.PurchaseTransaction;

import javax.persistence.PrePersist;
import java.sql.Date;
import java.time.Instant;

/**
 * Created by Serhat CAN on 31.05.2015.
 */

public class PurchaseTransactionListener {

    @PrePersist
    protected void prePersist(PurchaseTransaction entity) {
        Instant instant = Instant.now();
        entity.setTimeStamp(Date.from(instant));
    }
}
