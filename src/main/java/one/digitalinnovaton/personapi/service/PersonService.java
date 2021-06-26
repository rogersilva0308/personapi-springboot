package one.digitalinnovaton.personapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovaton.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovaton.personapi.entity.Person;
import one.digitalinnovaton.personapi.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	
	public MessageResponseDTO createPerson(Person person) {
		Person p = personRepository.save(person);
		
		return MessageResponseDTO
				.builder()
				.message("Create Person " + p.getId())
				.build();
	}

}
