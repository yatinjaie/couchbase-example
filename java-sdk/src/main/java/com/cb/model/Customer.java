/**
 *
 */
package com.cb.model;

/**
 * Customer model class.
 *
 * @author vijaykumar
 * @version $Revision: 1.10 $
 */
public class Customer {

    private String customerId;

    private String type;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private CreditCard ccInfo;

    private String dateAdded;

    private String state;

    private String emailAddress;

    private String dateLastActive;

    private String postalCode;

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
     * Getter of {@link #type}.
     *
     * @return {@link #type}
     */
    public String getType() {
        return type;
    }

    /**
     *
     * Getter of {@link #firstName}.
     *
     * @return {@link #firstName}
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * Getter of {@link #lastName}.
     *
     * @return {@link #lastName}
     */
    public String getLastName() {
        return lastName;
    }

    /**
     *
     * Getter of {@link #phoneNumber}.
     *
     * @return {@link #phoneNumber}
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     *
     * Getter of {@link #ccInfo}.
     *
     * @return {@link #ccInfo}
     */
    public CreditCard getCcInfo() {
        return ccInfo;
    }

    /**
     *
     * Getter of {@link #dateAdded}.
     *
     * @return {@link #dateAdded}
     */
    public String getDateAdded() {
        return dateAdded;
    }

    /**
     *
     * Getter of {@link #state}.
     *
     * @return {@link #state}
     */
    public String getState() {
        return state;
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
     * Setter of {@link #firstName}.
     *
     * @param firstName
     *            to be set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * Setter of {@link #lastName}.
     *
     * @param lastName
     *            to be set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     *
     * Setter of {@link #phoneNumber}.
     *
     * @param phoneNumber
     *            to be set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     *
     * Setter of {@link #ccInfo}.
     *
     * @param ccInfo
     *            to be set
     */
    public void setCcInfo(CreditCard ccInfo) {
        this.ccInfo = ccInfo;
    }

    /**
     *
     * Setter of {@link #dateAdded}.
     *
     * @param dateAdded
     *            to be set
     */
    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    /**
     *
     * Setter of {@link #state}.
     *
     * @param state
     *            to be set
     */
    public void setState(String state) {
        this.state = state;
    }


    /**
     *
     * Getter of {@link #emailAddress}.
     *
     * @return {@link #emailAddress}
     */
    public String getEmailAddress() {
        return emailAddress;
    }

    /**
     *
     * Getter of {@link #dateLastActive}.
     *
     * @return {@link #dateLastActive}
     */
    public String getDateLastActive() {
        return dateLastActive;
    }

    /**
     *
     * Getter of {@link #postalCode}.
     *
     * @return {@link #postalCode}
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     *
     * Setter of {@link #emailAddress}.
     *
     * @param emailAddress
     *            to be set
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     *
     * Setter of {@link #dateLastActive}.
     *
     * @param dateLastActive
     *            to be set
     */
    public void setDateLastActive(String dateLastActive) {
        this.dateLastActive = dateLastActive;
    }

    /**
     *
     * Setter of {@link #postalCode}.
     *
     * @param postalCode
     *            to be set
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((customerId == null) ? 0 : customerId.hashCode());
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
        final Customer other = (Customer) obj;
        if (customerId == null) {
            if (other.customerId != null)
                return false;
        } else if (!customerId.equals(other.customerId))
            return false;
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Customer [customerId=");
        builder.append(customerId);
        builder.append(", type=");
        builder.append(type);
        builder.append(", firstName=");
        builder.append(firstName);
        builder.append(", lastName=");
        builder.append(lastName);
        builder.append(", phoneNumber=");
        builder.append(phoneNumber);
        builder.append(", ccInfo=");
        builder.append(ccInfo);
        builder.append(", dateAdded=");
        builder.append(dateAdded);
        builder.append(", state=");
        builder.append(state);
        builder.append("]");
        return builder.toString();
    }

}
