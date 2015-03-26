package dal;

import beans.Leg;
import beans.Location;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAL
{

    private LinkedList<Location> locations = new LinkedList<Location>();
    private LinkedList<Leg> legs = (LinkedList<Leg>) new LinkedList<Leg>();

    public LinkedList<Location> getLocations()
    {
        return locations;
    }

    public void setLocations(LinkedList<Location> locations)
    {
        this.locations = locations;
    }

    public LinkedList<Leg> getLegs()
    {
        return legs;
    }

    public void setLegs(LinkedList<Leg> legs)
    {
        this.legs = legs;
    }

    public LinkedList<Location> loadLocations( BufferedReader br)
    {
        //gleich wie legs (br)
        try
        {
          
            String line = "";
            line = br.readLine();
            while ((line = br.readLine()) != null)
            {

                line = line.replace("\"", "");
               
                String[] splits = line.split(";");
                if (!(splits[3].equals("") || splits[4].equals("")))
                {
                    this.locations.add(new Location(splits[0], splits[1], Integer.parseInt(splits[2]),
                        Double.parseDouble(splits[3]), Double.parseDouble(splits[4]), Integer.parseInt(splits[5]), splits[6], splits[7]));
                }

            }
        } catch (FileNotFoundException ex)
        {
            Logger.getLogger(DAL.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex)
        {
            Logger.getLogger(DAL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return locations;
    }

    public LinkedList<Leg> loadLegs(BufferedReader br) throws FileNotFoundException, IOException
    {
        //Statt File BufferedReader verwenden für Test

        
        String line = "";
        line = br.readLine();

        while ((line = br.readLine()) != (null))
        {

            line = line.replace("\"", "");
            String[] lines = line.split(";");
            if (lines.length == 4)
            {
                Leg l = new Leg(lines[0], lines[1], lines[2], Double.parseDouble(lines[3]));
                legs.add(l);
            } else if (lines.length == 3)
            {
                 
                Leg l = new Leg(lines[0], lines[1], lines[2], 0);
               
              
                legs.add(l);
            }

        }
        return legs;
    }

    public Leg getLegFromKey(LinkedList<Leg> legs, String Key)
    {
        for (Leg l : legs)
        {
            if (l.getKey().equals(Key))
            {
                
                return l;
            }
        }
        return null;
    }

    public Location[] getLocationsFromLeg(Leg l)
    {
        
        System.out.println(l.getSource_location_key()+"||"+l.getTarget_location_key());

        Location[] locs = new Location[2];
        int i = 0;
        for (Location loc : locations)
        {
            if (loc.getKey().equals(l.getSource_location_key()))
            {

                locs[i] = loc;
                i++;
            }
            if (loc.getKey().equals(l.getTarget_location_key()))
            {

                locs[i] = loc;
                i++;
            }
            if (i > 1)
            {
                break;
            }
        }

        return locs;
    }
}
