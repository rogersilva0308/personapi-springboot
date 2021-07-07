package one.digitalinnovaton.personapi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import one.digitalinnovaton.personapi.exception.PersonNotFoundException;
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
		return createMessageResponse("Create person with id " + p.getId());
	}

	public List<PersonDTO> listAll() {
		List<Person> lista = personRepository.findAll();
		return lista.stream()
				.map(personMapper::toDTO)
				.collect(Collectors.toList());
	}

	public PersonDTO findByID(Long id) throws PersonNotFoundException {
		Person person = verifyIfExists(id);
		return personMapper.toDTO(person);
	}

	public void delete(Long id) throws PersonNotFoundException {
		verifyIfExists(id);
		personRepository.deleteById(id);
	}

	public MessageResponseDTO updateByID(Long id, PersonDTO personDTO) throws PersonNotFoundException {
		verifyIfExists(id);
		Person personToUpdtate = personMapper.toModel(personDTO);
		Person p = personRepository.save(personToUpdtate);
		return createMessageResponse("Update person with id " + id);
	}

	private Person verifyIfExists(Long id) throws PersonNotFoundException {
		return personRepository.findById(id)
				.orElseThrow(() -> new PersonNotFoundException(id));
	}

	private MessageResponseDTO createMessageResponse(String message) {
		return MessageResponseDTO
				.builder()
				.message(message)
				.build();
	}
}
