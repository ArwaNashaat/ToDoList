package com.example.todo;


import Controllers.*;
import Entities.*;
import Repositories.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
class TodoApplicationTests {
	@Autowired
	private ToDoRepository todoRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ToDoController todoController;
	@Autowired
	private UserController userController;

	@Test
	void contextLoads() {
		assertThat(todoController).isNotNull();
	}

	@Test
	void addToDoTest(){
		ToDo todo = new ToDo(1L,"default description", 1, false,LocalDate.now());
		todoController.addToDo(todo);
		Optional<ToDo> todoSaved = todoRepository.findById(1L);
		assertEquals(todoSaved.get(),todo);
		todoRepository.delete(todo);
	}

	@Test
	void registerUserTest(){
		User user = new User(1L, "userEmail@s.com", "userName", "userPassword22<@");
		ResponseEntity responseEntity = userController.registerUser(user);
		assertEquals(responseEntity,new ResponseEntity(user, HttpStatus.OK));
		userRepository.delete(user);
	}

	@Test
	void failRegisteringUserTest(){
		User user = new User(1L, "userEmail@s", "userName", "userPassword22<@");
		ResponseEntity responseEntity = userController.registerUser(user);
		assertEquals(responseEntity,new ResponseEntity("Wrong Email or Password", HttpStatus.BAD_REQUEST));
	}

	@Test
	void validateUserEmailTest(){
		User user = new User(1L, "userEmail@s.com", "userName", "userPassword22<@");
		Boolean validEmail = userController.validateEmail(user.getEmail());
		assertTrue(validEmail);
	}

	@Test
	void validateUserPasswordTest(){
		User user = new User(1L, "userEmail@s.com", "userName", "userPassword22<@");
		Boolean validPassword = userController.validatePassword(user.getPassword());
		assertTrue(validPassword);
	}
}
