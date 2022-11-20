package com.tcc.lavarapido.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcc.lavarapido.enums.IProfile;
import com.tcc.lavarapido.exceptions.ClientException;
import com.tcc.lavarapido.models.Profile;
import com.tcc.lavarapido.models.User;
import com.tcc.lavarapido.models.dto.UserDTO;
import com.tcc.lavarapido.repositories.UserRepository;

@Service
public class ClientService {

	public static final String CLIENT_NOT_FOUND = "There isn't a client with id = ";
	public static final String USER_IS_NOT_CLIENT = "This user is admin with id = ";

	@Autowired
	final UserRepository userRepository;

	public ClientService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	public List<User> findAll() {
		List<User> findAll = userRepository.findAll();
		List<User> auxList = new ArrayList<User>();

		for (User aux : findAll) {
			if(compareProfile(aux)) {
				auxList.add(aux);
			}
		}

		return auxList;
	}

	public User findById(Long id) {
		verifyIfIsClient(id);	
		
		return userRepository.findById(id)
				.orElseThrow(() -> new ClientException(CLIENT_NOT_FOUND + id, HttpStatus.NOT_FOUND));
	}

	private void verifyIfIsClient(Long id) {
		Optional<User> obj = userRepository.findById(id);
		
		 User user = new User(obj);

		boolean compareProfile = compareProfile(user);

		if (!obj.isPresent() || compareProfile != true) {
			throw new ClientException(USER_IS_NOT_CLIENT + id, HttpStatus.NOT_FOUND);
		}
	}

//	@Transactional
//	public User createClient(UserDTO clientDto) {
//
//		User newClient = null;
//
//		try {
//
//			clientDto.setId(null);
//			clientDto.setPassword(clientDto.getPassword());
//
////		clientDto.setPassword(encoder.encode(clientDto.getPassword()));
//
//			validaCpfAndEmail(clientDto);
//			clientDto.setCpf(clientDto.getCpf().replaceAll("[./-]", "").trim());
//			clientDto.setCel(clientDto.getCel().replaceAll("-", "").trim());
//
////		newClient = new Client(clientDto);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return userRepository.save(newClient);
//	}

	@Transactional
	public void delete(Long id) {
		try {
			verifyIfExists(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		userRepository.deleteById(id);
	}

	private void verifyIfExists(Long id) throws Exception {
		Optional<User> obj = userRepository.findById(id);
		if (!obj.isPresent()) {
			throw new ClientException(CLIENT_NOT_FOUND + id, HttpStatus.NOT_FOUND);
		}

	}

	private boolean compareProfile(User obj) {

		boolean compare = false;

		for (Profile profile : obj.getProfiles()) {
			if (profile.getProfile().equals(IProfile.CLIENT)) {
				compare = true;
			}
		}
		return compare;
	}

	public User findClientByCpf(String cpfClient) {
		return userRepository.findByCpf(cpfClient.replaceAll("[./-]", "").trim())
				.orElseThrow(() -> new ClientException(CLIENT_NOT_FOUND + cpfClient, HttpStatus.NOT_FOUND));
	}

}
