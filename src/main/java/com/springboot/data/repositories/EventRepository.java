package com.springboot.data.repositories;


import com.springboot.data.models.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends PagingAndSortingRepository<Event, Long> {

    @Query("select e from Event e where e.id = ?1")
    public Event findEventById(Long id);

}
