package com.pokemon.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONArray;

@Getter
@Setter
public class PokemonGameIndices {
    @JsonProperty("game_indices")
    private JSONArray gameIndices;
}

