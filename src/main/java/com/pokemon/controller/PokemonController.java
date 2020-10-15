package com.pokemon.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.gson.JsonObject;
import com.pokemon.model.*;
import com.pokemon.service.PokemonService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
public class PokemonController {

    @Autowired
    private PokemonService service;

    @GetMapping("/pokemon/height/top/{count}")
    private List<HighestPokemonResults> getHighestPokemons(@PathVariable("count") int count) {
        return service.getHighestPokemons(count);
    }

    @GetMapping("/pokemon/weight/top/{count}")
    private List<HeaviestPokemonResults> getHeaviestPokemons(@PathVariable("count") int count) throws IOException {
        return service.getHeaviestPokemons(count);
    }

    @GetMapping("/pokemon/baseExperience/top/{count}")
    private List<MostBaseExperiencePokemonResults> getMostBaseExperiencePokemons(@PathVariable("count") int count) {
        return service.getMostBaseExperiencePokemons(count);
    }

}

