package oktenweb.controllers;

import oktenweb.dao.UserDAO;
import oktenweb.models.Client;
import oktenweb.models.Restaurant;
import oktenweb.models.User;
import oktenweb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class RestaurantController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;
    @Autowired
    UserDAO userDAO;

//    @PostMapping("/saveRestaurant")
//    public Restaurant saveRestaurant(@RequestBody Restaurant restaurant)
//            throws SQLException {
//
//        System.out.println(restaurant.toString());
//        restaurant.setPassword(passwordEncoder.encode(restaurant.getPassword()));
//        try {
//            userService.save(restaurant);
//        }catch (Exception e){
//            System.out.println(e);
//        }
//        return (Restaurant)userDAO.findByUsername(restaurant.getUsername());
//    }
    @PostMapping("/saveRestaurant")
    public String saveRestaurant(@RequestBody Restaurant restaurant) {
        System.out.println(restaurant.toString());
        restaurant.setPassword(passwordEncoder.encode(restaurant.getPassword()));
        try {
            userService.save(restaurant);
        }catch (Exception e){
            System.out.println(e);
            return restaurant.getUsername()+" has not been saved";
        }
        return userDAO.findByUsername(restaurant.getUsername()).getUsername()+" has been saved successfully";
    }

}
