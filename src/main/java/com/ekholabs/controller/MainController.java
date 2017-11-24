package com.ekholabs.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ekholabs.dto.ClientDto;
import com.ekholabs.dto.PetDto;
import com.ekholabs.model.Client;
import com.ekholabs.model.Pet;
import com.ekholabs.service.ClientService;
import com.ekholabs.service.PetService;

@RestController
@RequestMapping(path = "/vet")
public class MainController {
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private PetService petService;
	
	@Autowired
    private ModelMapper modelMapper;
	
	@GetMapping(path = "/client/getAll")
	public List<Client> getAllClients() {
		return clientService.findAll();
	}
	
	@GetMapping(path = "/client/{clientId}")
	public ClientDto getClient(@PathVariable(value = "clientId") int clientId) {
		Client client = clientService.findById(clientId);
		return convertClientToDto(client);
	}
	
	@PostMapping(path = "/client/save")
	@ResponseStatus(HttpStatus.CREATED)
	public Client createClient(@RequestBody @Validated ClientDto client) {	
		Client newClient = new Client(client.getFullName(), client.getEmail());
		return clientService.save(newClient);
	}
	
	@RequestMapping(path = "/client/{userId}", method = RequestMethod.PATCH)
	public ClientDto updateClient(@PathVariable(value = "clientId") int clientId,
			@RequestBody @Validated ClientDto clientDto) {
		
		Client client = clientService.findById(clientId);
		if (client == null) {
			throw new NoSuchElementException("Client does not exist: " + clientId);
		}
		
		if (clientDto.getEmail() != null) {
			client.setEmail(clientDto.getEmail());
		}
		return convertClientToDto(clientService.save(client));		
	}
	
	@RequestMapping(path = "/client/{clientId}", method = RequestMethod.DELETE)
	public void deleteClient(@PathVariable(value = "clientId") int clientId) {
		Client client = clientService.findById(clientId);
		if (client == null) {
			throw new NoSuchElementException("Client does not exist: " + clientId);
		}
		clientService.delete(client);
	}
	
	private ClientDto convertClientToDto(Client client) {		
		ClientDto clientDto = modelMapper.map(client, ClientDto.class);	
		List<Pet> pets = client.getPets();
		if (pets != null) {
			List<String> petsDesc = pets.stream().map(Pet::getName).collect(Collectors.toList());
			clientDto.setPets(petsDesc);
		}				
		return clientDto;		
	}
	
	@GetMapping(path = "/pet/getAll")
	public List<Pet> getAllPetss() {
		return petService.findAll();
	}
	
	@GetMapping(path = "/pet/{petId}")
	public PetDto getPet(@PathVariable(value = "petId") int petId) {
		Pet pet = petService.findById(petId);
		return convertPetToDto(pet);
	}
	
	@PostMapping(path = "/pet/save")
	@ResponseStatus(HttpStatus.CREATED)
	public void createPet(@RequestBody @Validated PetDto pet) {	
		Client owner = clientService.findById(pet.getOwnerId());
		Pet newPet = new Pet(pet.getName(), pet.getCategory(), pet.getBirthday(), owner);
		petService.save(newPet);
	}
	
	@RequestMapping(path = "/pet/{petId}", method = RequestMethod.PATCH)
	public PetDto updateClient(@PathVariable(value = "petId") int petId,
			@RequestBody @Validated PetDto petDto) {
		
		Pet pet = petService.findById(petId);
		if (pet == null) {
			throw new NoSuchElementException("Pet does not exist: " + petId);
		}
		
		if (petDto.getName() != null) {
			pet.setName(petDto.getName());
		}
		return convertPetToDto(petService.save(pet));		
	}
	
	@RequestMapping(path = "/pet/{petId}", method = RequestMethod.DELETE)
	public void deletePet(@PathVariable(value = "petId") int petId) {
		Pet pet = petService.findById(petId);
		if (pet == null) {
			throw new NoSuchElementException("Pet does not exist: " + petId);
		}
		petService.delete(pet);
	}
	
	private PetDto convertPetToDto(Pet pet) {
		PetDto petDto = modelMapper.map(pet, PetDto.class);
		petDto.setOwnerDescription(pet.getOwner().getFullName());
		petDto.setOwnerId(pet.getOwner().getId());
		return petDto;
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return400(NoSuchElementException ex) {
        return ex.getMessage();
    }

}
