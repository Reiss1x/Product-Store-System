package com.reis.layer3exercise.domain;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import org.springframework.stereotype.Repository;

import com.reis.layer3exercise.entities.Product;


//interface que define o conjunto de métodos para acessar os dados do banco.
@Repository
public interface IStockRepository extends MongoRepository<Product, ObjectId> {
    Optional<Product> findProductByProdId(String prodId);
}
