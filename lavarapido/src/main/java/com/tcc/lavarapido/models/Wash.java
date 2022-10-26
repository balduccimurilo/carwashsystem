package com.tcc.lavarapido.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;


import com.tcc.lavarapido.utils.WashType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
//@Table(name = "wash", schema = "carwash")
@Table(name = "TB_WASH")
public class Wash implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idWash;
	
	@Column(name = "id_user")
	private Long id_user;

	@Column(nullable = false, columnDefinition = "ENUM('COMPLETE', 'BASIC')")
	@Enumerated(EnumType.STRING)
	private WashType washType;

	@Column(nullable = false, name = "wash_price")
	private BigDecimal price;


	@Column(name = "dt_reservation")
	@Convert(converter = Jsr310JpaConverters.LocalDateTimeConverter.class)
	private LocalDateTime dtReservation;
	
	
}
