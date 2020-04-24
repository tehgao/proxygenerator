package com.alvingao.proxygenerator.model;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class SpellCard extends Card {
    private String manaCost;
}
