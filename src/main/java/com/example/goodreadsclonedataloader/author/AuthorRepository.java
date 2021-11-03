package com.example.goodreadsclonedataloader.author;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CassandraRepository<Author, String> {
    //We specify the type of the entity class and the type of the id for the entity class
}
