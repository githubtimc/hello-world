package com.bcbsnc.example.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bcbsnc.example.entity.Booking;

public interface BookingRepository extends JpaRepository <Booking, Long> {
	Collection<Booking> findByBookingName(String bookingName) ;

}
