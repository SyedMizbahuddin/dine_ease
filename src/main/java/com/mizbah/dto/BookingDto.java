package com.mizbah.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BookingDto {

	private Long id;
	private Long branchTableId;
	private Date startDateTime;
	private Date endDateTime;
}
