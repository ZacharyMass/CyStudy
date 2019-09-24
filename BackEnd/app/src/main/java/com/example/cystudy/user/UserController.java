package com.example.cystudy.user;

import com.example.cystudy.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path="/user")
public class UserController {
  @Autowired
  private UserRepository userRepository;

  @PostMapping(path="/add")
  public @ResponseBody String addNewUser(@RequestParam String first_name,
                                         @RequestParam String last_name,
                                         @RequestParam String role,
                                         @RequestParam String password
                                         ) {
    User n = new User();
    n.setFirstName(first_name);
    n.setLastName(last_name);
    n.setRole(role);
    n.setPassword(password);

    userRepository.save(n);
    return "Saved";
  }

  @GetMapping(path="/list")
  public @ResponseBody Iterable<User> getAllUsers() {
    return userRepository.findAll();
  }
}
