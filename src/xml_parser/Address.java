package xml_parser;

public class Address {

    public String city;

    public String state;

    public String postalCode;

    public String countryCode;

    public String lines;
    
    public String setLines(String set)
    {
    	return set;
    }
    
    public String setCity(String set)
    {
    	return set;
    }
    
    public String getCity()
    {
    	return city;
    }

    public String setState(String set)
    {
    	return set;
    }

    public String setPostalCode(String set)
    {
    	return set;
    }
    
    public String setCountry(String set)
    {
    	return set;
    }

    

    public Address(){}

    public Address(String city, String state, String postalCode, String countryCode, String lines) {
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.countryCode = countryCode;
        this.lines = lines;
    }

    
	
}
