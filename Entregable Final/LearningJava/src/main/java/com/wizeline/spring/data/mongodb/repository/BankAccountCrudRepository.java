package com.wizeline.spring.data.mongodb.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.wizeline.spring.data.mongodb.model.BankAccountCrud;

public interface BankAccountCrudRepository extends MongoRepository<BankAccountCrud, String> {
 
  List<BankAccountCrud> findByAccountNameContaining(String accountName);
}
