package com.tcc.lavarapido.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcc.lavarapido.enums.Profile;
import com.tcc.lavarapido.exceptions.ClientException;
import com.tcc.lavarapido.models.Client;
import com.tcc.lavarapido.models.dto.UserDTO;
import com.tcc.lavarapido.repositories.ClientRepository;

@Service
public class ClientService {
	
	public static final String CLIENT_NOT_FOUND = "There isn't a client with id = ";
	
	@Autowired
	final ClientRepository clientRepository;
	
//	@Autowired
//	private BCryptPasswordEncoder encoder;

	public ClientService(ClientRepository clientRepository) {
		super();
		this.clientRepository = clientRepository;
	}

	public List<Client> findAll() {
        return clientRepository.findAll();
    }
	
	public Client findById (Long id) {
		verifyIfIsClient(id);
		return clientRepository.findById(id).
			orElseThrow(() -> new ClientException(CLIENT_NOT_FOUND + id, HttpStatus.NOT_FOUND));
	}
	
	private void verifyIfIsClient(Long id) {
		Optional<Client> obj = clientRepository.findById(id);
		if(!obj.isPresent() || !obj.get().getProfile().equals(Profile.CLIENT)) {
			throw new ClientException(CLIENT_NOT_FOUND + id, HttpStatus.NOT_FOUND);
		}
	}

	@Transactional
	public Client createClient(UserDTO clientDto) {
		
		Client newClient = null;
		
		try {
			
		clientDto.setId(null);
		clientDto.setPassword(clientDto.getPassword());
		
//		clientDto.setPassword(encoder.encode(clientDto.getPassword()));
		
		validaCpfAndEmail(clientDto);
		clientDto.setCpf(clientDto.getCpf());
		
		newClient = new Client(clientDto);
		
		
		
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return clientRepository.save(newClient);
	}
	
	@Transactional
	public void delete(Long id) {
		try {
			verifyIfExists(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		clientRepository.deleteById(id);
	}
	
	private void verifyIfExists(Long id) throws Exception {
		Optional<Client> obj = clientRepository.findById(id);
		if(!obj.isPresent()) {
			throw new ClientException(CLIENT_NOT_FOUND + id, HttpStatus.NOT_FOUND);
		}
		
	}
	
	public Client findClientByCpf(String cpfClient) {
		return clientRepository.findByCpf(cpfClient)
				.orElseThrow(() -> new ClientException(CLIENT_NOT_FOUND + cpfClient, HttpStatus.NOT_FOUND));
		
	}

	private void validaCpfAndEmail(UserDTO clientDto) throws Exception {
        Optional<Client> obj = clientRepository.findByCpf(clientDto.getCpf());
        if(obj.isPresent() && obj.get().getId_user() != clientDto.getId()){
          throw new ClientException("CPF Já Cadastrado no Sistema", HttpStatus.BAD_REQUEST);
        }
 
        obj = clientRepository.findByEmail(clientDto.getEmail());
        if(obj.isPresent() && obj.get().getId_user() != clientDto.getId()){
         throw new ClientException("E-mail já cadastrado no sistema.", HttpStatus.BAD_REQUEST);
        }
 
     }
	
	
}
