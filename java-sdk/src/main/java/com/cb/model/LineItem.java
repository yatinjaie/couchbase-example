/**
 *
 */
package com.cb.model;

/**
 * Line Item model.
 *
 * @author vijaykumar
 * @version $Revision: 1.10 $
 */
public class LineItem {

    private int count;
    private String product;
    /**
     *
     * Getter of {@link #count}.
     *
     * @return {@link #count}
     */
    public int getCount() {
        return count;
    }
    /**
     *
     * Getter of {@link #product}.
     *
     * @return {@link #product}
     */
    public String getProduct() {
        return product;
    }
    /**
     *
     * Setter of {@link #count}.
     *
     * @param count
     *            to be set
     */
    public void setCount(int count) {
        this.count = count;
    }
    /**
     *
     * Setter of {@link #product}.
     *
     * @param product
     *            to be set
     */
    public void setProduct(String product) {
        this.product = product;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((product == null) ? 0 : product.hashCode());
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
        final LineItem other = (LineItem) obj;
        if (product == null) {
            if (other.product != null)
                return false;
        } else if (!product.equals(other.product))
            return false;
        return true;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("LineItem [count=");
        builder.append(count);
        builder.append(", product=");
        builder.append(product);
        builder.append("]");
        return builder.toString();
    }


}
