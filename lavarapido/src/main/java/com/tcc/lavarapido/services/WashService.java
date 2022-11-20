package com.tcc.lavarapido.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcc.lavarapido.enums.WashType;
import com.tcc.lavarapido.exceptions.ClientException;
import com.tcc.lavarapido.exceptions.WashException;
import com.tcc.lavarapido.forms.WashForm;
import com.tcc.lavarapido.models.User;
import com.tcc.lavarapido.models.Wash;
import com.tcc.lavarapido.models.dto.WashDTO;
import com.tcc.lavarapido.repositories.UserRepository;
import com.tcc.lavarapido.repositories.WashRepository;

@Service
public class WashService {
	
	
	private final WashRepository washRepository;
	private final UserRepository userRepository;
	
	public static final String WASH_NOT_FOUND = "There isn't a wash registred! ";
	
	public static final String CLIENT_CPF_NOT_FOUND = "There isn't a client with cpf = ";
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

	@Autowired
	public WashService(WashRepository washRepository, UserRepository userRepository) {
		super();
		this.washRepository = washRepository;
		this.userRepository = userRepository;
	}
	
	@Transactional
	public Wash createWash(WashForm washForm) {
		
		WashDTO washDto = new WashDTO();
		
		Wash newWash = null;
		
		try {
			
		washDto.setId(null);
		if(washForm.getWashType().equals(WashType.BASIC)) {
			washDto.setPrice(20);
		} else {
			washDto.setPrice(50);
		}
		washDto.setWashType(washForm.getWashType());
		
		LocalDateTime dateTime = LocalDateTime.parse(washForm.getDtReservation(), formatter);
		washDto.setDtReservation(dateTime);
		
		User client = findClientByCpf(washForm.getCpfClient());
		
		if(client != null) {
			washDto.setUser(client);;
		}
		
		newWash = new Wash(washDto);

		
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return washRepository.save(newWash);
	}
	
	public List<Wash> findAll() {
		return washRepository.findAll();
	}
	
	public Wash findById(Long id) {
		return washRepository.findById(id)
				.orElseThrow(() -> new WashException(WASH_NOT_FOUND + id, HttpStatus.NOT_FOUND));
	}
	
	public User findClientByCpf(String cpfClient) {
		return userRepository.findByCpf(cpfClient)
				.orElseThrow(() -> new ClientException(CLIENT_CPF_NOT_FOUND + cpfClient, HttpStatus.NOT_FOUND));
		
	}

	@Transactional
	public void delete(Long id) {
		try {
			findById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		userRepository.deleteById(id);
	}

}
