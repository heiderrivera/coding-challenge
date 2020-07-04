package org.horivera.codingchallenge.bill.repository;

import org.horivera.codingchallenge.bill.domain.Bill;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends MongoRepository<Bill, String> {

}
