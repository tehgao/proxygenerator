package com.alvingao.proxygenerator.service.web;

import com.alvingao.proxygenerator.model.type.Color;
import com.alvingao.proxygenerator.model.view.ViewCard;
import com.alvingao.proxygenerator.service.lookup.LookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class CardWebService {

    private LookupService lookupService;

    @Autowired
    public CardWebService(LookupService lookupService) {
        this.lookupService = lookupService;
    }

    public ViewCard processDeckList(String deckList) {
        return Arrays.stream(deckList.split("\n"))
                     .map(entry -> {
                         Matcher matcher = Pattern.compile("([0-9]+)x? ([\\w]+)").matcher(entry);
                         return Optional.ofNullable(matcher.group(2)).map(name -> IntStream
                                 .range(0, Optional.ofNullable(matcher.group(1)).map(Integer::parseInt).orElse(1))
                                 .mapToObj(idx -> lookupService.getCardByName(name))
                                 .filter(Optional::isPresent)
                                 .map(Optional::get)
                                 .map(card -> {
                                     ViewCard viewCard = new ViewCard();
                                     viewCard.setName(card.getName());
                                     viewCard.setTypeLine(card.getTypeLine());
                                     viewCard.setColorIdentity(card.getColorIdentity().stream().sorted((a, b) ->
                                             Arrays.asList(Color.W, Color.U, Color.B, Color.R, Color.G).indexOf(b) -
                                                     Arrays.asList(Color.W, Color.U, Color.B, Color.R, Color.G)
                                                           .indexOf(a)
                                     )
                                                                   .map(color -> color.toString())
                                                                   .collect(Collectors.joining()));
                                     return viewCard;
                                 })
                                 .collect(Collectors.toList())).orElse(new ArrayList<ViewCard>());
                     });
    }
}
