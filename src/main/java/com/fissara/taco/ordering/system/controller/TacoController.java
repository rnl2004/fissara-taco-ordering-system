package com.fissara.taco.ordering.system.controller;

import com.fissara.taco.ordering.system.model.Taco;
import com.fissara.taco.ordering.system.service.TacoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/taco")
public class TacoController {

    @Autowired
    private TacoService tacoService;

    @GetMapping("/tacos")
    public List<Taco> getAllTacos() {

        return tacoService.findAll();
    }
}
