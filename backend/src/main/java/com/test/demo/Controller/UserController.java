package com.test.demo.Controller;

import com.test.demo.Entity.User;
import com.test.demo.Service.UserService;
import com.test.demo.login.Login;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(com.test.demo.Controller.UserController.class);
  @Autowired
  UserService userService;
  // Insert user record
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public User addEmployee(@RequestBody User user) {
	  try {
		   userService.addUser(user);
		   logger.info("Registation success");
		  return userService.addUser(user);
	} catch (Exception e) {
		logger.error ("Check Mail ID "+e.getMessage(), HttpStatus.NOT_FOUND);
		return null;
	}
    
  }
  // Fetch all user records
  @GetMapping
  public List<User> getAllUsers(){
    return userService.getAllUsers();
  }
  // Fetch single user
  @GetMapping("/{id}")
  public User getUserById(@PathVariable("id") int userId){
	  try {
		  userService.getUserById(userId);
		  logger.info("Get By Id success");
			return userService.getUserById(userId);
	} catch (Exception e) {
		logger.error ("User Not Found "+e.getMessage(), HttpStatus.NOT_FOUND);
		return null;
	}
  }
  @GetMapping("/role/{role}")
  public List<User> getRole(@PathVariable("role") String role){
    return userService.getRole(role);
  }
  // Update user record
  @PutMapping("/updateuser")
  public ResponseEntity<String> updateUser(@RequestBody User user) {
    try {
      userService.updateUser(user);
      return new ResponseEntity<String>(HttpStatus.OK);
    }catch(NoSuchElementException ex){
      // log the error message
      System.out.println(ex.getMessage());
      return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }
  }
  // Delete user record
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteUser(@PathVariable int id){
    try {
      userService.deleteUserById(id);
      logger.info("Deleted User Success");
      return new ResponseEntity<String>(HttpStatus.OK);
    }catch(RuntimeException ex){
      // log the error message
      System.out.println(ex.getMessage());
      logger.error ("User Not Found "+ex.getMessage(), HttpStatus.NOT_FOUND);
      return null;
    }
  }
  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody Login login){
	  logger.info("Under User Login method");
	  if(userService.findByEmailAndPassword(login)!= null) {
		  logger.info("Login Success");
		  return (ResponseEntity<?>) ResponseEntity.status(HttpStatus.OK).body(userService.findByEmailAndPassword(login));
	  }else {
		  logger.error("Invalid Id and Password", HttpStatus.NOT_ACCEPTABLE);
//		  return new ResponseEntity<>("Invalid Id and Password", HttpStatus.NOT_ACCEPTABLE);
		  return null;
	  }
	
  }
}