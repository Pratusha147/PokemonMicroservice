package com.pokemon.model;

import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONArray;

@Getter
@Setter
public class Pokemon {
    private int count;
    private String next;
    private String previous;
    private JSONArray results;
}
