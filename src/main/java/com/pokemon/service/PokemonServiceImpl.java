package com.pokemon.service;

import com.pokemon.dao.PokemonRepository;
import com.pokemon.model.*;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PokemonServiceImpl implements PokemonService {

    @Autowired
    PokemonRepository pokemonRepository;

    @Override
    public void loadRedPokemons() {
        if(!(dataLoaded())) {
            int pokemonCount = getPokemonCount();
            JSONArray pokemonResultsArray = getPokemonArray(pokemonCount);
            filterPokemonsByColor(pokemonResultsArray, "red");
        }

    }

    @Override
    public boolean dataLoaded() {
        PokemonModel pokemonModel = new PokemonModel();
        if (pokemonRepository.count()==0)
            return false;
        else
            return true;
    }

    @Override
    public int getPokemonCount() {
        RestTemplate getPokemons = new RestTemplate();
        Pokemon pokemons = getPokemons.getForObject("https://pokeapi.co/api/v2/pokemon/", Pokemon.class);
        return pokemons.getCount();
    }

    @Override
    public JSONArray getPokemonArray(int pokemonCount) {
        RestTemplate getPokemons = new RestTemplate();
        Pokemon pokemons = getPokemons.getForObject("https://pokeapi.co/api/v2/pokemon/?limit=" + pokemonCount, Pokemon.class);
        JSONArray pokemonResultsArray = pokemons.getResults();
        return pokemonResultsArray;
    }

    @Override
    public void filterPokemonsByColor(JSONArray pokemonResultsArray, String Color) {
        RestTemplate pokemonDetails = new RestTemplate();
        PokemonModel pokemonModel = new PokemonModel();
        for (int i = 0; i < pokemonResultsArray.size(); i++) {
            HashMap<String, String> passedValues = (HashMap<String, String>) pokemonResultsArray.get(i);
            for (Map.Entry<String, String> mapTemp : passedValues.entrySet()) {

                if (mapTemp.getKey().equalsIgnoreCase("url")) {
                    JSONArray gameIndicesArray = pokemonDetails.getForObject(mapTemp.getValue(), PokemonGameIndices.class).getGameIndices();

                    if (gameIndicesArray.toJSONString().contains("\"version\":{\"name\":\""+Color+"\"")) {
                        pokemonModel = pokemonDetails.getForObject(mapTemp.getValue(), PokemonModel.class);

                        pokemonRepository.save(pokemonModel);
                    }
                }
            }
        }
    }

    @Override
    public List<HeaviestPokemonResults> getHeaviestPokemons(int count) {
        loadRedPokemons();
        return pokemonRepository.findHeaviest(PageRequest.of(0,count));
        }

    @Override
    public List<HighestPokemonResults> getHighestPokemons(int count) {
        loadRedPokemons();
        return pokemonRepository.findHighest(PageRequest.of(0,count));
    }

    @Override
    public List<MostBaseExperiencePokemonResults> getMostBaseExperiencePokemons(int count) {
        loadRedPokemons();
        return pokemonRepository.findMostBaseExperience(PageRequest.of(0,count));
    }
}