package edu.usm.healthsystem.model.client;

/**
 * Represents a patient in the system, extending the basic client details
 * with demographic and registration-related information.
 */
public interface Patient extends Client {

    /**
     * Gets the date of registration.
     *
     * @return the registration date
     */
    String getDate();

    /**
     * Sets the date of registration.
     *
     * @param date the registration date
     */
    void setDate(String date);

    /**
     * Gets the NHIS registration number.
     *
     * @return the NHIS number
     */
    String getNhisNumber();

    /**
     * Sets the NHIS registration number.
     *
     * @param nhisNumber the NHIS number
     */
    void setNhisNumber(String nhisNumber);

    /**
     * Gets the card number.
     *
     * @return the card number
     */
    String getCardNumber();

    /**
     * Sets the card number.
     *
     * @param cardNumber the card number
     */
    void setCardNumber(String cardNumber);

    /**
     * Gets the marital status of the patient.
     *
     * @return the marital status
     */
    String getMaritalStatus();

    /**
     * Sets the marital status of the patient.
     *
     * @param maritalStatus the marital status
     */
    void setMaritalStatus(String maritalStatus);

    /**
     * Gets the sex of the patient.
     *
     * @return the sex
     */
    String getSex();

    /**
     * Sets the sex of the patient.
     *
     * @param sex the sex
     */
    void setSex(String sex);

    /**
     * Gets the address of the patient.
     *
     * @return the address
     */
    String getAddress();

    /**
     * Sets the address of the patient.
     *
     * @param address the address
     */
    void setAddress(String address);

    /**
     * Gets the age of the patient.
     *
     * @return the age
     */
    int getAge();

    /**
     * Sets the age of the patient.
     *
     * @param age the age
     */
    void setAge(int age);

}