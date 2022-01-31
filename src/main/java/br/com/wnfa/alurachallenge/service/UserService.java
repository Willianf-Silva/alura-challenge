package br.com.wnfa.alurachallenge.service;

import br.com.wnfa.alurachallenge.dto.request.UserRequestDTO;
import br.com.wnfa.alurachallenge.dto.response.UserResponseDTO;

public interface UserService {

	UserResponseDTO createUser(UserRequestDTO userRequestDTO);

}
