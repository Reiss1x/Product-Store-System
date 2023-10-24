package com.reis.layer3exercise.domain;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.reis.layer3exercise.entities.Sale;


@Repository
public interface ISaleRepository extends MongoRepository<Sale, ObjectId> {
    
}
