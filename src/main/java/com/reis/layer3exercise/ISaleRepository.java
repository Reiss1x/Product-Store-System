package com.reis.layer3exercise;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ISaleRepository extends MongoRepository<Sale, ObjectId> {
    
}
