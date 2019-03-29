package oktenweb.controllers;

import oktenweb.models.Restaurant;
import oktenweb.models.User;
import oktenweb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestaurantController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;


    @PostMapping("/saveRestaurant")
    public User saveRestaurant(@RequestBody Restaurant restaurant){

        System.out.println(restaurant.toString());
        restaurant.setPassword(passwordEncoder.encode(restaurant.getPassword()));
        userService.save(restaurant);

        return restaurant;
    }

}
