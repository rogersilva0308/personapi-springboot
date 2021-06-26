package one.digitalinnovaton.personapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import one.digitalinnovaton.personapi.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

}
