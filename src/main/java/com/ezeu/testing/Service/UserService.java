package com.ezeu.testing.Service;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezeu.testing.Entity.UserInfo;
import com.ezeu.testing.Repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository  userRepository;

	public String addUser(UserInfo user) {
		 userRepository.save(user);
		 return "User added successfully";
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

}
