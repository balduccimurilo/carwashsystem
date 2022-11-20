package com.tcc.lavarapido.models.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.tcc.lavarapido.enums.WashType;
import com.tcc.lavarapido.models.User;
import com.tcc.lavarapido.models.Wash;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WashDTO {
	
	private Long id;
	private WashType washType;
	private Integer price;
	private LocalDateTime dtReservation;
	private User user;
	
	public WashDTO(Wash wash) {
		this.id = wash.getIdWash();
		this.washType = wash.getWashType();
		this.price = wash.getPrice();
		this.dtReservation = wash.getDtReservation();
		this.user = wash.getUser();
	}
}
