package org.horivera.codingchallenge.logistic.repository;

import org.horivera.codingchallenge.logistic.domain.SentOrder;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogisticRepository extends MongoRepository<SentOrder, String> {

}
