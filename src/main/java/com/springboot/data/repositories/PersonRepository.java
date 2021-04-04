package com.springboot.data.repositories;

import com.springboot.data.models.Event;
import com.springboot.data.models.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> {

    Page<Person> findByEventId(Event eventId, Pageable pageable);
}
