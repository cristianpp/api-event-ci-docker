package com.springboot.data.service.impl;

import com.springboot.data.api.external.cpf.ApiValidateCpfService;
import com.springboot.data.api.external.cpf.ApiValidateCpfServiceProxy;
import com.springboot.data.models.Event;
import com.springboot.data.models.Person;
import com.springboot.data.repositories.PersonRepository;
import com.springboot.data.service.EventService;
import com.springboot.data.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ApiValidateCpfService apiValidateCpfService;

    @Autowired
    private EventService eventService;


    @Override
    public Page<Person> findPeoplesByEvent(Long eventId, Pageable pageable) {

        Event event = eventService.findEventById(eventId);

        Page<Person> persons = this.personRepository.findByEventId(event, pageable);
        if (persons.isEmpty()) {
            throw new EntityNotFoundException("There are no people at this event. Event Id: " + eventId);
        }
        return persons;
    }

    @Override
    public Person create(Person person, Long eventId) {

        boolean valid = new ApiValidateCpfServiceProxy(apiValidateCpfService).validateCpf(person.getCpf());

        if (!valid) {
            throw new EntityNotFoundException(("CPF not valid. " + person.getCpf()));
        }
        Event event = eventService.findEventById(eventId);
        person.setEventId(event);
        Person personCreate = this.personRepository.save(person);

        return personCreate;
    }

}
