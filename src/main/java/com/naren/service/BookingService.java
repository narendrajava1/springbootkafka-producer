package com.naren.service;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.naren.mapper.BookingMapper;
import com.naren.model.Booking;
import com.naren.model.dto.BookingDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookingService {
	
	@Autowired
	private KafkaTemplate<String, Object> kafkaTemplate;
	@Autowired
	private BookingMapper bookingMapper;
	
	public String makeBooking(BookingDTO bookingDTO) {
		Booking booking = bookingMapper.transformBookikngDTOToBooking(bookingDTO);
		CompletableFuture<SendResult<String, Object>> completableFuture = kafkaTemplate.send("booking-topic","bike", booking);
		completableFuture.whenComplete(new BiConsumer<SendResult<String, Object>,Throwable>() {
			@Override
	        public void accept(SendResult<String, Object> result, Throwable u) {
	            if (u != null) {
	                log.error("error while send ing to the topic: {}",u);
	            }
	            else {
	                ProducerRecord<String, Object> record = result.getProducerRecord();
	                Booking data = (Booking) record.value();

	                log.info("Producing request succeeded data is: {} and partition is: {}, key is: {},topic is: {} at timestamp is: {}", data,record.partition(),record.key(),record.topic(),record.timestamp());   
	           }   
	      }
		});
		return booking.getBookingType();
		
	}

}
