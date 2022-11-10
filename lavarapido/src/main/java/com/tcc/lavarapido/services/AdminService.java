package com.tcc.lavarapido.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcc.lavarapido.enums.Profile;
import com.tcc.lavarapido.exceptions.ClientException;
import com.tcc.lavarapido.models.Admin;
import com.tcc.lavarapido.models.Client;
import com.tcc.lavarapido.models.dto.UserDTO;
import com.tcc.lavarapido.repositories.AdminRepository;

@Service
public class AdminService {

	@Autowired
	final AdminRepository adminRepository;

	public AdminService(AdminRepository adminRepository) {
		super();
		this.adminRepository = adminRepository;
	}

	public static final String ADMIN_NOT_FOUND = "There isn't a admin with id = ";

	@Transactional
	public Admin createAdmin(UserDTO adminDto) {

		try {

			adminDto.setId(null);
			adminDto.setPassword(adminDto.getPassword());

//		clientDto.setPassword(encoder.encode(clientDto.getPassword()));

			validaCpfAndEmail(adminDto);
			adminDto.setCpf(adminDto.getCpf());

		} catch (Exception e) {
			e.printStackTrace();
		}

		Admin newAdmin = new Admin(adminDto);

		return adminRepository.save(newAdmin);
	}
	
	public List<Admin> findAll() {
        return adminRepository.findAll();
    }
	
	public Admin findById (Long id) {
		verifyIfIsAdmin(id);
		return adminRepository.findById(id).
			orElseThrow(() -> new ClientException(ADMIN_NOT_FOUND + id, HttpStatus.NOT_FOUND));
	}
	
	private void verifyIfIsAdmin(Long id) {
		Optional<Admin> obj = adminRepository.findById(id);
		if(!obj.isPresent() || !obj.get().getProfile().equals(Profile.ADMIN)) {
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
		adminRepository.deleteById(id);
	}

	private void verifyIfExists(Long id) throws Exception {
		Optional<Admin> obj = adminRepository.findById(id);
		if (!obj.isPresent()) {
			throw new ClientException(ADMIN_NOT_FOUND + id, HttpStatus.NOT_FOUND);
		}

	}

	private void validaCpfAndEmail(UserDTO adminDto) throws Exception {
		Optional<Client> obj = adminRepository.findByCpf(adminDto.getCpf());
		if (obj.isPresent() && obj.get().getId_user() != adminDto.getId()) {
			throw new Exception("CPF Já Cadastrado no Sistema");
		}

		obj = adminRepository.findByEmail(adminDto.getEmail());
		if (obj.isPresent() && obj.get().getId_user() != adminDto.getId()) {
			throw new Exception("E-mail já cadastrado no sistema.");
		}

	}

}
