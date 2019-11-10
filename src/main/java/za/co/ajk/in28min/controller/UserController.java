package za.co.ajk.in28min.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import za.co.ajk.in28min.exceptions.UserNotFoundException;
import za.co.ajk.in28min.model.Post;
import za.co.ajk.in28min.model.User;
import za.co.ajk.in28min.service.impl.UserServiceImpl;

import javax.validation.Valid;
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
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    /**
     * Return specific user by Id
     *
     * @param userId
     * @return
     */
//    @GetMapping(value = "/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
//    public EntityModel<User> getUser(@PathVariable long userId) {
//
//        User user = userService.getUser(userId);
//
//        //  Also add link to retrieve all the users.
//        EntityModel<User> model = new EntityModel<>(user);
//        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers());
//        model.add(linkTo.withRel("all-users"));
//
//        return model;
//    }
    @GetMapping(value = "/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
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
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User createdUser = userService.saveUser(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build().expand(createdUser.getId()).toUri();
//      or
//        URI build = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").build(createdUser.getId());

        final HttpHeaders headers = new HttpHeaders();
        headers.add("Location", location.toString());
        return new ResponseEntity(createdUser, headers, HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable long userId) {
        User user = userService.getUser(userId);
        if (user == null) {
            throw new UserNotFoundException("Cannot find user with id " + userId + " to delete");
        }
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/userId}/posts")
    public List<Post> retrieveAllUserPosts(@PathVariable long userId) {
        return userService.getAllUserPosts(userId);
    }

    @PostMapping("/{userId}/posts")
    public ResponseEntity<Post> createUserPost(@PathVariable long userId, @Valid @RequestBody Post post) {
        userService.createUserPost(userId, post);
        return new ResponseEntity<>(post, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}/posts/{postId}")
    public Post getPostDetails(@PathVariable long userId, @PathVariable long postId) {
        return userService.retrieveUserPost(userId, postId);
    }
}
