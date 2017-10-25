package za.co.wernerm.squekyclean.dto;

import za.co.wernerm.squekyclean.model.Car;
import za.co.wernerm.squekyclean.model.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by werner on 2017/10/24.
 */
public class CarDTO {
    private String registrationNumber;
    private List<TransactionDTO> transactions = new ArrayList<>();

    public CarDTO() {
    }

    public CarDTO(String registrationNumber, List<TransactionDTO> transactions) {
        this.registrationNumber = registrationNumber;
        this.transactions = transactions;
    }

    public static CarDTO map(Car car){
        List<TransactionDTO> transactions = new ArrayList<>();
        CarDTO dto = new CarDTO();
        dto.setRegistrationNumber(car.getRegistrationNumber());
        for(Transaction transaction : car.getTransactions())
            transactions.add(TransactionDTO.map(transaction));
        dto.setTransactions(transactions);
        return dto;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public List<TransactionDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDTO> transactions) {
        this.transactions = transactions;
    }
}
