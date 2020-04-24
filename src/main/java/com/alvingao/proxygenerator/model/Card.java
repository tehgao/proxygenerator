package com.alvingao.proxygenerator.model;

import com.alvingao.proxygenerator.model.type.Color;
import com.alvingao.proxygenerator.model.type.Rarity;
import lombok.Data;

import java.util.List;

@Data
public class Card {
    private String name;
    private String typeLine;
    private List<Color> colorIdentity;

    private String textBox;
    private String imageUrl;

    private String collectorNumber;
    private String setCode;
    private String artist;
    private Rarity rarity;
}
