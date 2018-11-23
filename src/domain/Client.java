package domain;

import java.util.HashMap;
import java.util.Map;

public class Client {
    // Fields
    private Map<Integer, Address> addresses;
    private String firstname;
    private String lastname;
    private int id;


    // Constructors
    public Client(String firstname, String lastname, int id, Map<Integer, Address> addresses) {
        setFirstname(firstname);
        setLastname(lastname);
        setId(id);
        setAddresses(addresses);
    }
    public Client(String firstname, String lastname, int id) {
        this(firstname, lastname, id, new HashMap<>());
    }
    public Client(String firstname, String lastname) {
        this(firstname, lastname, 0, new HashMap<>());
    }


    // Getters
    public String getFirstname() {
        return this.firstname;
    }
    public String getLastname() {
        return this.lastname;
    }
    public int getId() {
        return this.id;
    }
    public Map<Integer, Address> getAddresses() {
        return addresses;
    }
    public Address getAddress(int trackNr){
        return addresses.get(trackNr);
    }


    // Setters
    public void setFirstname(String firstname) {
        if(firstname != null && firstname.matches("^[A-Z][a-z]+[A-Z a-z]*")) {
            this.firstname = firstname;
        }else {
            throw new IllegalArgumentException();
        }
    }
    public void setLastname(String lastname) {
        if(lastname != null && lastname.matches("^[A-Z][a-z]+[A-Z a-z]*")) {
            this.lastname = lastname;
        }else {
            throw new IllegalArgumentException();
        }
    }
    public void setId(int id) {
        if(id >= 0){
            this.id = id;
        }else{
            throw new IllegalArgumentException();
        }
    }
    public void setAddresses(Map<Integer, Address> addresses) {
        if(addresses != null){
            this.addresses = addresses;
        }else{
            throw new IllegalArgumentException();
        }
    }


    // Address methods
    public void addAddress(int trackNr, String city, String street, int houseNr, int zipcode) {
        addresses.put(trackNr, new Address(trackNr, city, street, houseNr, zipcode, id));
    }
    public void editAddress(int trackNr, String city, String street, int houseNr, int zipcode) {
        addAddress(trackNr, city, street, houseNr, zipcode);
    }
    public void removeAddress(int trackNr){
        addresses.remove(trackNr);
    }


    @Override
    public String toString(){
        return String.format("%s %s", firstname, lastname);
    }
}
