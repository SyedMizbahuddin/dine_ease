package com.mizbah.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginResponse {

	private String token;

}
