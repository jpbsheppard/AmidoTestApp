package Api.service;

import Api.model.User;

import java.util.List;

/**
 * Created by jack on 30/05/17.
 */
public interface UserServiceImpl {

    User findById(long id);

    User findByName(String name);

    void saveUser(User user);

    void updateUser(User user);

    void deleteUserById(long id);

    List<User> findAllUsers();

    void deleteAllUsers();

    boolean isUserExist(User user);
}
