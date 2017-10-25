package za.co.wernerm.squekyclean.dto;

import za.co.wernerm.squekyclean.model.Car;
import za.co.wernerm.squekyclean.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by werner on 2017/10/24.
 */
public class UserDTO {
    private List<CarDTO> cars = new ArrayList<>();
    private String email;

    public UserDTO() {
    }

    public static UserDTO map(User user){
        UserDTO dto = new UserDTO();
        dto.setEmail(user.getEmail());
        List<CarDTO> cars = new ArrayList<>();
        for(Car car : user.getCars())
            cars.add(CarDTO.map(car));
        dto.setCars(cars);
        return dto;
    }

    public UserDTO(List<CarDTO> cars, String email) {
        this.cars = cars;
        this.email = email;
    }

    public List<CarDTO> getCars() {
        return cars;
    }

    public void setCars(List<CarDTO> cars) {
        this.cars = cars;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
