package za.co.ajk.in28min.service.impl;

import org.springframework.stereotype.Service;
import za.co.ajk.in28min.exceptions.PostNotFoundException;
import za.co.ajk.in28min.exceptions.UserNotFoundException;
import za.co.ajk.in28min.model.Post;
import za.co.ajk.in28min.model.User;
import za.co.ajk.in28min.repository.UserRepository;
import za.co.ajk.in28min.service.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static List<User> userList = new ArrayList<>();
    private static List<Post> userPosts = new ArrayList<>();

    static {
//        userList.add(new User(10001, "Adam", new Date(), new ArrayList<>()));
//        userList.add(new User(10002, "Eve", new Date(), new ArrayList<>()));
//        userList.add(new User(3, "Jack", new Date(), new ArrayList<>()));
//        userList.add(new User(1, "Adam", new Date()));
//        userList.add(new User(2, "Eve", new Date()));
//        userList.add(new User(3, "Jack", new Date()));

    }

    private UserRepository userRepository;

    public UserServiceImpl() {
    }

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userList;
    }

    @Override
    public User getUser(long userId) {
        Optional<User> userOpt = userList.stream()
                .filter(user -> user.getId() == userId)
                .findAny();

        userOpt.orElseThrow(() -> new UserNotFoundException("User not found for id : " + userId));
        return userOpt.get();

    }

    @Override
    public User saveUser(User user) {
        if (user.getId() == 0) {
            user.setId(userList.size() + 1);
        }

        userList.add(user);
        return user;
    }

    @Override
    public void deleteUser(long userId) {
        userList = userList.stream().filter(user -> user.getId() != userId)
                .collect(Collectors.toList());

    }

    @Override
    public List<Post> getAllUserPosts(long userId) {
        //return getUser(userId).getUserPosts();
        return null;
    }

    @Override
    public Post createUserPost(long userId, Post post) {
        User user = getUser(userId);
      //  user.getUserPosts().add(post);
        return post;
    }

    @Override
    public Post retrieveUserPost(long userId, long postId) {
        User user = getUser(userId);
//        List<Post> userPost = user.getUserPosts();
//        Optional<Post> optionalPost = userPost.stream()
//                .filter(post -> post.getId() == postId)
//                .findFirst();
//
//        optionalPost.orElseThrow(() -> new PostNotFoundException("Post not found"));
//        return optionalPost.get();

        return null;
    }
}
