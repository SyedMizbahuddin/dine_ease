package com.mizbah.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TableTypeDto {

	@NotBlank(message = "Name shoud be not blank")
	private String name;

	@Min(value = 1, message = "Chairs should be atleast 1")
	private int chairs;
	private Long id;
}
