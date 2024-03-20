package com.mizbah.dto;

import com.mizbah.dto.request.CityRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CityDto extends CityRequest {

	private Long id;
}
