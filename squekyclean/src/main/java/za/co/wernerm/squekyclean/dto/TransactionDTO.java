package za.co.wernerm.squekyclean.dto;

import za.co.wernerm.squekyclean.model.Transaction;
import za.co.wernerm.squekyclean.model.TransactionType;

import java.util.Date;

/**
 * Created by werner on 2017/10/24.
 */
public class TransactionDTO {
    private TransactionType transactionType;
    private Date date;
    private String description;

    public TransactionDTO() {
    }

    public TransactionDTO(TransactionType transactionType, Date date, String description) {
        this.transactionType = transactionType;
        this.date = date;
        this.description = description;
    }

    public static TransactionDTO map(Transaction transaction){
        TransactionDTO dto = new TransactionDTO();
        dto.setDate(transaction.getTmstamp());
        dto.setDescription(transaction.getDescription());
        dto.setTransactionType(TransactionType.valueOf(transaction.getTransactionTypeId()));
        return dto;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
