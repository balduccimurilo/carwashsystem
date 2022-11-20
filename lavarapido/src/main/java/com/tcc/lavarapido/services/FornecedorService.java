package com.tcc.lavarapido.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tcc.lavarapido.exceptions.FornecedorException;
import com.tcc.lavarapido.models.Fornecedor;
import com.tcc.lavarapido.models.dto.FornecedorDTO;
import com.tcc.lavarapido.repositories.FornecedorRepository;

@Service
public class FornecedorService {

	public static final String FORNECEDOR_NOT_FOUND = "There isn't a fornecedor with name = ";

	@Autowired
	final FornecedorRepository fornecedorRepository;

	public FornecedorService(FornecedorRepository fornecedorRepository) {
		super();
		this.fornecedorRepository = fornecedorRepository;
	}

	public List<Fornecedor> findAll() {
		return fornecedorRepository.findAll();
	}

	public Fornecedor findById(Long id) {
		verifyFornecedor(id);
		return fornecedorRepository.findById(id)
				.orElseThrow(() -> new FornecedorException(FORNECEDOR_NOT_FOUND + id, HttpStatus.NOT_FOUND));
	}

	public Fornecedor findByFantasyName(String fantasyName) {
		return fornecedorRepository.findByFantasyName(fantasyName)
				.orElseThrow(() -> new FornecedorException(FORNECEDOR_NOT_FOUND + fantasyName, HttpStatus.NOT_FOUND));
	}

	public Fornecedor findByCnpj(String cnpj) {
		return fornecedorRepository.findByCnpj(cnpj)
				.orElseThrow(() -> new FornecedorException(FORNECEDOR_NOT_FOUND + cnpj, HttpStatus.NOT_FOUND));
	}

	@Transactional
	public Fornecedor createFornecedor(FornecedorDTO fornecedorDto) {

		Fornecedor newFornecedor = null;

		try {

			fornecedorDto.setId(null);

			fornecedorDto.setCnpj(fornecedorDto.getCnpj().replaceAll("[./-]", "").trim());
			verifyCnpj(fornecedorDto);
			fornecedorDto.setCel(fornecedorDto.getCel().replaceAll("-", "").trim());

			newFornecedor = new Fornecedor(fornecedorDto);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return fornecedorRepository.save(newFornecedor);
	}

	@Transactional
	public void delete(Long id) {
		try {
			verifyFornecedor(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		fornecedorRepository.deleteById(id);
	}

	public void verifyFornecedor(Long id) {
		Optional<Fornecedor> obj = fornecedorRepository.findById(id);
		if (!obj.isPresent()) {
			throw new FornecedorException(FORNECEDOR_NOT_FOUND + id, HttpStatus.NOT_FOUND);
		}
	}

	private void verifyCnpj(FornecedorDTO fornecedorDto) {
		Optional<Fornecedor> obj = fornecedorRepository.findByCnpj(fornecedorDto.getCnpj());
		if (obj.isPresent() && obj.get().getIdFornecedor() != fornecedorDto.getId()) {
			throw new FornecedorException("CNPJ Já Cadastrado no Sistema", HttpStatus.BAD_REQUEST);
		}

	}

}
