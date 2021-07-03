package one.digitalinnovaton.personapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovaton.personapi.dto.request.PersonDTO;
import one.digitalinnovaton.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovaton.personapi.entity.Person;
import one.digitalinnovaton.personapi.mapper.PersonMapper;
import one.digitalinnovaton.personapi.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	private PersonRepository personRepository;
	
	private final PersonMapper personMapper = PersonMapper.INSTANCE;
	
	public MessageResponseDTO createPerson(PersonDTO personDTO) {
		
		Person personToSave = personMapper.toModel(personDTO);
		
		Person p = personRepository.save(personToSave);		
		return MessageResponseDTO
				.builder()
				.message("Create Person " + p.getId())
				.build();
	}

}
