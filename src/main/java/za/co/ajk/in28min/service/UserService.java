package za.co.ajk.in28min.service;

import za.co.ajk.in28min.model.Post;
import za.co.ajk.in28min.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUser(long userId);
    User saveUser(User user);
    void deleteUser(long userId);

    List<Post> getAllUserPosts(long userId);
    Post createUserPost(long userId, Post post);
    Post retrieveUserPost(long userId, long postId);
}
