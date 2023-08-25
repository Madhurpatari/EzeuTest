package com.ezeu.testing.Controller;

import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezeu.testing.Entity.UserInfo;
import com.ezeu.testing.Repository.UserRepository;
import com.ezeu.testing.Service.UserService;

@RestController
@RequestMapping("/api/users")
public class ApiController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	@PostMapping
	public String addUser(@RequestBody UserInfo user) {
		return  userService.addUser(user);
	}
	
	@GetMapping("/{id}")
	public UserInfo getUserById(@PathVariable int id) {
		return userService.getUserById(id);
	}
	
	@GetMapping
	public List<UserInfo> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@PutMapping("/{id}")
	public UserInfo updateUserDetails(@PathVariable int id, @RequestBody UserInfo userRequest){
		boolean isExists = userRepository.existsById(id);
		if(isExists) {
			return userService.updateUserDetails(id, userRequest);
		}else {
			throw new EntityNotFoundException("User does not exists");
		}
	}

	
	@PatchMapping("/{id}/{deleteReq}")
	public UserInfo deleteAttributes(@PathVariable int id, @PathVariable String deleteReq) {
		boolean isExists = userRepository.existsById(id);
		if(isExists) {
			return userService.deleteAttributes(id, deleteReq);
		}else {
			throw new EntityNotFoundException("User does not exists");
		}
	}
	
}
