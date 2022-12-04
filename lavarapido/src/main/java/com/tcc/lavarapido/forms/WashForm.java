package com.tcc.lavarapido.forms;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.tcc.lavarapido.enums.WashType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WashForm {
	
	private Long id;
	private WashType washType;
	private BigDecimal price;
	private String dtReservation;
	private String cpfClient;
	private Long idClient;
	private String placa;
	private String carro;
	
}
