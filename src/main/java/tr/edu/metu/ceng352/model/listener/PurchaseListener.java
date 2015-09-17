package tr.edu.metu.ceng352.model.listener;

import tr.edu.metu.ceng352.model.App;
import tr.edu.metu.ceng352.model.Purchase;

import javax.persistence.PrePersist;
import java.sql.Date;
import java.time.Instant;

/**
 * Created by Serhat CAN on 31.05.2015.
 */

public class PurchaseListener {

    @PrePersist
    protected void prePersist(Purchase entity) {
        Instant instant = Instant.now();
        entity.setTimeStamp(Date.from(instant));
    }
}
