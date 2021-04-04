package com.springboot.data.controller;

import com.springboot.data.models.Event;
import com.springboot.data.models.dto.EventDTO;
import com.springboot.data.service.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public Page<Event> findAll(Pageable pageable) {
        return this.eventService.findAll(pageable);
    }

    @GetMapping("/sort")
    public Iterable<Event> findAllSorting() {
        return this.eventService.findAllSorting();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> findEventById(@PathVariable Long id) {
        Event event = this.eventService.findEventById(id);
        return ResponseEntity.status(HttpStatus.OK).body(event);
    }

   /* @PostMapping
    public ResponseEntity<Event> create(@RequestBody Event event) {
        Event eventCreate = eventService.create(event);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(eventCreate.getId()).toUri();

        return ResponseEntity.created(uri).body(eventCreate);
    }*/

    @PostMapping
    public ResponseEntity<EventDTO> create(@Valid @RequestBody EventDTO eventDTO) {
        Event event = modelMapper.map(eventDTO, Event.class);
        Event eventCreated = eventService.create(event);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(eventCreated.getId()).toUri();

        return ResponseEntity.created(uri).body(convertToDto(eventCreated));
    }

    @DeleteMapping("/{eventId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long eventId){
        this.eventService.delete(eventId);
    }


    private EventDTO convertToDto(Event event) {
        EventDTO eventDTO = modelMapper.map(event, EventDTO.class);
      //  postDto.setSubmissionDate(post.getSubmissionDate(),
        //        userService.getCurrentUser().getPreference().getTimezone());
        return eventDTO;
    }

}
