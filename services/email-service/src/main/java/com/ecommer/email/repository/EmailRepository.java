package com.ecommer.email.repository;

import com.ecommer.email.model.entity.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmailRepository extends MongoRepository<Notification, Integer> {
}
