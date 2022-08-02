package com.test.demo.Repository;

import com.test.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findByRole(String role);
	User findByEmailAndPassword(String email, String password);
}