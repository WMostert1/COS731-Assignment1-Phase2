package za.co.wernerm.squekyclean.dto;

import za.co.wernerm.squekyclean.model.TransactionType;

import java.util.Date;

/**
 * Created by werner on 2017/10/24.
 */
public class CreateTransactionDTO extends TransactionDTO{
    private String carRegistration;

    public CreateTransactionDTO(String carRegistration, TransactionType transactionType, Date date, String description) {
        super(transactionType, date, description);

        this.carRegistration = carRegistration;
    }

    public CreateTransactionDTO() {

    }


    public String getCarRegistration() {
        return carRegistration;
    }

    public void setCarRegistration(String carRegistration) {
        this.carRegistration = carRegistration;
    }
}
