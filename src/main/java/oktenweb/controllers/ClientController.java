package oktenweb.controllers;


import oktenweb.dao.UserDAO;
import oktenweb.models.Client;
import oktenweb.models.User;
import oktenweb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UserService userService;
    @Autowired
    UserDAO userDAO;


    @PostMapping("/saveClient")
    public Client saveRestaurant(@RequestBody Client client,
                               Model model){

        System.out.println(client.toString());
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        userService.save(client);


        System.out.println(userDAO.findByUsername(client.getUsername()).toString());
        return (Client)userDAO.findByUsername(client.getUsername());
    }
}
