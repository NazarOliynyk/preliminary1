package oktenweb.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString(exclude = {"restaurantContacts"})
@FieldDefaults(level = AccessLevel.PRIVATE)
@DiscriminatorValue("RESTAURANT")

public class Restaurant extends User{

//    String username;
//    String password;
//    String restaurantName;
//    String phoneNumber;
//
//    public Restaurant(String phoneNumber, String restaurantName, String password, String username) {
//        this.phoneNumber = phoneNumber;
//        this.restaurantName = restaurantName;
//        this.password = password;
//        this.username = username;
//    }
    String restaurantName;
    String phoneNumber;

    @JsonIgnore
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "restaurant"
    )
    List<Contact> restaurantContacts = new ArrayList<>();


}