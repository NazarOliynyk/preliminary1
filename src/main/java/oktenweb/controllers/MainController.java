package oktenweb.controllers;

import oktenweb.components.ResponseSuccessURL;
import oktenweb.components.SuccessURLComponent;
import oktenweb.dao.UserDAO;
import oktenweb.models.Client;
import oktenweb.models.Contact;
import oktenweb.models.Restaurant;
import oktenweb.models.User;
import oktenweb.services.ContactService;
import oktenweb.services.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {
//    @Autowired
//    UserDAO userDAO;
//    @Autowired
//    private ContactService contactService;
//    @Autowired
//    UserServiceImpl userServiceImpl;
//    String name = "";
//    String className = "";
//    String username = "";
//
//    @PostMapping("/successURL")
//    public String successURL(Model model){
//
//        Authentication auth =
//                SecurityContextHolder.getContext().getAuthentication();
//
////        System.out.println("auth.getName(): "+auth.getName());
////        System.out.println("auth.toString(): "+auth.toString());
////        System.out.println("auth.getPrincipal(): "+auth.getPrincipal());
////        System.out.println("auth.getCredentials(): "+auth.getCredentials());
////        System.out.println("auth.getDetails(): "+auth.getDetails());
////        System.out.println("auth.getClass(): "+auth.getClass());
//        User userLogged = userDAO.findByUsername(auth.getName());
//        //userLogged = (User) userServiceImpl.loadUserByUsername(auth.getName());
//        Restaurant restaurant = new Restaurant();
//        Client client = new Client();
//        System.out.println("userLogged.toString(): "+userLogged.toString());
//        System.out.println("userLogged.getClass(): "+userLogged.getClass());
//
//        if(userLogged.getClass().equals(oktenweb.models.Restaurant.class)){
//            System.out.println("USER -> RESTAURANT");
//            restaurant = (Restaurant) userLogged;
//            className = "RESTAURANT";
//            username = restaurant.getUsername();
//            name = restaurant.getRestaurantName();
//        }else if (userLogged.getClass().equals(oktenweb.models.Client.class)){
//            System.out.println("USER -> CLIENT");
//            client = (Client) userLogged;
//            className = "CLIENT";
//            username = client.getUsername();
//            name = client.getClientName();
//        }else {
//            System.out.println("UNDEFINED CLASS!");
//        }
//        model.addAttribute("className", className);
//        model.addAttribute("username", username);
//        model.addAttribute("name", name);
//
//        return "securedPage";
//    }

    @Autowired
    private SuccessURLComponent successURLComponent;
    @PostMapping("/successURL")
    public String successURL(Model model){
        ResponseSuccessURL responseSuccessURL= successURLComponent.getResponse();
        model.addAttribute("className", responseSuccessURL.getClassName());
        model.addAttribute("username", responseSuccessURL.getUsername());
        model.addAttribute("name", responseSuccessURL.getName());
        return "securedPage";
    }

    @Autowired
    UserDAO userDAO;

    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam int id){

        String path =  "D:\\FotoSpringPreliminary1"+ File.separator;

        User user = userDAO.findOne(id);
        Restaurant restaurant;
        Client client;
        List<Contact> contacts = new ArrayList<>();


        if (user.getClass().equals(Restaurant.class)){
            restaurant = (Restaurant) user;
            contacts = restaurant.getRestaurantContacts();
        }else if (user.getClass().equals(Client.class)){
            client = (Client) user;
            contacts = client.getClientContacts();
        }
        for (Contact contact : contacts) {

            Path pathToFile = FileSystems.getDefault().getPath(path + contact.getAvatar());
            try {
                Files.delete(pathToFile);
            } catch (IOException e) {
                e.printStackTrace();
                return "Image was not deleted";
            }
        }

        userDAO.delete(id);
        return "securedPage";
    }
}
