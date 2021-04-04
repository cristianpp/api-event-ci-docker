package com.springboot.data.controller;

import com.springboot.data.models.Person;
import com.springboot.data.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.io.Serializable;
import java.net.URI;

@RestController
@RequestMapping("/events/{eventId}/peoples")
public class PeopleController implements Serializable {

    @Autowired
    private PersonService personService;


    @GetMapping
    public Page<Person> findPeoplesByEvent(@PathVariable("eventId") Long eventId, Pageable pageable) {
        return this.personService.findPeoplesByEvent(eventId, pageable);
    }

    @PostMapping
    public ResponseEntity<Person> create(@PathVariable("eventId") Long eventId, @RequestBody Person person) {

        Person personCreate = personService.create(person, eventId);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .buildAndExpand(personCreate.getEventId().getId()).toUri();

        return ResponseEntity.created(uri).body(personCreate);
    }
}
