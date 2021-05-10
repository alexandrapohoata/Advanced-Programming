package com.example.demo.dao;
import com.example.demo.model.Person;

import java.util.List;
import java.util.Optional;

public interface PersonDao {

    void insertPerson(int id, Person person);

    List<Person> getAll();

    Optional<Person> selectPersonById(int id);

    void deletePersonById(int id);

    void updatePersonById(int id, Person person);
}
