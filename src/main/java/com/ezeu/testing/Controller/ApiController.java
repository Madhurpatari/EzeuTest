package com.ezeu.testing.Controller;

import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.ezeu.testing.Entity.UserInfo;
import com.ezeu.testing.Repository.UserRepository;
import com.ezeu.testing.Service.UserService;

@RestController
public class ApiController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@GetMapping
	public String welcome(){
		return "WELCOME TO AUTHENTICATED USER MANAGEMENT SYSTEM";
	}


	@PostMapping("/signup")
	public String addUser(@RequestBody UserInfo user) {
		return  userService.addUser(user);
	}

	@GetMapping("/admin/home")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<String> adminHomePage(){
		return ResponseEntity.ok().body("Welcome Admin..!!");
	}

	@GetMapping("/users/home")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<String> userHomePage(){
		return ResponseEntity.ok().body("Welcome User..!!");
	}
	
	@GetMapping("/users/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public UserInfo getUserById(@PathVariable int id) {
		return userService.getUserById(id);
	}
	
	@GetMapping("/admin")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<UserInfo> getAllUsers(){
		return userService.getAllUsers();
	}

	@PutMapping("/forgot-Password")
	public ResponseEntity<String> forgotPasssword(@RequestParam String email){
		return new ResponseEntity<>(userService.forgotPassword(email), HttpStatus.OK);

	}

	@PutMapping("/set-password")
	public ResponseEntity<String> setPassword(@RequestParam String email, @RequestHeader String newPassword){
		return new ResponseEntity<>(userService.setPassword(email, newPassword ), HttpStatus.OK);
	}

	
}
