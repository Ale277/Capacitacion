package com.wizeline.spring.data.mongodb.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;
import com.wizeline.spring.data.mongodb.model.BankAccountCrud;
import com.wizeline.spring.data.mongodb.repository.BankAccountCrudRepository;

@ExtendWith(MockitoExtension.class)
class UsersControllerTest {

	@Mock
	private BankAccountCrud bankAccountCrud;

	@InjectMocks
	private UsersController usersController;

	@Mock
	private BankAccountCrudRepository bankAccountCrudRepository;

	@Test
	void SeObtienenTodosLosUsersCon_testGetAllBank() {
		List<BankAccountCrud> bankAccountCrud = new ArrayList<BankAccountCrud>();

		BankAccountCrud bankAccountCrudUser = new BankAccountCrud();
		bankAccountCrudUser.setAccountName("Luis");
		bankAccountCrudUser.setPublished(true);
		bankAccountCrudUser.setUserName("Prueba");

		bankAccountCrud.add(0, bankAccountCrudUser);

		String accountName = "Prueba";

		when(bankAccountCrudRepository.findByAccountNameContaining(accountName)).thenReturn(bankAccountCrud);

		ResponseEntity<List<BankAccountCrud>> entity = usersController.getAllBank(accountName);

		String RESULTADO_ESPERADO = "<200 OK OK,[Tutorial [id=null, accountName=Luis, userName=Prueba, accountBalance=true]],[]>";

		Assertions.assertEquals(RESULTADO_ESPERADO, entity.toString());

		System.out.println("Test Get all Exitoso");
	}

	@Test
	void SeObtieneUnUserCon_testGetBankById() {

		Optional<BankAccountCrud> bankAccountCrudUser = Optional.of(new BankAccountCrud());

		String id = "123456789";

		when(bankAccountCrudRepository.findById(id)).thenReturn(bankAccountCrudUser);

		ResponseEntity<BankAccountCrud> entity = usersController.getBankById(id);

		String RESULTADO_ESPERADO = "<200 OK OK,Tutorial [id=null, accountName=null, userName=null, accountBalance=false],[]>";

		Assertions.assertEquals(RESULTADO_ESPERADO, entity.toString());
		System.out.println("Test Get por id Exitoso");

	}

	@Test
	void MetodoParaCrearUsers_testCreateUser() {

		BankAccountCrud bankAccountCrudUser = new BankAccountCrud();
		bankAccountCrudUser.setAccountName("Luis");
		bankAccountCrudUser.setPublished(true);
		bankAccountCrudUser.setUserName("Prueba");

		when(bankAccountCrudRepository.save(Mockito.any())).thenReturn(bankAccountCrudUser);

		ResponseEntity<BankAccountCrud> entity = usersController.createUser(bankAccountCrudUser);

		String RESULTADO_ESPERADO = "<201 CREATED Created,Tutorial [id=null, accountName=Luis, userName=Prueba, accountBalance=true],[]>";

		Assertions.assertEquals(RESULTADO_ESPERADO, entity.toString());

		System.out.println("Test Post Exitoso");

	}

	@Test
	void MetodoParaModificarUsersPorId_testUpdateUser() {

		Optional<BankAccountCrud> bankAccountCrudUser = Optional.of(new BankAccountCrud());

		BankAccountCrud bankAccountCrudDeUser = new BankAccountCrud();
		bankAccountCrudDeUser.setAccountName("Luis");
		bankAccountCrudDeUser.setPublished(true);
		bankAccountCrudDeUser.setUserName("Prueba");

		String id = "12345";

		when(bankAccountCrudRepository.findById(id)).thenReturn(bankAccountCrudUser);

		ResponseEntity<BankAccountCrud> entity = usersController.updateUser(id, bankAccountCrudDeUser);

		String RESULTADO_ESPERADO = "<200 OK OK,[]>";

		Assertions.assertEquals(RESULTADO_ESPERADO, entity.toString());

		System.out.println("Test Put Exitoso");

	}

	@Test
	void testDeleteUser() {

		String id = "12345";

		ResponseEntity<HttpStatus> entity = usersController.deleteUser(id);

		String RESULTADO_ESPERADO = "<204 NO_CONTENT No Content,[]>";

		Assertions.assertEquals(RESULTADO_ESPERADO, entity.toString());

		System.out.println("Test Delete Exitoso");

	}

	@Test
	void MetodoParaBorrarAllUsers_testDeleteAllUsers() {

		ResponseEntity<HttpStatus> entity = usersController.deleteAllUsers();

		String RESULTADO_ESPERADO = "<204 NO_CONTENT No Content,[]>";

		Assertions.assertEquals(RESULTADO_ESPERADO, entity.toString());

		System.out.println("Test Delete all Exitoso");

	}

}
