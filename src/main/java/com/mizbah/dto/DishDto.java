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
public class DishDto {

	@NotBlank(message = "Name shoud be not blank")
	private String name;

	private Long categoryId;

	private Long id;
}
