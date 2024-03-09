package com.mizbah.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DishDto {

	@NotBlank(message = "Name shoud be not blank")
	private String name;

	private int category_id;
}
