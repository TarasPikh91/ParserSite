package customertimes.springParserDb.controller;

import customertimes.springParserDb.dao.Role;
import customertimes.springParserDb.dao.User;
import customertimes.springParserDb.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;

@Controller
public class RegistrationController {

    final private UserRepository userRepository;

    public RegistrationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String saveUser(@ModelAttribute User user, Model model) {
        User userFromDb = userRepository.findByUsername(user.getUsername()).get();
        if (userFromDb != null) {
            model.addAttribute("message", "User already exist");
            return "redistration";
        }
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return "login";
    }
}
