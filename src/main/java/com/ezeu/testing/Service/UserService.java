package com.ezeu.testing.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.ezeu.testing.Utils.EmailUtil;
import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ezeu.testing.Entity.UserInfo;
import com.ezeu.testing.Repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository  userRepository;
	@Autowired
	private PasswordEncoder  passwordEncoder;

	@Autowired
	private EmailUtil emailUtil;

	public String addUser(UserInfo user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		if(user.getEmail().endsWith("@admin.com")){
			return "Admin registered successfully";
		}
		return "User registered successfully";


	}

	public UserInfo getUserById(int id) {
		return userRepository.findById(id)
				.orElseThrow(()-> new EntityNotFoundException("User not found  with id : "+ id));
	}

	public List<UserInfo> getAllUsers() {
		return userRepository.findAll();
	}

	public UserInfo updateUserDetails(int id, UserInfo userRequest) {
		Optional<UserInfo> optionalUser = userRepository.findById(id);
		if(optionalUser.isPresent()){
			UserInfo user = optionalUser.get();
			user.setEmail(userRequest.getEmail());
			user.setName(userRequest.getName());
			user.setPhoneNumber(userRequest.getPhoneNumber());
			return userRepository.save(user);
		}else{
			throw new
					EntityNotFoundException("User does not  exists");
		}

	}

	public UserInfo deleteAttributes(int id, String deleteReq) {
		Optional<UserInfo> optionalUser = userRepository.findById(id);
		if(optionalUser.isPresent()){
			UserInfo user = optionalUser.get();
			if(deleteReq.equalsIgnoreCase("email")){
				user.setEmail(null);
			} else if (deleteReq.equalsIgnoreCase("phoneNumber")) {
				user.setPhoneNumber(null);
			} else if (deleteReq.equalsIgnoreCase("name")) {
				user.setName(null);
			} else if (deleteReq.equalsIgnoreCase("id")) {
				throw new IllegalStateException("You cannot delete id");
			}
			return userRepository.save(user);
		}else{
			throw new
					EntityNotFoundException("User does not  exists");
		}
	}

	public String deleteUser(int id) {
		userRepository.deleteById(id);
		return "User removed successfully";
	}

	public String forgotPassword(String email) {
		UserInfo userInfo =userRepository.findByEmail(email)
				.orElseThrow(()-> new RuntimeException("User not found..!!"));
		try{
			emailUtil.sendSetPasswordEmail(email);
		} catch (MessagingException e) {
			throw new RuntimeException("Unable to send message please try again later.!!");
		}
	return "Please check your email to reset password.";
	}


	public String setPassword(String email, String newPassword) {
		UserInfo user = userRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("User not found with this email: " + email));
		user.setPassword(passwordEncoder.encode(newPassword));
		userRepository.save(user);
		return "new password set successfully";
	}
}
