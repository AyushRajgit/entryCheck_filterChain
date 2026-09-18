package in.cper.entryCheck_filterChain.controller;

import in.cper.entryCheck_filterChain.entity.User;
import in.cper.entryCheck_filterChain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user){
        System.out.println("Entered addUser endpoint");
        userService.addUser();
        System.out.println("Exiting addUser endpoint");
        return ResponseEntity.ok(user);
    }

}
