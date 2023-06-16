package com.naren.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
	
	private String bookingType;
	private String bookingPersonName;
	private LocalDate bookingDate;
	private BigDecimal amountPaid;
	private BigDecimal minAmount;
	private String bookingFor;

}