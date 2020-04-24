package com.alvingao.proxygenerator.model.view;

import com.alvingao.proxygenerator.model.type.Rarity;
import lombok.Data;

@Data
public class ViewCard {
    private String name;
    private String typeLine;
    private String colorIdentity;

    private String manaCost;

    private String textBox;
    private String imageUrl;

    private String collectorNumber;
    private String setCode;
    private String artist;
    private Rarity rarity;

    private boolean creature;
    private String power;
    private String toughness;
}
