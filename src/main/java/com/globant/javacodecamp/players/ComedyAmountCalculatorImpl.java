package com.globant.javacodecamp.players;

import com.globant.javacodecamp.players.model.Performance;
import com.globant.javacodecamp.players.model.Play;
//TODO start to review the refactory this week in order to finish on 1st april week

public class ComedyAmountCalculatorImpl implements PlayTypeAmountCalculator {
    @Override
    public int calculateAmount(Performance performance, Play play) {
        var amount = 30000;
        if (performance.audience > 20) {
            amount += 10000 + 500 * (performance.audience - 20);
        }
        amount += 300 * performance.audience;
        return amount;
    }
}
