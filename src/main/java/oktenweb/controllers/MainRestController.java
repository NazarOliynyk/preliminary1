package oktenweb.controllers;

import oktenweb.dao.UserDAO;
import oktenweb.models.Client;
import oktenweb.models.Contact;
import oktenweb.models.Restaurant;
import oktenweb.models.User;
import oktenweb.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class MainRestController {

    @Autowired
    UserDAO userDAO;

    @Autowired
    private ContactService contactService;

    @PostMapping("/saveContact")
    public User saveContact(
            @RequestParam("className") String className,
            @RequestParam("username") String username,
            @RequestParam("contactName") String contactName,
            @RequestParam("email") String email){
        Restaurant restaurant;
        Client client;
        User user = new User();
        Contact contact = new Contact(contactName, email);
        if(className.equals("CLIENT")){
            client = (Client) userDAO.findByUsername(username);
            contact.setClient(client);
            contactService.save(contact);
            user= client;
        }else if (className.equals("RESTAURANT")){
            restaurant = (Restaurant) userDAO.findByUsername(username);
            contact.setRestaurant(restaurant);
            contactService.save(contact);
            user= restaurant;
        }
        return user;
    }


//    @GetMapping("/showContact")
//    public List<Contact> showContact(){
//        List<Contact> results = contactService.findAll();
//        return results;
//    }

}
