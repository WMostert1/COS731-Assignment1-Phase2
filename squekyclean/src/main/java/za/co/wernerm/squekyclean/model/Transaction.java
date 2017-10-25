package za.co.wernerm.squekyclean.model;

import org.joda.time.DateTime;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by werner on 2017/10/24.
 */

@Entity
@Table(name = "Transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "carId")
    private Car car;

    @JoinColumn(name = "transactionTypeId")
    private int transactionTypeId;

    private Timestamp tmstamp;

    private String description;

    public Transaction() {
    }

    public Transaction(long id, Car car, int transactionTypeId, Timestamp tmstamp, String description) {
        this.id = id;
        this.car = car;
        this.transactionTypeId = transactionTypeId;
        this.tmstamp = tmstamp;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Timestamp getTmstamp() {
        return tmstamp;
    }

    public void setTmstamp(Timestamp tmstamp) {
        this.tmstamp = tmstamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTransactionTypeId() {
        return transactionTypeId;
    }

    public void setTransactionTypeId(int transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
    }
}
