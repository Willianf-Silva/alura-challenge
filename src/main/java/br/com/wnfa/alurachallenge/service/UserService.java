package br.com.wnfa.alurachallenge.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.wnfa.alurachallenge.dto.request.UserRequestDTO;
import br.com.wnfa.alurachallenge.dto.response.UserResponseDTO;

public interface UserService {

	UserResponseDTO createUser(UserRequestDTO userRequestDTO) throws Exception;

	UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) throws Exception;

	Page<UserResponseDTO> findAll(Pageable pageable);

	UserResponseDTO findById(Long id) throws Exception;

	void deleteById(Long id) throws Exception;

}
