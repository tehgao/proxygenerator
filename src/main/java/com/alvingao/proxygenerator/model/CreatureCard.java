package com.alvingao.proxygenerator.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class CreatureCard extends SpellCard {
    private String power;
    private String toughness;
}
