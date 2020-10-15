package com.pokemon.dao;

import com.pokemon.model.PokemonModel;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PokemonRepository extends CrudRepository<PokemonModel, Integer> {

    @Query("SELECT p.name from PokemonModel p order by p.weight desc")
    List findHeaviest(PageRequest pageRequest);

    @Query("SELECT p.name from PokemonModel p order by p.height desc")
    List findHighest(PageRequest pageRequest);

    @Query("SELECT p.name from PokemonModel p order by p.baseExperience desc")
    List findMostBaseExperience(PageRequest pageRequest);

}
