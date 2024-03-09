package com.mizbah.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CityDto {

	@NotBlank(message = "City shoud be not blank")
	private String city;
}
