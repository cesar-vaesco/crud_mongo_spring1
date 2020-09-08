package com.example.usuario.service;

import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.usuario.entity.User;
import com.example.usuario.exception.UserNotFoundException;
import com.example.usuario.repository.UserRepository;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	private static final Log log = LogFactory.getLog(UserServiceImpl.class);
	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<User> findAll() {
		Optional<List<User>> user = userRepository.findAll();
		return user.get();
	}

	public User findByUserId(String userId) {
		Optional<User> user = userRepository.findOne(userId);
		if (user.isPresent()) {
			log.debug(String.format("Read userId '{}'", userId));
			return user.get();
		} else
			throw new UserNotFoundException(userId);
	}

	@Override
	public User saveUser(User user) {
		return userRepository.saveUser(user);
	}

	@Override
	public void updateUser(User user) {
		userRepository.updateUser(user);

	}

	@Override
	public void deleteUser(String userId) {
		userRepository.deleteUser(userId);
	}

}
