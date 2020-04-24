package com.alvingao.proxygenerator.model.type;

public enum Rarity {
    COMMON("C"), UNCOMMON("U"), RARE("R"), MYTHIC("M"), LAND("L");

    private String abbrev;

    Rarity(String abbrev) {
        this.abbrev = abbrev;
    }

    public String getAbbrev() {
        return abbrev;
    }
}
