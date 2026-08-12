package com.example.ems1.dto;

import lombok.Data;

@Data
public class VerifyOtpRequest {
	private String email;
	private String otp;

}
