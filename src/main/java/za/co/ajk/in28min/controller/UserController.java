package za.co.ajk.in28min.controller;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import za.co.ajk.in28min.model.User;
import za.co.ajk.in28min.service.impl.UserServiceImpl;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    /**
     * Retrieve all users
     *
     * @return
     */
    @GetMapping
    @ResponseBody
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    /**
     * Return specific user by Id
     *
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    public User getUser(@PathVariable long userId) {
        return userService.getUser(userId);
    }

    /**
     * Save new user
     *
     * @param user
     * @return
     */
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.saveUser(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build().expand(createdUser.getId()).toUri();
//      or
//        URI build = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").build(createdUser.getId());

        final HttpHeaders headers = new HttpHeaders();
        headers.add("Location", location.toString());


        return new ResponseEntity(createdUser, headers, HttpStatus.CREATED);
    }
}
