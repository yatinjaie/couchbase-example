/**
 *
 */
package com.cb.model;

import lombok.Data;

/**
 *
 * @author yatinjaie
 *
 */
@Data
public class CreditCard {

    private String cardNumber;
    private String cardType;
    private String cardExpiry;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("CreditCard [cardNumber=");
        builder.append(cardNumber);
        builder.append(", cardType=");
        builder.append(cardType);
        builder.append(", cardExpiry=");
        builder.append(cardExpiry);
        builder.append("]");
        return builder.toString();
    }





}
