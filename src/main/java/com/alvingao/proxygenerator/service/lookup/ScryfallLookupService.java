package com.alvingao.proxygenerator.service.lookup;

import com.alvingao.proxygenerator.model.Card;
import com.alvingao.proxygenerator.model.CreatureCard;
import com.alvingao.proxygenerator.model.SpellCard;
import com.alvingao.proxygenerator.model.type.Color;
import com.alvingao.proxygenerator.model.type.Rarity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ScryfallLookupService implements LookupService {
    @Override
    public List<Card> getCardsByNames(List<String> cardNames) {
        return cardNames.stream()
                        .map(this::getCardByName)
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .collect(Collectors.toList());
    }

    @Override
    public Optional<Card> getCardByName(String cardName) {
        forohfor.scryfall.api.Card scryfallCard;
        try {
            scryfallCard = forohfor.scryfall.api.MTGCardQuery.search(cardName).get(0);
        } catch (IndexOutOfBoundsException e) {
            return Optional.empty();
        }

        Card result;

        if (Optional.ofNullable(scryfallCard.getManaCost()).isPresent()) {
            result = new SpellCard();

            if (Optional.ofNullable(scryfallCard.getPower()).isPresent()) {
                result = new CreatureCard();
                ((CreatureCard) result).setPower(scryfallCard.getPower());
                ((CreatureCard) result).setToughness(scryfallCard.getToughness());
            }
            ((SpellCard) result).setManaCost(scryfallCard.getManaCost());
        } else {
            result = new Card();
        }

        result.setName(scryfallCard.getName());
        result.setColorIdentity(Arrays.stream(scryfallCard.getColorIdentity()).map(Color::valueOf)
                                      .collect(Collectors.toList()));
        result.setTypeLine(scryfallCard.getTypeLine());
        result.setTextBox(scryfallCard.getOracleText());
        result.setCollectorNumber(scryfallCard.getCollectorNumber());
        result.setArtist(scryfallCard.getArtist());
        result.setImageUrl(scryfallCard.getImageURI("art_crop"));
        result.setSetCode(scryfallCard.getSetCode());
        result.setRarity(Rarity.valueOf(scryfallCard.getRarity().toUpperCase()));

        return Optional.of(result);
    }
}
