package com.naren.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.naren.model.dto.BookingDTO;
import com.naren.service.BookingService;

@RestController
public class BookingController {
	
	@Autowired
	private BookingService bookingService;
	
	@PostMapping("/booking")
	public String makeBooking(@RequestBody BookingDTO bookingDTO) {
		return bookingService.makeBooking(bookingDTO);
	}

}
