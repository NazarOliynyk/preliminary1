package oktenweb.components;

import oktenweb.dao.UserDAO;
import oktenweb.models.Client;
import oktenweb.models.Contact;
import oktenweb.models.Restaurant;
import oktenweb.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//@Component
@Service
public class ShowContactsComponent {

    @Autowired
    UserDAO userDAO;

    public List<Contact> getContacts(String username){
        System.out.println("username.toString(): "+username.toString());

        List<Contact> results = new ArrayList<>();
        User userChosen = new User();

        List<User> users = userDAO.findAll();
        for (User user : users) {
            if(user.getUsername().equals(username)){
                userChosen = user;
                System.out.println(userChosen.toString());
            }
        }
        if(userChosen.getClass().equals(oktenweb.models.Client.class)){
            Client client = (Client) userChosen;
            System.out.println(client.toString());
            results = client.getClientContacts();
        }else if (userChosen.getClass().equals(oktenweb.models.Restaurant.class)){
            Restaurant restaurant = (Restaurant) userChosen;
            System.out.println(restaurant.toString());
            results = restaurant.getRestaurantContacts();
        }

        for (Contact result : results) {
            System.out.println(result.toString());
        }

        return results;
    }
}
