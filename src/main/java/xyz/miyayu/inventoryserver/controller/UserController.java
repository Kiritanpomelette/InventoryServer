package xyz.miyayu.inventoryserver.controller;


import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import xyz.miyayu.inventoryserver.Service.User;
import xyz.miyayu.inventoryserver.Service.UserService;

@RestController
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/users")
  public List<User> users() {
    return userService.getUsers();
  }

  //追加分
  @GetMapping("/admins")
  public List<User> admins() {
    new RestTemplate().getForObject("http://localhost:8080/users", User.class);
    return userService.getUsers();
  }


}
