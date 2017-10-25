package za.co.wernerm.squekyclean.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import za.co.wernerm.squekyclean.dto.CreatePostDTO;
import za.co.wernerm.squekyclean.dto.UserDTO;
import za.co.wernerm.squekyclean.model.Car;
import za.co.wernerm.squekyclean.model.Post;
import za.co.wernerm.squekyclean.model.Thread;
import za.co.wernerm.squekyclean.model.User;
import za.co.wernerm.squekyclean.repository.CarRepository;
import za.co.wernerm.squekyclean.repository.PostRepository;
import za.co.wernerm.squekyclean.repository.ThreadRepository;
import za.co.wernerm.squekyclean.repository.UserRepository;
import za.co.wernerm.squekyclean.subscriptions.SNSSubscription;

import javax.xml.ws.WebServiceException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;


/**
 * Created by werner on 2017/09/13.
 */
@Controller
@RequestMapping(path="api/user")
public class UserController {
    @Autowired
    public UserRepository userRepository;

    @Autowired
    public CarRepository carRepository;

    @PostMapping
    @ResponseBody
    public ResponseEntity createUser(String email, String carRegistration){
        if(email == null || email.isEmpty())
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        if(carRegistration == null || carRegistration.isEmpty())
            return new ResponseEntity(HttpStatus.BAD_REQUEST);

        User user = new User();
        user.setEmail(email);
        userRepository.save(user);

        Car car = new Car();
        car.setUser(user);
        car.setRegistrationNumber(carRegistration);
        carRepository.save(car);


        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    @ResponseBody
    public UserDTO getUser(String email){
        List<User> users = userRepository.findUsers(email);
        if(users == null || users.size() == 0)
            return null;

        return UserDTO.map(users.get(0));
    }



}