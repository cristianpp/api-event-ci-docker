package com.springboot.data.service;

import com.springboot.data.models.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventService {

    Page<Event> findAll(Pageable pageable);

    Iterable<Event> findAllSorting();

    Event create(Event event);

    void delete(Long eventId);

    Event findEventById(Long eventId);

}
