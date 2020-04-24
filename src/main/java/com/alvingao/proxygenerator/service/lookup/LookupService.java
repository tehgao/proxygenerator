package com.alvingao.proxygenerator.service.lookup;

import com.alvingao.proxygenerator.model.Card;

import java.util.List;
import java.util.Optional;

public interface LookupService {
    List<Card> getCardsByNames(List<String> cardNames);

    Optional<Card> getCardByName(String cardName);
}
