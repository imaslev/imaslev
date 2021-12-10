
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dto;

import java.math.BigDecimal;

/**
 *
 * @author ivaylomaslev
 */
public enum Denominations {
    FIVES(new BigDecimal("5.00")),
    SINGLES(new BigDecimal("1.00")),
    QUARTERS(new BigDecimal("0.25")),
    DIMES(new BigDecimal("0.10")),
    NICKELS(new BigDecimal("0.05")),
    PENNIES(new BigDecimal("0.01"));

    public final BigDecimal description;

    Denominations(BigDecimal denomination) {
        description = denomination;
    }
}
