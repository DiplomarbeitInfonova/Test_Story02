package beans;

/**
 *
 * @author David
 */
public class Location
{

    private String key;
    private String name;
    private int location_type_key;
    private double longitude;
    private double latitude;
    private int post_code;
    private String city;
    private String street;

    public Location(String key, String name, int location_type_key, double longitude, double latitude, int post_code, String city, String street)
    {
        this.key = key;
        this.name = name;
        this.location_type_key = location_type_key;
        this.longitude = longitude;
        this.latitude = latitude;
        this.post_code = post_code;
        this.city = city;
        this.street = street;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getLocation_type_key()
    {
        return location_type_key;
    }

    public void setLocation_type_key(int location_type_key)
    {
        this.location_type_key = location_type_key;
    }

    public double getLongitude()
    {
        return longitude;
    }

    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }

    public double getLatitude()
    {
        return latitude;
    }

    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }

    public int getPost_code()
    {
        return post_code;
    }

    public void setPost_code(int post_code)
    {
        this.post_code = post_code;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    
    public boolean equals(Location l){
        
        return this.getKey().equals(l.getKey());
    }
    
    @Override
    public String toString()
    {
        return " Key:" + key
                + " Name:" + name
                + " Location Type Key:" + location_type_key
                + " Longitude:" + longitude
                + " Latitude:" + latitude
                + " PLZ:" + post_code
                + " City:" + city
                + " Straße:" + street;
    }
}
