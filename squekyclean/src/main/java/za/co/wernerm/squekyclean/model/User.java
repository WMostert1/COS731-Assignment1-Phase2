package za.co.wernerm.squekyclean.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * Created by werner on 2017/10/24.
 */
@Entity
@Table(name = "Users")
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Car> cars;

    private String email;

    public User() {
    }

    public User(long id, Set<Car> cars, String email) {
        this.id = id;
        this.cars = cars;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
