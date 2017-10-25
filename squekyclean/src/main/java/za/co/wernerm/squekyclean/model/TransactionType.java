package za.co.wernerm.squekyclean.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by werner on 2017/10/24.
 *
 INSERT INTO TransactionTypes VALUES(1,'Normal Wash');
 INSERT INTO TransactionTypes VALUES(2,'Full Wash');
 INSERT INTO TransactionTypes VALUES(3,'Premium Wash');
 INSERT INTO TransactionTypes VALUES(4,'Wash and Go');
 INSERT INTO TransactionTypes VALUES(5,'Full Wash + Polish');
 INSERT INTO TransactionTypes VALUES(6,'Normal Wash + Polish');
 INSERT INTO TransactionTypes VALUES(7,'Premium Wash + Polish');
 INSERT INTO TransactionTypes VALUES(8,'Wash and Go + Dry');
 INSERT INTO TransactionTypes VALUES(9,'Full Valet');
 INSERT INTO TransactionTypes VALUES(10,'Full Valet + Polish');
 */
public enum TransactionType {
    NORMAL_WASH(1),
    FULL_WASH(2),
    PREMIUM_WASH(3),
    WASH_AND_GO(4),
    FULL_WASH_POLISH(5),
    NORMAL_WASH_POLISH(6),
    PREMIUM_WASH_POLISH(7),
    WASH_AND_GO_DRY(8),
    FULL_VALET(9),
    FULL_VALET_POLISH(10);

    private int value;
    private static Map<Integer,TransactionType> map = new HashMap<>();

    TransactionType(int value) {
        this.value = value;
    }

    static {
        for (TransactionType pageType : TransactionType.values()) {
            map.put(pageType.value, pageType);
        }
    }

    public static TransactionType valueOf(int val) {
        return map.get(val);
    }

    public int getValue() {
        return value;
    }
}
