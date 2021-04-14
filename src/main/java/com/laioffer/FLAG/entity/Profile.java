package com.laioffer.FLAG.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;
import java.util.Set;

public class Profile {

    private Set<String> profile;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    public Profile() {

    }

    public Profile(String firstName, String lastName, String email, String phoneNumber, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;

    }

    public Set<String> getProfile() {
        return profile;
    }

    public void setProfile(Set<String> profile) {
        this.profile = profile;
    }

    @JsonProperty("first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @JsonProperty("phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @JsonProperty("address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile1 = (Profile) o;
        return Objects.equals(profile, profile1.profile) && Objects.equals(firstName, profile1.firstName) && Objects.equals(lastName, profile1.lastName) && Objects.equals(email, profile1.email) && Objects.equals(phoneNumber, profile1.phoneNumber) && Objects.equals(address, profile1.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profile, firstName, lastName, email, phoneNumber, address);
    }
}
