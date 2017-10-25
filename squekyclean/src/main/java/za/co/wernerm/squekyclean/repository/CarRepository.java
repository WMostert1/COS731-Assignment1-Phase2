package za.co.wernerm.squekyclean.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import za.co.wernerm.squekyclean.model.Car;
import za.co.wernerm.squekyclean.model.User;

import java.util.List;

public interface CarRepository extends CrudRepository<Car, Integer> {
    String DELETE_CAR = "DELETE FROM Cars WHERE registrationNumber = ?1";

    @Modifying
    @Transactional
    @Query(value = DELETE_CAR, nativeQuery = true)
    void deleteCar(String carRegistration);

    String FIND_CAR = "SELECT * FROM Cars WHERE registrationNumber = ?1";

    @Query(value = FIND_CAR, nativeQuery = true)
    List<Car> findCar(String carRegistration);
}
