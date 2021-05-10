package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository("FakePerson")
public class FakePerson implements PersonDao {
    private static final List<Person> database = new ArrayList<>();
    @Override
    public void insertPerson(int id, Person person) {
        database.add(new Person(id, person.getName()));
    }

    @Override
    public List<Person> getAll() {
        return database;
    }

    @Override
    public Optional<Person> selectPersonById(int id) {
        return database.stream()
                .filter(player -> player.getId() == id)
                .findFirst();
    }

    @Override
    public void deletePersonById(int id) {
        Optional<Person> player = selectPersonById(id);
        player.ifPresent(database::remove);
    }

    @Override
    public void updatePersonById(int id, Person person) {
        selectPersonById(id).map(p -> {
            int idPlayer = database.indexOf(p);
            if (idPlayer >= 0) {
                database.set(idPlayer, new Person(id, p.getName()));
                return 1;
            }
            return 0;
        });
    }
}
