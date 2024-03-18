package com.mizbah.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BranchTableDto {

	private Long id;
	private int count;
	private TableTypeDto tableType;
}
