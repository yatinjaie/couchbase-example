/**
 *
 */
package com.cb.model;

/**
 * Review data model.
 *
 * @author vijaykumar
 * @version $Revision: 1.10 $
 */
public class Reviews {
    private String type;
    private String productId;
    private String reviewId;
    private int rating;
    private String reviewedAt;
    private String customerId;
    /**
     *
     * Getter of {@link #type}.
     *
     * @return {@link #type}
     */
    public String getType() {
        return type;
    }
    /**
     *
     * Getter of {@link #productId}.
     *
     * @return {@link #productId}
     */
    public String getProductId() {
        return productId;
    }
    /**
     *
     * Getter of {@link #reviewId}.
     *
     * @return {@link #reviewId}
     */
    public String getReviewId() {
        return reviewId;
    }
    /**
     *
     * Getter of {@link #rating}.
     *
     * @return {@link #rating}
     */
    public int getRating() {
        return rating;
    }
    /**
     *
     * Getter of {@link #reviewedAt}.
     *
     * @return {@link #reviewedAt}
     */
    public String getReviewedAt() {
        return reviewedAt;
    }
    /**
     *
     * Getter of {@link #customerId}.
     *
     * @return {@link #customerId}
     */
    public String getCustomerId() {
        return customerId;
    }
    /**
     *
     * Setter of {@link #type}.
     *
     * @param type
     *            to be set
     */
    public void setType(String type) {
        this.type = type;
    }
    /**
     *
     * Setter of {@link #productId}.
     *
     * @param productId
     *            to be set
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }
    /**
     *
     * Setter of {@link #reviewId}.
     *
     * @param reviewId
     *            to be set
     */
    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }
    /**
     *
     * Setter of {@link #rating}.
     *
     * @param rating
     *            to be set
     */
    public void setRating(int rating) {
        this.rating = rating;
    }
    /**
     *
     * Setter of {@link #reviewedAt}.
     *
     * @param reviewedAt
     *            to be set
     */
    public void setReviewedAt(String reviewedAt) {
        this.reviewedAt = reviewedAt;
    }
    /**
     *
     * Setter of {@link #customerId}.
     *
     * @param customerId
     *            to be set
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((reviewId == null) ? 0 : reviewId.hashCode());
        result = prime * result + ((type == null) ? 0 : type.hashCode());
        return result;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Reviews other = (Reviews) obj;
        if (reviewId == null) {
            if (other.reviewId != null)
                return false;
        } else if (!reviewId.equals(other.reviewId))
            return false;
        if (type == null) {
            if (other.type != null)
                return false;
        } else if (!type.equals(other.type))
            return false;
        return true;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Reviews [type=");
        builder.append(type);
        builder.append(", productId=");
        builder.append(productId);
        builder.append(", reviewId=");
        builder.append(reviewId);
        builder.append(", rating=");
        builder.append(rating);
        builder.append(", reviewedAt=");
        builder.append(reviewedAt);
        builder.append(", customerId=");
        builder.append(customerId);
        builder.append("]");
        return builder.toString();
    }


}

