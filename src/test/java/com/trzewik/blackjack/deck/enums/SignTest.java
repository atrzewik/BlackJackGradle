package com.trzewik.blackjack.deck.enums;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SignTest {

    @Test
    public void getValue() {
        assertEquals(7, Sign.SEVEN.getValue());
    }
}