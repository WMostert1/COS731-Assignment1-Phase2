package za.co.wernerm.squekyclean.api;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import za.co.wernerm.squekyclean.dto.CreatePostDTO;
import za.co.wernerm.squekyclean.dto.CreateTransactionDTO;
import za.co.wernerm.squekyclean.model.Car;
import za.co.wernerm.squekyclean.model.Post;
import za.co.wernerm.squekyclean.model.Thread;
import za.co.wernerm.squekyclean.model.Transaction;
import za.co.wernerm.squekyclean.repository.CarRepository;
import za.co.wernerm.squekyclean.repository.TransactionRepository;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

/**
 * Created by werner on 2017/10/24.
 */

@Controller
@RequestMapping(path="api/transaction")
public class TransactionController {
    @Autowired
    CarRepository carRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @PostMapping
    @ResponseBody
    public ResponseEntity createTransaction(@RequestBody CreateTransactionDTO createTransactionDTO){
        Transaction transaction = new Transaction();

        Car car = carRepository.findCar(createTransactionDTO.getCarRegistration()).get(0);

        transaction.setCar(car);
        transaction.setDescription(createTransactionDTO.getDescription());
        transaction.setTransactionTypeId(createTransactionDTO.getTransactionType().getValue());
        transaction.setTmstamp(Timestamp.from(createTransactionDTO.getDate().toInstant()));
        transactionRepository.save(transaction);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
