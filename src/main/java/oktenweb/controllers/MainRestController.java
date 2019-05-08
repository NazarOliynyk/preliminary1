package oktenweb.controllers;


import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import oktenweb.components.ShowContactsComponent;
import oktenweb.dao.UserDAO;
import oktenweb.models.Client;
import oktenweb.models.Contact;
import oktenweb.models.Restaurant;
import oktenweb.models.User;
import oktenweb.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@RestController
public class MainRestController {

    @Autowired
    UserDAO userDAO;

    @Autowired
    private ContactService contactService;
//
//    @PostMapping("/saveContact")
//    public User saveContact(
//            @RequestParam("className") String className,
//            @RequestParam("username") String username,
//            @RequestParam("contactName") String contactName,
//            @RequestParam("email") String email){
//        Restaurant restaurant;
//        Client client;
//        User user = new User();
//        Contact contact = new Contact(contactName, email);
//        if(className.equals("CLIENT")){
//            client = (Client) userDAO.findByUsername(username);
//            contact.setClient(client);
//            contactService.save(contact);
//            user= client;
//        }else if (className.equals("RESTAURANT")){
//            restaurant = (Restaurant) userDAO.findByUsername(username);
//            contact.setRestaurant(restaurant);
//            contactService.save(contact);
//            user= restaurant;
//        }
//        return user;
//    }

    @PostMapping("/saveContact")
    public
    @ResponseBody
        // allows to not return template
    User upload(
            @RequestParam("className") String className,
            @RequestParam("username") String username,
            @RequestParam("contactName") String contactName,
            @RequestParam("email") String email,
            @RequestParam("image") MultipartFile image
    ) throws MessagingException {

        Contact contact = new Contact(contactName, email);

//        String path = System.getProperty("user.home")
//                + File.separator
//                +"images"
//                +File.separator
//                +image.getOriginalFilename();
//        String path = "D:\\JAVA_ADVANCED\\SPRING\\preliminary1\\src\\main\\resources\\static"
//                +File.separator
//                +image.getOriginalFilename();

        String path =  "D:\\FotoSpringPreliminary1"+File.separator
                +image.getOriginalFilename();

        try {
            image.transferTo(new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        contact.setAvatar(image.getOriginalFilename());
        User user = new User();
        Restaurant restaurant;
        Client client;

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


//    @PostMapping("/showContacts")
//    public List<Contact> showContact(@RequestParam("username") String username){
//        System.out.println("username.toString(): "+username.toString());
//
//        List<Contact> results = new ArrayList<>();
//        User userChosen = new User();
//
//        List<User> users = userDAO.findAll();
//        for (User user : users) {
//            if(user.getUsername().equals(username)){
//                userChosen = user;
//                System.out.println(userChosen.toString());
//            }
//        }
//        if(userChosen.getClass().equals(oktenweb.models.Client.class)){
//            Client client = (Client) userChosen;
//            System.out.println(client.toString());
//            results = client.getClientContacts();
//        }else if (userChosen.getClass().equals(oktenweb.models.Restaurant.class)){
//            Restaurant restaurant = (Restaurant) userChosen;
//            System.out.println(restaurant.toString());
//            results = restaurant.getRestaurantContacts();
//        }
//
//        for (Contact result : results) {
//            System.out.println(result.toString());
//        }
//
//        return results;
//    }

    @Autowired
    ShowContactsComponent showContactsComponent;
    @PostMapping("/showContacts")
    public List<Contact> showContact(@RequestParam("username") String username){
        return showContactsComponent.getContacts(username);
    }

    // the next Controller is just to try <select> in ajax

    @PostMapping("/saveTheName")
    public String saveTheName(
          @RequestParam("contactName}") String contactName
           // @RequestBody Contact contact
    ){
        //System.out.println("contact.toString(): "+contact.toString());
        System.out.println("contactName: "+contactName);
        return "";
    }

}
