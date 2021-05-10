package com.example.demo.service;

import com.example.demo.dao.PersonDao;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonDao personDao;

    @Autowired
    public PersonService(@Qualifier("FakePerson") PersonDao personDao) {
        this.personDao = personDao;
    }

    public void addPerson(int id, Person p) {
        personDao.insertPerson(id, p);
    }

    public List<Person> getPersons() {
        return personDao.getAll();
    }

    public Optional<Person> getPersonById(int id) {
        return personDao.selectPersonById(id);
    }

    public void deletePerson(int id) {
        personDao.deletePersonById(id);
    }

    public void updatePerson(int id, Person person) {
        personDao.updatePersonById(id, person);
    }
}
