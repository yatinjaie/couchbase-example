/**
 *
 */
package com.cb.model;

import java.util.Set;

import lombok.Data;

/**
 *
 * @author yatinjaie
 *
 */
@Data
public class Purchase {

    private String purchaseId;
    private String type;
    private String purchasedAt;
    private String customerId;
    private Set<LineItem> lineItems;

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Purchase [purchaseId=");
        builder.append(purchaseId);
        builder.append(", type=");
        builder.append(type);
        builder.append(", purchasedAt=");
        builder.append(purchasedAt);
        builder.append(", customerId=");
        builder.append(customerId);
        builder.append(", lineItems=");
        builder.append(lineItems);
        builder.append("]");
        return builder.toString();
    }

}
