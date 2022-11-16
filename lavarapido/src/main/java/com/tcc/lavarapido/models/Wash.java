package com.tcc.lavarapido.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tcc.lavarapido.enums.WashType;
import com.tcc.lavarapido.models.dto.WashDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
//@Table(name = "wash", schema = "carwash")
@Table(name = "wash")
public class Wash implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idWash;

	@Column(nullable = false, columnDefinition = "ENUM('COMPLETE', 'BASIC')")
	@Enumerated(EnumType.STRING)
	private WashType washType;

	@Column(nullable = false, name = "wash_price")
	private Integer price;

	@Column(name = "dt_reservation")
	@Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
	private LocalDateTime dtReservation;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "client_id")
	@JsonIgnore
	private Client client;

	public Wash(Long idWash, WashType washType, Integer price, LocalDateTime dtReservation) {
		super();
		this.idWash = idWash;
		this.washType = washType;
		this.price = price;
		this.dtReservation = dtReservation;
	}

	public Wash(WashDTO washDto) {
		this.idWash = washDto.getId();
		this.washType = washDto.getWashType();
		this.price = washDto.getPrice();
		this.dtReservation = washDto.getDtReservation();
		this.client = washDto.getClient();
	}
	
}
