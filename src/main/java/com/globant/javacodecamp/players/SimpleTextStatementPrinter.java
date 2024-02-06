package com.globant.javacodecamp.players;

import com.globant.javacodecamp.players.model.Invoice;
import com.globant.javacodecamp.players.model.Performance;
import com.globant.javacodecamp.players.model.Play;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class SimpleTextStatementPrinter {

    private final Map<String,PlayTypeAmountCalculator> amountCalculators = Map.of(
            "comedy",new ComedyAmountCalculatorImpl(),
            "tragedy",new TragedyAmountCalculatorImpl()
    );

    public String print(Invoice invoice, Map<String, Play> plays) {
        var totalAmount = 0;
        var volumeCredits = 0;
        var result = String.format("Statement for %s\n", invoice.customer);

        NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

        for (var perf : invoice.performances) {
            var play = plays.get(perf.playID);
            var thisAmount = 0;

            thisAmount = calculateAmount(perf, play);

            // add volume credits
            volumeCredits += Math.max(perf.audience - 30, 0);
            // add extra credit for every ten comedy attendees
            if ("comedy".equals(play.type)) volumeCredits += Math.floor(perf.audience / 5);

            // print line for this order
            result += String.format("  %s: %s (%s seats)\n", play.name, frmt.format(thisAmount / 100), perf.audience);
            totalAmount += thisAmount;
        }
        result += String.format("Amount owed is %s\n", frmt.format(totalAmount / 100));
        result += String.format("You earned %s credits", volumeCredits);
        return result;
    }

    private  int calculateAmount(Performance performance, Play play) {
        int thisAmount;

      var amountCalculator =  amountCalculators.get(play.type);
      if (amountCalculator == null)  throw new Error("unknown type: ${play.type}");

      return amountCalculator.calculateAmount(performance,play);

        }
    }

