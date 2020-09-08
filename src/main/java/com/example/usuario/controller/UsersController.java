package com.example.usuario.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.usuario.entity.User;
import com.example.usuario.exception.UserNotFoundException;
import com.example.usuario.service.UserService;

import java.util.List;

import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UsersController {

	/*Mostrar en consola la acción de la petición*/
	private static final Log log = LogFactory.getLog(UsersController.class);

	private UserService usersService;
	private User user;

	@Autowired
	public UsersController(UserService usersService) {
		this.usersService = usersService;
	}

	/**
	 * http://localhost:4444/users/1
	 * */
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET)
	public ResponseEntity<User> userById(@PathVariable String userId) throws UserNotFoundException {
		log.info("Get userById");
		try {
			user = usersService.findByUserId(userId);
		} catch (UserNotFoundException e) {
			user = null;
		}
		return ResponseEntity.ok(user);

	}

	/**
	 * http://localhost:4444/users/allusers
	 * */
	@RequestMapping(value = "/allusers", method = RequestMethod.GET)
	public ResponseEntity<List<User>> userById() {
		log.info("Get allUsers");
		return ResponseEntity.ok(usersService.findAll());
	}

	/**
	 * http://localhost:4444/users/delete/{id_de_usuario_a_borrar}
	 * */
	@RequestMapping(value = "delete/{userId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
		log.info("Delete user " + userId);
		usersService.deleteUser(userId);
		return ResponseEntity.noContent().build();
	}

	/**
	 * http://localhost:4444/users/save
	 * 
	 * require {"userId":"string_id",  "name":"nombre_usuario"}
	 * */
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public ResponseEntity<User> saveUser(@RequestBody @Valid User user) {
		log.info("Save new user");
		return ResponseEntity.ok(usersService.saveUser(user));
	}

	/**
	 * http://localhost:4444/users/actualice
	 * 
	 * require {"userId":"string_id",  "name":"nombre_usuario"}
	 * */
	@RequestMapping(value="/actualice",method = RequestMethod.PUT)
	public ResponseEntity<Void> updateUser(@RequestBody @Valid User user) {
		log.info("update user " + user.getUserId());
		usersService.updateUser(user);
		return ResponseEntity.noContent().build();
	}

}
