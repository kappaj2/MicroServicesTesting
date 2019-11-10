package za.co.ajk.in28min.service.impl;

import org.hibernate.annotations.NotFoundAction;
import org.springframework.stereotype.Service;
import za.co.ajk.in28min.model.User;
import za.co.ajk.in28min.repository.UserRepository;
import za.co.ajk.in28min.service.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static List<User> userList = new ArrayList<>();

    static {
        userList.add(new User(1, "Adam", new Date()));
        userList.add(new User(2, "Eve", new Date()));
        userList.add(new User(3, "Jack", new Date()));
    }

    private UserRepository userRepository;

    public UserServiceImpl() {
    }

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userList;
    }

    public User getUser(long userId){
       Optional<User> userOpt = userList.stream()
               .filter(user -> user.getId() == userId)
               .findAny();

        userOpt.orElseThrow(() -> new RuntimeException("User not found"));
        return userOpt.get();

    }

    public User saveUser(User user){
        if(user.getId() == 0){
            user.setId(userList.size()+1);
        }

        userList.add(user);
        return user;
    }
}
