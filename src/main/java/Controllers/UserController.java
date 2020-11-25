package Controllers;

import Entities.User;
import Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Pattern;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    public User registerUser(User user){
        boolean isValidEmail = validateEmail(user.getEmail());
        boolean isValidPassword = validatePassword(user.getPassword());
        if(isValidEmail && isValidPassword)
            return userRepository.save(user);
        //return json with message wrong pass or email and http response
        return null;
    }

    public boolean validateEmail(String email){
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return Pattern.matches(regex, email);
    }

    public boolean validatePassword(String password){
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&-+=()])(?=\\S+$).{8,20}$";
        return Pattern.matches(regex, password);
    }

}
