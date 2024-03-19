package com.mizbah.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mizbah.validation.annotation.WithinTimeRange;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
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

	@NotNull(message = "Start date time must be provided")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm", timezone = "Asia/Kolkata")
	@Future
	@WithinTimeRange(start = "08:00", end = "22:00")
	private Date startDateTime;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm", timezone = "Asia/Kolkata")
	private Date endDateTime;
}
