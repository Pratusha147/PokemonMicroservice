package com.pokemon.service;

import com.pokemon.model.HeaviestPokemonResults;
import com.pokemon.model.HighestPokemonResults;
import com.pokemon.model.MostBaseExperiencePokemonResults;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.util.List;

public interface PokemonService {

    public void loadRedPokemons();
    public boolean dataLoaded();
    public int getPokemonCount();
    public JSONArray getPokemonArray(int pokemonCount);
    public void filterPokemonsByColor(JSONArray pokemonResultsArray, String color);
    public List<HeaviestPokemonResults> getHeaviestPokemons(int count) throws IOException;
    public List<HighestPokemonResults> getHighestPokemons(int count);
    public List<MostBaseExperiencePokemonResults> getMostBaseExperiencePokemons(int count);


}
