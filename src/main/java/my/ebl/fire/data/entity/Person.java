package my.ebl.fire.data.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    @Column(name = "first_name", length = 50, nullable = false)
    private String firstName;
    @Basic
    @Column(name = "last_name", length = 50, nullable = false)
    private String lastName;
    @Basic
    @Column(name = "age", length = 3, nullable = false)
    private String age;
    @Basic
    @Column(name = "favourite_colour", length = 50, nullable = false)
    private String favouriteColour;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getFavouriteColour() {
        return favouriteColour;
    }

    public void setFavouriteColour(String favouriteColour) {
        this.favouriteColour = favouriteColour;
    }
}
