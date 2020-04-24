package com.alvingao.proxygenerator.service.lookup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class ScryfallLookupTest {

    private ScryfallLookupService scryfallLookup;

    @BeforeEach
    void setup() {
        scryfallLookup = new ScryfallLookupService();
    }

    @Test
    void getCardsByNames() {
        List<String> cards = Arrays.asList("Lightning Bolt", "Llanowar Elves", "Cryptic Command");
        System.out.println(scryfallLookup.getCardsByNames(cards));
    }

    @Test
    void getCardByName() {
    }
}