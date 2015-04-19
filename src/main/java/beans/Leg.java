package beans;

import java.util.Objects;

public class Leg
{

    public Leg() {
       }

    @Override
    public String toString()
    {
        return "Leg{" + "key=" + key + ", source_location_key=" + source_location_key + ", target_location_key=" + target_location_key + ", distance=" + distance + '}';
    }

    private String key;
    private String source_location_key;
    private String target_location_key;
    private double distance;

    public Leg(String key, String source_location_key, String target_location_key, double distance)
    {
        this.key = key;
        this.source_location_key = source_location_key;
        this.target_location_key = target_location_key;
        this.distance = distance;
    }

    public String getKey()
    {
        return key;
    }

    public void setKey(String key)
    {
        this.key = key;
    }

    public String getSource_location_key()
    {
        return source_location_key;
    }

    public void setSource_location_key(String source_location_key)
    {
        this.source_location_key = source_location_key;
    }

    public String getTarget_location_key()
    {
        return target_location_key;
    }

    public void setTarget_location_key(String target_location_key)
    {
        this.target_location_key = target_location_key;
    }

    public double getDistance()
    {
        return distance;
    }

    public void setDistance(double distance)
    {
        this.distance = distance;
    }

    
    
    public boolean checkDistance()
    {
        if(this.getDistance()==0)
        {
            return true;
        }
        return false;
    }
    
    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Leg other = (Leg) obj;
        if (!Objects.equals(this.key, other.key))
        {
            return false;
        }
        if (this.source_location_key != other.source_location_key)
        {
            return false;
        }
        if (this.target_location_key != other.target_location_key)
        {
            return false;
        }
        if (Double.doubleToLongBits(this.distance) != Double.doubleToLongBits(other.distance))
        {
            return false;
        }
        return true;
    }

}
