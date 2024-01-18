package com.nicolas.belt.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.nicolas.belt.models.LoginUser;
import com.nicolas.belt.models.User;
import com.nicolas.belt.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    @Autowired 
	private BCryptPasswordEncoder bCrypt;



    public List<User> findAll() {
		return userRepo.findAll(); 
	}

    public User findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

	public User registerUser(User user)  {
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashed);
		return userRepo.save(user);
	}

    public boolean authenticateUser(LoginUser user) {
		User userRegistered = userRepo.findByEmail(user.getEmail());
		if (userRegistered == null) {
			return false;
		} else {
			if (BCrypt.checkpw(user.getPassword(), userRegistered.getPassword())) {
				return true;
			} else {
				return false;
			}
		}
	}
}
