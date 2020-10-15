package com.pokemon.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.beans.ConstructorProperties;

@Getter
@Setter
@Entity
@AllArgsConstructor
public class PokemonModel {

    @JsonProperty("base_experience")
    private int baseExperience;
    private int height;
    @Id
    private int id;
    private String name;
    private int weight;

    public PokemonModel() {

    }
}
