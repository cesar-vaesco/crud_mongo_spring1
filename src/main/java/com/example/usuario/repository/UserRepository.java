package com.example.usuario.repository;

import java.util.List;
import java.util.Optional;

import com.example.usuario.entity.User;

public interface UserRepository {

	Optional<List<User>> findAll();

	public Optional<User> findOne(String userId);

	public User saveUser(User user);

	public void updateUser(User user);

	public void deleteUser(String userId);
}
