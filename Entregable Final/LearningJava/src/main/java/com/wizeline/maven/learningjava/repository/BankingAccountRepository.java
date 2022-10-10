

package com.wizeline.maven.learningjava.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.wizeline.maven.learningjava.model.BankAccountDTO;


@Repository
public interface BankingAccountRepository extends MongoRepository<BankAccountDTO, Long> {
}
