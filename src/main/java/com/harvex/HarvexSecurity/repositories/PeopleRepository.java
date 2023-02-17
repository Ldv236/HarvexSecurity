package com.harvex.HarvexSecurity.repositories;

import com.harvex.HarvexSecurity.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository <Person, Integer> {
    Optional<Person> findByUsername(String username); //optional - for case when user not found
}
