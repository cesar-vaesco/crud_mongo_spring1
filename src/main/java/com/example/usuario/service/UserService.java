package com.example.usuario.service;

import java.util.List;

import com.example.usuario.entity.User;

public interface UserService {

	List<User> findAll();

	User findByUserId(String userId);

	User saveUser(User user);

	void updateUser(User user);

	void deleteUser(String userId);
}
