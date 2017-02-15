package com.bcbsnc.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Booking {

	@Id
	@GeneratedValue
	private Long id;
	private String bookingName;
	
	public Booking() {
		super();

	}
	public Booking(String bookingName) {
		super();
		this.bookingName = bookingName;
	}
	public Long getId() {
		return id;
	}
	public String getBookingName() {
		return bookingName;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setBookingName(String bookingName) {
		this.bookingName = bookingName;
	}
	@Override
	public String toString() {
		return "Booking [id=" + id + ", bookingName=" + bookingName + "]";
	}
}
