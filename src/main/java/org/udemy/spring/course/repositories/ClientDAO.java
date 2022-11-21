package org.udemy.spring.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.udemy.spring.course.domain.bo.Client;

public interface ClientDAO extends JpaRepository<Client, Integer> {
    @Query("SELECT c FROM Client c left join fetch c.orderSet where c.id =:id")
    Client findClientFetchOrderList(@Param("id") Integer id);
}
