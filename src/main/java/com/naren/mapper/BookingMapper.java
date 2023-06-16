package com.naren.mapper;

import org.mapstruct.Mapper;

import com.naren.model.Booking;
import com.naren.model.dto.BookingDTO;

@Mapper(componentModel = "spring")
public interface BookingMapper {
	
	Booking transformBookikngDTOToBooking(BookingDTO bookingDTO);

}
