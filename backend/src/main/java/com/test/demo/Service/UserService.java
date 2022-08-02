package com.test.demo.Service;


import com.test.demo.Entity.User;
import com.test.demo.login.Login;

import java.util.List;

public interface UserService {
  User addUser(User user);
  User getUserById(int userId);
  void updateUser(User user);
  void deleteUserById(int userId);
  List<User> getAllUsers();
  List<User> getRole(String role);
  User findByEmailAndPassword(Login login);
}