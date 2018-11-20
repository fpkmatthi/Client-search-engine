package domain;

public class Address {
    // Fields
    private int trackNr;
    private String city;
    private String street;
    private int houseNr;
    private int zipcode;
    private int clientID;


    // Constructors
    public Address(int trackNr, String city, String street, int houseNr, int zipcode, int clientID) {
        setTrackNr(trackNr);
        setCity(city);
        setStreet(street);
        setHouseNr(houseNr);
        setZipcode(zipcode);
        setClientID(clientID);
    }
    public Address(int trackNr, String city, String street, int houseNr, int zipcode) {
        this(trackNr, city, street, houseNr, zipcode, 0);
    }


    // Getters
    public int getTrackNr() {
        return this.trackNr;
    }
    public String getCity() {
        return this.city;
    }
    public String getStreet() {
        return this.street;
    }
    public int getHouseNr() {
        return this.houseNr;
    }
    public int getZipcode() {
        return this.zipcode;
    }
    public int getClientID() {
        return clientID;
    }


    // Setters
    public void setTrackNr(int trackNr) {
        if(trackNr <= 0){
            throw new IllegalArgumentException();
        }else{
            this.trackNr = trackNr;
        }
    }
    public void setCity(String city) {
        if(city == null || !city.matches("[A-Z][a-zàâäèéêëîïôœùûüÿçÀÂÄÈÉÊËÎÏÔŒÙÛÜŸÇ]+(-[A-Za-zàâäèéêëîïôœùûüÿçÀÂÄÈÉÊËÎÏÔŒÙÛÜŸÇ]+)*")){
            throw new IllegalArgumentException();
        }else{
            this.city = city;
        }
    }
    public void setStreet(String street) {
        if(city == null || !street.matches("[A-Z][a-zàâäèéêëîïôœùûüÿçÀÂÄÈÉÊËÎÏÔŒÙÛÜŸÇ]+(-[A-Za-zàâäèéêëîïôœùûüÿçÀÂÄÈÉÊËÎÏÔŒÙÛÜŸÇ]+)*")){
            throw new IllegalArgumentException();
        }else{
            this.street = street;
        }
    }
    public void setHouseNr(int houseNr) {
        if(houseNr < 1){
            throw new IllegalArgumentException();
        }else{
            this.houseNr = houseNr;
        }
    }
    public void setZipcode(int zipcode) {
        if(zipcode < 1000 || zipcode > 9992){
            throw new IllegalArgumentException();
        }else{
            this.zipcode = zipcode;
        }
    }
    public void setClientID(int clientID) {
        this.clientID = clientID;
    }


    @Override
    public String toString(){
        return String.format("%-19s %d%n" +
                "%-20s %s (%d)%n" +
                "%-25s %s %d", "Volgnummer:", trackNr, "Woonplaats:", city, zipcode, "Adres:", street, houseNr);
    }
}