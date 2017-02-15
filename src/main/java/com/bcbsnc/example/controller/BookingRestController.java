package com.bcbsnc.example.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bcbsnc.example.entity.Booking;
import com.bcbsnc.example.repository.BookingRepository;

@RestController
public class BookingRestController {

	@Autowired
	BookingRepository bookingRepository;
	
	@RequestMapping("/bookings")
	Collection<Booking> bookings() {
		return this.bookingRepository.findAll();
	}
}
