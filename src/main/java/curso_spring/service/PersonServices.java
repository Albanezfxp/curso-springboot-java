package curso_spring.service;

import curso_spring.controllers.PersonController;
import curso_spring.data.dto.PersonDto;
import curso_spring.exception.RequiredObjectIsNullException;
import curso_spring.exception.ResourceNotFoundException;
import curso_spring.mapper.ObjectMapper;
import curso_spring.model.Person;
import curso_spring.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Service
public class PersonServices {

    private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    @Autowired
    PersonRepository repository;


    public List<PersonDto> findAll() {

        logger.info("Finding all People!");

        var persons = ObjectMapper.parseListObject(repository.findAll(), PersonDto.class);
        persons.forEach(this::addHateoasLinks);
        return persons;
    }

    public PersonDto findById(Long id) {
        logger.info("Finding one Person!");

        var entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
        var dto =  ObjectMapper.parseObject(entity, PersonDto.class);
        addHateoasLinks(dto);
        return dto;
    }

    public PersonDto create(PersonDto person) {

        if (person == null) throw new RequiredObjectIsNullException();

        logger.info("Creating one Person!");
        var entity = ObjectMapper.parseObject(person, Person.class);

        var dto = ObjectMapper.parseObject(repository.save(entity), PersonDto.class);
        addHateoasLinks(dto);
        return dto;
    }

    public PersonDto update(PersonDto person) {

        if (person == null) throw new RequiredObjectIsNullException();

        logger.info("Updating one Person!");
        Person entity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        var dto = ObjectMapper.parseObject(repository.save(entity), PersonDto.class);
        addHateoasLinks(dto);
        return dto;

    }
    public void delete(Long id) {
    logger.info("Deleting one Person!");
    Person entity = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));

    repository.delete(entity); }


    private void addHateoasLinks(PersonDto dto) {
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(PersonController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));

    }
}
