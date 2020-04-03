package com.fissara.taco.ordering.system.service;

import com.fissara.taco.ordering.system.model.Taco;
import com.fissara.taco.ordering.system.repository.TacoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TacoService {

    @Autowired
    private TacoRepository tacoRepository;

    public List<Taco> findAll() {
        return tacoRepository.findAll();
    }

    public Taco save(Taco taco) {
        return tacoRepository.save(taco);
    }

}
