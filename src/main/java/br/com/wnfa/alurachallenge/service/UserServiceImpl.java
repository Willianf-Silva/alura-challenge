package br.com.wnfa.alurachallenge.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.wnfa.alurachallenge.dto.request.UserRequestDTO;
import br.com.wnfa.alurachallenge.dto.response.UserResponseDTO;
import br.com.wnfa.alurachallenge.entity.RoleDO;
import br.com.wnfa.alurachallenge.entity.UserDO;
import br.com.wnfa.alurachallenge.exception.ResourceNotFoundException;
import br.com.wnfa.alurachallenge.exception.RolesNotFoundException;
import br.com.wnfa.alurachallenge.exception.UserAlreadyRegisteredException;
import br.com.wnfa.alurachallenge.mapper.UserMapper;
import br.com.wnfa.alurachallenge.repository.RoleRepository;
import br.com.wnfa.alurachallenge.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private final UserMapper userMapper = UserMapper.INSTANCE;

	@Override
	public UserResponseDTO createUser(UserRequestDTO userRequestDTO) throws Exception {
		verifyDuplicate(null, userRequestDTO.getUsername());
		UserDO user = userMapper.toModel(userRequestDTO);
		user.setActive(Boolean.TRUE);
		user.setDateCreate(LocalDate.now());
		user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
		user.setRoles(getRoles(userRequestDTO));
		
		return userMapper.toResponseDTO(userRepository.save(user));
	}

	@Override
	public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) throws Exception {
		verifyDuplicate(id, userRequestDTO.getUsername());
		UserDO userDO = verifyExists(id);
		userDO.setRoles(getRoles(userRequestDTO));
		userDO.setDateUpdate(LocalDate.now());
		userRequestDTO.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
		BeanUtils.copyProperties(userRequestDTO, userDO, "id");
		return userMapper.toResponseDTO(userRepository.save(userDO));
	}
	
	@Override
	public Page<UserResponseDTO> findAll(Pageable pageable) {
		Page<UserDO> users = userRepository.findAll(pageable);
		
		List<UserResponseDTO> response = users.stream()
				.map(userMapper::toResponseDTO)
				.collect(Collectors.toList());
		
		return new PageImpl<>(response, pageable, users.getTotalElements());
	}
	
	@Override
	public UserResponseDTO findById(Long id) throws Exception {
		UserDO userDO = verifyExists(id);
		return userMapper.toResponseDTO(userDO);
	}
	
	@Override
	public void deleteById(Long id) throws Exception {
		UserDO userDO = verifyExists(id);
		userDO.setActive(Boolean.FALSE);
		userDO.setDateUpdate(LocalDate.now());
		userRepository.save(userDO);
	}

	@Override
	public UserResponseDTO findByUsername(String username) throws Exception {
		List<UserDO> users = userRepository.findByUsername(username);
		if (users.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		return userMapper.toResponseDTO(users.get(0));
	}

	private UserDO verifyExists(Long id) throws ResourceNotFoundException {
		Optional<UserDO> userDO = userRepository.findById(id);
		if (userDO.isEmpty()) {
			throw new ResourceNotFoundException();
		}
		return userDO.get();
	}

	private Set<RoleDO> getRoles(UserRequestDTO userRequestDTO) throws Exception {
		Set<RoleDO> roles = userRequestDTO.getRoles().stream()
				.map(roleName -> roleRepository.findByRoleName(roleName.getRoleName()))
				.filter(role -> role != null)
				.collect(Collectors.toSet());
		if (roles.isEmpty()) {
			throw new RolesNotFoundException();
		}
				return roles;
	}

	private void verifyDuplicate(Long id, String user) throws Exception {
		List<UserDO> userDO = userRepository.findByUsername(user);

		// TODO Melhorar a l??gica da valida????o estudando boas praticas de programa????o

		if (userDO.size() == 1) {
			if (id == null || userDO.get(0).getId() != id) {
				throw new UserAlreadyRegisteredException();
			}
		}
		if (userDO.size() > 1) {
			throw new UserAlreadyRegisteredException();
		}
	}
}
