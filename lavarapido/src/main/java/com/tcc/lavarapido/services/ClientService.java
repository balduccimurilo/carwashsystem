package com.tcc.lavarapido.services;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.tcc.lavarapido.exceptions.ClientException;
import com.tcc.lavarapido.models.Client;
import com.tcc.lavarapido.models.dto.ClientDTO;
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
		return clientRepository.findById(id).
			orElseThrow(() -> new ClientException(CLIENT_NOT_FOUND + id, HttpStatus.NOT_FOUND));
	}
	
	public Client createClient(ClientDTO clientDto) {
		
		try {
			
		clientDto.setId(null);
		clientDto.setPassword(clientDto.getPassword());
//		clientDto.setPassword(encoder.encode(clientDto.getPassword()));
		
		validaCpfAndEmail(clientDto);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Client newClient = new Client(clientDto);
		
		return clientRepository.save(newClient);
	}
	
	private void validaCpfAndEmail( ClientDTO clientDto) throws Exception {
        Optional<Client> obj = clientRepository.findByCpf(clientDto.getCpf());
        if(obj.isPresent() && obj.get().getId_user() != clientDto.getId()){
          throw new Exception("CPF Já Cadastrado no Sistema");
        }
 
        obj = clientRepository.findByEmail(clientDto.getEmail());
        if(obj.isPresent() && obj.get().getId_user() != clientDto.getId()){
         throw new Exception("E-mail já cadastrado no sistema.");
        }
 
     }
}
