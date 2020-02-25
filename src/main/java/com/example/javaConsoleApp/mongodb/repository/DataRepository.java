package com.example.javaConsoleApp.mongodb.repository;

import com.example.javaConsoleApp.mongodb.model.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DataRepository extends MongoRepository<Data, String> {
    Page<Data> findAll(Pageable pageable);
}
