package za.co.wernerm.squekyclean.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import za.co.wernerm.squekyclean.model.Car;
import za.co.wernerm.squekyclean.model.User;
import za.co.wernerm.squekyclean.repository.CarRepository;
import za.co.wernerm.squekyclean.repository.PostRepository;
import za.co.wernerm.squekyclean.repository.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by werner on 2017/10/24.
 */
@Controller
@RequestMapping(path="api/car")
public class CarController {
    @Autowired
    public CarRepository carRepository;

    @Autowired
    public UserRepository userRepository;

    @PutMapping
    @ResponseBody
    public ResponseEntity updateCars(String email, String [] carRegistration){
        if(carRegistration == null)
            carRegistration = new String[]{};
        List<String> carRegistrations = Arrays.asList(carRegistration);
        List<Car> cars = new ArrayList<>();
        carRepository.findAll().forEach((car)->{

            if(car.getUser() != null && car.getUser().getEmail().equals(email))
                cars.add(car);
        });
        List<String> deletedCars = new ArrayList<>();
        List<String> newCars = new ArrayList<>();
        for(Car car : cars){
            if(!carRegistrations.contains(car.getRegistrationNumber())){
                deletedCars.add(car.getRegistrationNumber());
            }
        }

        for(String carReg : carRegistration){
            boolean isNew = true;
            for(Car car : cars){
                if(car.getRegistrationNumber().equals(carReg))
                    isNew = false;
            }
            if(isNew)
                newCars.add(carReg);
        }
        User currentUser = userRepository.findUsers(email).get(0);
        for(String carReg : newCars){
            Car car = new Car();
            car.setUser(currentUser);
            car.setRegistrationNumber(carReg);
            carRepository.save(car);
        }

        for(String deletedCar : deletedCars){
            carRepository.deleteCar(deletedCar);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
