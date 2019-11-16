package za.co.ajk.in28min.controller;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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
import za.co.ajk.in28min.repository.PostRepository;
import za.co.ajk.in28min.repository.UserRepository;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/jpa/users")
public class UserJPAController {

    private UserRepository userRepository;
    private PostRepository postRepository;

    public UserJPAController(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    /**
     * Retrieve all users
     *
     * @return
     */
    @GetMapping
    @ResponseBody
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(value = "/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public EntityModel<User> getUser(@PathVariable int userId) {

        Optional<User> userOptional = userRepository.findById(userId);

        userOptional.orElseThrow(() -> new UserNotFoundException("Cannot find user for id : " + userId));

        EntityModel<User> model = new EntityModel<>(userOptional.get());
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers());
        model.add(linkTo.withRel("all-users"));

        return model;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        User createdUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build().expand(createdUser.getId()).toUri();

        final HttpHeaders headers = new HttpHeaders();
        headers.add("Location", location.toString());
        return new ResponseEntity(createdUser, headers, HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteUserById(@PathVariable int userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        userOptional.orElseThrow(() -> new UserNotFoundException("Cannot find user for id : " + userId));
        userRepository.delete(userOptional.get());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{userId}/posts")
    public List<Post> retrieveAllPostsForAUser(@PathVariable Integer userId) {
        Optional<User> userOptional = userRepository.findById(userId);

        userOptional.orElseThrow(() -> new UserNotFoundException("Cannot find user for id : " + userId));
        return userOptional.get().getPosts();

    }

    @PostMapping("/{userId}/posts")
    public ResponseEntity<Object> createUserPost(@PathVariable int userId, @RequestBody Post post) {
        Optional<User> userOptional = userRepository.findById(userId);
        userOptional.orElseThrow(() -> new UserNotFoundException("Cannot find user for id : " + userId));

        User retrievedUser = userOptional.get();

        post.setUser(retrievedUser);
        postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").build().expand(post.getId()).toUri();
        return ResponseEntity.created(location).build();

    }

}
