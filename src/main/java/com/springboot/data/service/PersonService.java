package com.springboot.data.service;

import com.springboot.data.models.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PersonService {

    public Page<Person> findPeoplesByEvent(Long eventId, Pageable pageable);

    public Person create(Person person, Long eventId);
}
