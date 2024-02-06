package com.globant.javacodecamp.players;

import com.globant.javacodecamp.players.model.Performance;
import com.globant.javacodecamp.players.model.Play;

public class TragedyAmountCalculatorImpl implements PlayTypeAmountCalculator {
    @Override
    public int calculateAmount(Performance performance, Play play) {
        var amount = 40000;
        if (performance.audience > 30) {
            amount += 1000 * (performance.audience - 30);

        }
        return amount;
    }
}
