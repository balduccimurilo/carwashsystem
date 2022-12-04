package com.tcc.lavarapido.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
	
	public static final String DATE_ERROR = "Date need to be more then today or date is reservated ";
	
	public static final String CLIENT_CPF_NOT_FOUND = "There isn't a client with cpf or id = ";
	
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
		Integer pricePerType = pricePerType(washForm.getWashType());

		if(pricePerType != null) {
			washDto.setPrice(pricePerType);
		}
		
		washDto.setCarro(washForm.getCarro());
		washDto.setPlaca(washForm.getPlaca());
		
		washDto.setWashType(washForm.getWashType());
		
		LocalDateTime dateTime = LocalDateTime.parse(washForm.getDtReservation(), formatter);
		boolean existsDtReservation = washRepository.existsBydtReservation(dateTime);
		if(dateTime.isAfter(LocalDateTime.now()) && !existsDtReservation) {
			washDto.setDtReservation(dateTime);	
		}else {
			throw new WashException(DATE_ERROR, HttpStatus.BAD_REQUEST);
		}
		
		User client = null;
		
		if(washForm.getCpfClient() != null) {
			client = findClientByCpf(washForm.getCpfClient());
		}else {
			client = findClientById(washForm.getIdClient());
		}
		
			
		washDto.setClientName(client.getName());
		
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
	
	public List<Wash> findByUserId(Long id) {
		
		List<Wash> washListFiltered = new ArrayList<Wash>();
		
		Optional<User> user = userRepository.findById(id);
		if(user.isPresent()) {
			Set<Wash> washes = user.get().getWashes();
			for(Wash wash : washes) {
				washListFiltered.add(wash);
			}
		}else {
			throw new ClientException(CLIENT_CPF_NOT_FOUND + id, HttpStatus.NOT_FOUND);
		}
		
		return washListFiltered;
	}
	
	public Wash findById(Long id) {
		return washRepository.findById(id)
				.orElseThrow(() -> new WashException(WASH_NOT_FOUND + id, HttpStatus.NOT_FOUND));
	}
	
	public User findClientByCpf(String cpfClient) {
		return userRepository.findByCpf(cpfClient)
				.orElseThrow(() -> new ClientException(CLIENT_CPF_NOT_FOUND + cpfClient, HttpStatus.NOT_FOUND));
		
	}
	
	public User findClientById(Long idClient) {
		return userRepository.findById(idClient)
				.orElseThrow(() -> new ClientException(CLIENT_CPF_NOT_FOUND + idClient, HttpStatus.NOT_FOUND));
		
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
	
	private Integer pricePerType(WashType washType) {
		switch (washType) {

		case BASIC:

		return 20;

		case COMPLETACERA:

		return 60;


		case COMPLETE:

		return 50;

		
		case INTERNA:

		return 30;
			
		case POLIMENTO:

		return 300;
			
		case PRETINHO:

		return 10;

		default:

		return 20;

		}

	}


}
