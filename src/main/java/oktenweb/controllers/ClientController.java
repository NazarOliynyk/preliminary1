package oktenweb.controllers;


import oktenweb.dao.UserDAO;
import oktenweb.models.Client;
import oktenweb.models.User;
import oktenweb.services.UserService;
import oktenweb.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class ClientController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;
    @Autowired
    UserDAO userDAO;
    @Autowired
    UserServiceImpl userServiceImpl;


//    @PostMapping("/saveClient")
//    public Client saveRestaurant(@RequestBody Client client,
//                               Model model) throws SQLException{
//
//        System.out.println(client.toString());
//        client.setPassword(passwordEncoder.encode(client.getPassword()));
//
//            userService.save(client);
//
//
//        System.out.println(userDAO.findByUsername(client.getUsername()).toString());
//        return (Client)userDAO.findByUsername(client.getUsername());
//    }

//    @PostMapping("/saveClient")
//    public String saveRestaurant(@RequestBody Client client, Model model) {
//        client.setPassword(passwordEncoder.encode(client.getPassword()));
//        try {
//            userService.save(client);
//        }catch (Exception e){
//            System.out.println(e);
//            return client.getUsername()+" has not been saved";
//        }
//        return userDAO.findByUsername(client.getUsername()).getUsername()+" has been saved successfully";
//    }

    @PostMapping("/saveClient")
    public String saveClient(@RequestBody Client client,
                                 Model model) {

        if(!userDAO.existsByUsername(client.getUsername())){
            System.out.println(client.toString());
            client.setPassword(passwordEncoder.encode(client.getPassword()));
            userServiceImpl.save(client);
            return userDAO.findByUsername(client.getUsername()).getUsername()+" has been saved successfully";
        }else {
            return client.getUsername()+" has not been saved";
        }

    }
}
