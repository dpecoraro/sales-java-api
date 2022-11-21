package org.udemy.spring.course.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.udemy.spring.course.domain.bo.Client;
import org.udemy.spring.course.domain.bo.OrderStore;

import java.util.List;
import java.util.Optional;

public interface OrderDAO extends JpaRepository<OrderStore, Integer> {
    List<OrderStore> findByClient(Client client);

    @Query("select o from OrderStore o left join fetch o.orderStore where o.id =:id")
    Optional<OrderStore> findByIdFetchProduct(@Param("id") Integer id);
}
