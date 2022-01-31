package br.com.wnfa.alurachallenge.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wnfa.alurachallenge.dto.request.UserRequestDTO;
import br.com.wnfa.alurachallenge.dto.response.UserResponseDTO;
import br.com.wnfa.alurachallenge.entity.UserDO;
import br.com.wnfa.alurachallenge.mapper.UserMapper;
import br.com.wnfa.alurachallenge.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	
	private final UserMapper userMapper = UserMapper.INSTANCE;

	@Override
	public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
		UserDO user = userMapper.toModel(userRequestDTO);
		user.setActive(Boolean.TRUE);
		user.setDateCreate(LocalDate.now());
		
		return userMapper.toResponseDTO(userRepository.save(user));
	}
}
