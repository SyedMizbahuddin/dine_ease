package com.mizbah.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FoodCategoryDto {

	@NotBlank(message = "Name shoud be not blank")
	private String name;
	private String image;
}
