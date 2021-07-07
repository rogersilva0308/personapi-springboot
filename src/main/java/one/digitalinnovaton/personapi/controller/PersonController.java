package one.digitalinnovaton.personapi.controller;

import java.util.List;

import javax.validation.Valid;

import one.digitalinnovaton.personapi.exception.PersonNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import one.digitalinnovaton.personapi.dto.request.PersonDTO;
import one.digitalinnovaton.personapi.dto.response.MessageResponseDTO;
import one.digitalinnovaton.personapi.service.PersonService;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

	@Autowired
	private PersonService personService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO) {
		return personService.createPerson(personDTO);
	}
	
	@GetMapping
	public List<PersonDTO> listAll(){
		return personService.listAll();
	}

	@GetMapping("/{id}")
	public PersonDTO findByID(@PathVariable Long id) throws PersonNotFoundException {
		return personService.findByID(id);
	}

	@PutMapping("/{id}")
	public MessageResponseDTO updateByID(@PathVariable Long id, @RequestBody PersonDTO personDTO) throws PersonNotFoundException {
		return personService.updateByID(id, personDTO);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteByID(@PathVariable Long id) throws PersonNotFoundException {
		personService.delete(id);
	}

}
