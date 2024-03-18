package com.mizbah.adapter;

import org.springframework.stereotype.Service;

import com.mizbah.adapter.interfaces.AbstractAdapter;
import com.mizbah.dto.BookingDto;
import com.mizbah.entity.Booking;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BookingAdapter extends AbstractAdapter<Booking, BookingDto> {

	TableTypeAdapter tableTypeAdapter;

	@Override
	public Booking toEntity(BookingDto dto) {
		return Booking.builder()
				.startDateTime(dto.getStartDateTime())
				.endDateTime(dto.getEndDateTime())
				.build();
	}

	@Override
	public BookingDto toDto(Booking entity) {
		return BookingDto.builder()
				.id(entity.getId())
				.startDateTime(entity.getStartDateTime())
				.endDateTime(entity.getEndDateTime())
				.branchTableId(entity.getBranchTable().getId())
				.build();

	}

}
