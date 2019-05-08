package oktenweb.components;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import oktenweb.dao.UserDAO;
import oktenweb.models.Client;
import oktenweb.models.Restaurant;
import oktenweb.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Component
@Service
public class SuccessURLComponent {
    @Autowired
    UserDAO userDAO;
    public ResponseSuccessURL getResponse(){

        Authentication auth =
                SecurityContextHolder.getContext().getAuthentication();
        String name = "";
        String className = "";
        String username = "";

        System.out.println("auth.getName(): "+auth.getName());
        System.out.println("auth.toString(): "+auth.toString());
        System.out.println("auth.getPrincipal(): "+auth.getPrincipal());
        System.out.println("auth.getCredentials(): "+auth.getCredentials());
        System.out.println("auth.getDetails(): "+auth.getDetails());
        System.out.println("auth.getClass(): "+auth.getClass());

        User userLogged = userDAO.findByUsername(auth.getName());
     //   System.out.println("userLogged.toString(): "+userLogged.toString());
      //  System.out.println("userLogged.getClass(): "+userLogged.getClass());

        System.out.println("userLogged.getClass().toString(): "+userLogged.getClass().toString());

        System.out.println(userLogged.getClass().toString().equals("class oktenweb.models.Restaurant"));
        System.out.println(userLogged.getClass().toString().equals("class oktenweb.models.RestaurantTTT"));
        Restaurant restaurant = new Restaurant();
        Client client = new Client();
        if(auth.getName().equals("admin")){
            System.out.println("IT IS ADMIN, Who Just LOGGED in!!");
            return new ResponseSuccessURL("IT IS ADMIN, Who Just LOGGED in!!", "admin", "admin");
        }
        else {
                if(userLogged.getClass().equals(oktenweb.models.Restaurant.class)){
                System.out.println("USER -> RESTAURANT");
                restaurant = (Restaurant) userLogged;
                className = "RESTAURANT";
                username = restaurant.getUsername();
                name = restaurant.getRestaurantName();
                }else if (userLogged.getClass().equals(oktenweb.models.Client.class)){
                System.out.println("USER -> CLIENT");
                client = (Client) userLogged;
                className = "CLIENT";
                username = client.getUsername();
                name = client.getClientName();
                }else {
                System.out.println("UNDEFINED CLASS!");
                className = "UNDEFINED CLASS!";
            }
        }


        return new ResponseSuccessURL(className, username, name);
    }



}
