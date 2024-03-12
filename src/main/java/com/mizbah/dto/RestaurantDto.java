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
public class RestaurantDto {

	@NotBlank(message = "Name shoud be not blank")
	private String name;

	private String image;

	private Long id;

	private Long ownerId;
}
