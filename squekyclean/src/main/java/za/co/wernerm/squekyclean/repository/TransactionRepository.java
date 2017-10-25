package za.co.wernerm.squekyclean.repository;

import org.springframework.data.repository.CrudRepository;
import za.co.wernerm.squekyclean.model.Car;
import za.co.wernerm.squekyclean.model.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
}
