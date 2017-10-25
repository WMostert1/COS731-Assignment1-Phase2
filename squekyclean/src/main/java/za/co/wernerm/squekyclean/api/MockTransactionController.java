package za.co.wernerm.squekyclean.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import za.co.wernerm.squekyclean.dto.CreateTransactionDTO;
import za.co.wernerm.squekyclean.dto.PostDTO;
import za.co.wernerm.squekyclean.dto.TransactionDTO;
import za.co.wernerm.squekyclean.model.Car;
import za.co.wernerm.squekyclean.model.Post;
import za.co.wernerm.squekyclean.model.Transaction;
import za.co.wernerm.squekyclean.model.TransactionType;
import za.co.wernerm.squekyclean.repository.CarRepository;
import za.co.wernerm.squekyclean.repository.TransactionRepository;

import java.security.SecureRandom;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by werner on 2017/10/24.
 */

@Controller
@RequestMapping(path="api/transactions/generate")
public class MockTransactionController {

    @Autowired
    CarRepository carRepository;

    @Autowired
    TransactionRepository transactionRepository;

    private String genRandomReg(){
        return UUID.randomUUID().toString().replace("-","").substring(0,8);
    }

    @GetMapping
    @ResponseBody
    public List<CreateTransactionDTO> mock(){
        List<CreateTransactionDTO> transactionDTOS = new ArrayList<>();
        List<Car> cars = new ArrayList<>();
        carRepository.findAll().forEach(car -> {
            if(car.getUser() != null)
                cars.add(car);
        });
        SecureRandom random = new SecureRandom();
        //exisiting car random transaction
        Car randomCar = cars.get(random.nextInt(cars.size()));
        Transaction existingCarTransaction = new Transaction();
        existingCarTransaction.setTransactionTypeId(random.nextInt(9)+1);
        existingCarTransaction.setDescription("Mock Transaction");
        existingCarTransaction.setTmstamp(Timestamp.from(Instant.now()));
        existingCarTransaction.setCar(randomCar);

        transactionDTOS.add(new CreateTransactionDTO(randomCar.getRegistrationNumber(),TransactionType.valueOf(existingCarTransaction.getTransactionTypeId()),
                existingCarTransaction.getTmstamp(),
                existingCarTransaction.getDescription()));


        transactionRepository.save(existingCarTransaction);

        //random transactions
        for(int i = 0; i < 2; i++) {
            String regNo = genRandomReg().toUpperCase();
            Car newCar = new Car();
            try{

                newCar.setRegistrationNumber(regNo);
                carRepository.save(newCar);
            }catch (Exception e){
                e.printStackTrace();
                //fine to let this go
                 }

            Transaction transaction = new Transaction();
            transaction.setTransactionTypeId(random.nextInt(9)+1);
            transaction.setDescription("Mock Transaction");
            transaction.setTmstamp(Timestamp.from(Instant.now()));
            transaction.setCar(newCar);
            transactionRepository.save(transaction);

            transactionDTOS.add(new CreateTransactionDTO(regNo, TransactionType.valueOf(transaction.getTransactionTypeId()),
                    transaction.getTmstamp(),
                    transaction.getDescription()));

        }
        return transactionDTOS;
    }
}
