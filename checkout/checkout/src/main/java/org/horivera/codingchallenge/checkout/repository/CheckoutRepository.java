package org.horivera.codingchallenge.checkout.repository;

import org.horivera.codingchallenge.checkout.domain.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CheckoutRepository extends MongoRepository<Order, String> {

}
