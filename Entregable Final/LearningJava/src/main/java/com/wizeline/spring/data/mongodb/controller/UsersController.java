package com.wizeline.spring.data.mongodb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wizeline.spring.data.mongodb.model.BankAccountCrud;
import com.wizeline.spring.data.mongodb.repository.BankAccountCrudRepository;

//@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api/v1")
public class UsersController {

  @Autowired
  BankAccountCrudRepository bankAccountCrudRepository;

  @GetMapping("/user/All")
  public ResponseEntity<List<BankAccountCrud>> getAllBank(@RequestParam(required = false) String accountName) {
    try {
      List<BankAccountCrud> bankAccountCrud = new ArrayList<BankAccountCrud>();

      if (accountName == null)
        bankAccountCrudRepository.findAll().forEach(bankAccountCrud::add);
      else
        bankAccountCrudRepository.findByAccountNameContaining(accountName).forEach(bankAccountCrud::add);

      if (bankAccountCrud.isEmpty()) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
      }

      return new ResponseEntity<>(bankAccountCrud, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/user/{id}")
  public ResponseEntity<BankAccountCrud> getBankById(@PathVariable("id") String id) {
    Optional<BankAccountCrud> bankAccountCrud = bankAccountCrudRepository.findById(id);

    if (bankAccountCrud.isPresent()) {
      return new ResponseEntity<>(bankAccountCrud.get(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @PostMapping("/user/post")
  public ResponseEntity<BankAccountCrud> createUser(@RequestBody BankAccountCrud bankAccountCrud) {
    try {
      BankAccountCrud _bankAccountCrud = bankAccountCrudRepository.save(new BankAccountCrud(bankAccountCrud.getAccountName(), bankAccountCrud.getUserName(), false));
      return new ResponseEntity<>(_bankAccountCrud, HttpStatus.CREATED);
    } catch (Exception e) {
      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/user/put/{id}")
  public ResponseEntity<BankAccountCrud> updateUser(@PathVariable("id") String id, @RequestBody BankAccountCrud bankAccountCrud) {
    Optional<BankAccountCrud> BankAccountCrud = bankAccountCrudRepository.findById(id);

    if (BankAccountCrud.isPresent()) {
      BankAccountCrud _bankAccountCrud = BankAccountCrud.get();
      _bankAccountCrud.setAccountName(bankAccountCrud.getAccountName());
      _bankAccountCrud.setUserName(bankAccountCrud.getUserName());
      _bankAccountCrud.setPublished(bankAccountCrud.isPublished());
      return new ResponseEntity<>(bankAccountCrudRepository.save(_bankAccountCrud), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @DeleteMapping("/user/delete/{id}")
  public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") String id) {
    try {
      bankAccountCrudRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @DeleteMapping("/user/delete/All")
  public ResponseEntity<HttpStatus> deleteAllUsers() {
    try {
      bankAccountCrudRepository.deleteAll();
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }



}
