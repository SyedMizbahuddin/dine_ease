package com.mizbah.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CityDto {

	@NotBlank(message = "City shoud be not blank")
	private String city;
	private Long id;
}
