package com.example.ems1.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.ems1.dto.VerifyOtpRequest;
import com.example.ems1.entity.User;
import com.example.ems1.exception.InvalidOtpException;
import com.example.ems1.exception.UserNotFoundException;
import com.example.ems1.exception.VerifedException;
import com.example.ems1.repository.UserRepository;

@Service
public class OtpService {
	private UserRepository userRepository;

	public OtpService(UserRepository userRepository) {
		
		this.userRepository = userRepository;
	}
public String VerifyOtp(VerifyOtpRequest verifyOtpRequest) {
	Optional<User> optionalUser = userRepository.getByEmail(verifyOtpRequest.getEmail());
	if(optionalUser.isPresent()) {
		User user = optionalUser.get();
		if(user.getOtp()==null) {
			throw new VerifedException("user is already verified");
		}
		if(!user.getOtp().equals(verifyOtpRequest.getOtp())) {
		
			throw new InvalidOtpException("Invalid OTP");
	}
		if(LocalDateTime.now().isAfter(user.getOtpexpirytime())) {
			return "OTP expired";
		}
		else {
			user.setVerified(true);
			user.setOtp(null);
			user.setOtpexpirytime(null);
			userRepository.save(user);
			return"otp verified successfully";
		}
}else {
	throw new UserNotFoundException("no user found");
}
	
}
}
