package com.springboot.data.service.impl;

import com.springboot.data.models.Event;
import com.springboot.data.repositories.EventRepository;
import com.springboot.data.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

import static org.springframework.data.domain.Sort.by;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Page<Event> findAll(Pageable pageable) {
        return  this.eventRepository.findAll(pageable);
    }

    @Override
    public Iterable<Event> findAllSorting() {
        return this.eventRepository.findAll(by(Sort.Direction.ASC, "name" ));
    }

    @Override
    public Event create(Event event) {
        return this.eventRepository.save(event);
    }

    @Override
    public void delete(Long eventId) {
        this.findEventById(eventId);
        this.eventRepository.deleteById(eventId);
    }

    @Override
    public Event findEventById(Long eventId) {
        Event event = this.eventRepository.findEventById(eventId);
        if (event == null) {
            throw new EntityNotFoundException("Event Not Found. Event id:" + eventId);
        }
        return event;
    }

}
