package com.tcc.lavarapido.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcc.lavarapido.enums.Role;
import com.tcc.lavarapido.exceptions.AdminException;
import com.tcc.lavarapido.exceptions.ClientException;
import com.tcc.lavarapido.models.Profile;
import com.tcc.lavarapido.models.User;
import com.tcc.lavarapido.models.dto.UserDTO;
import com.tcc.lavarapido.repositories.UserRepository;

@Service
public class AdminService {

	@Autowired
	final UserRepository userRepository;

	public AdminService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	public static final String ADMIN_NOT_FOUND = "There isn't a admin with id = ";

	@Transactional
	public User createAdmin(UserDTO adminDto) {

		try {

			adminDto.setId(null);
			adminDto.setPassword(adminDto.getPassword());

//		clientDto.setPassword(encoder.encode(clientDto.getPassword()));

			validaCpfAndEmail(adminDto);
			adminDto.setCpf(adminDto.getCpf());

		} catch (Exception e) {
			e.printStackTrace();
		}

//		Admin newAdmin = new Admin(adminDto);

//		return adminRepository.save(newAdmin);
		return null;
	}
	
	public List<User> findAll() {
		List<User> findAll = userRepository.findAll();
		for(User user : findAll) {
			String string = user.getProfiles().toString();
			if(user.getProfiles().toString().equals(Role.ADMIN)) {
				
			}
		}
        List<User> aux = new ArrayList<User>();
		
		return userRepository.findAll();
    }
	
	public User findById (Long id) {
		verifyIfIsAdmin(id);
		return userRepository.findById(id).
			orElseThrow(() -> new AdminException(ADMIN_NOT_FOUND + id, HttpStatus.NOT_FOUND));
	}
	
	private void verifyIfIsAdmin(Long id) {
		Optional<User> obj = userRepository.findById(id);
		if(!obj.isPresent() || !obj.get().getProfiles().equals(Role.ADMIN)) {
			throw new ClientException(ADMIN_NOT_FOUND + id, HttpStatus.NOT_FOUND);
		}
	}
	
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
			throw new ClientException(ADMIN_NOT_FOUND + id, HttpStatus.NOT_FOUND);
		}

	}

	private void validaCpfAndEmail(UserDTO adminDto) throws Exception {
		Optional<User> obj = userRepository.findByCpf(adminDto.getCpf());
		if (obj.isPresent() && obj.get().getId() != adminDto.getId()) {
			throw new Exception("CPF Já Cadastrado no Sistema");
		}

		obj = userRepository.findByEmail(adminDto.getEmail());
		if (obj.isPresent() && obj.get().getId() != adminDto.getId()) {
			throw new Exception("E-mail já cadastrado no sistema.");
		}

	}

}
