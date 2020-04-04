package com.fissara.taco.ordering.system.repository;

import com.fissara.taco.ordering.system.model.Taco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TacoRepository extends JpaRepository<Taco, Long> {

    List<Taco> findTacosByOrderId(Long id);
}
