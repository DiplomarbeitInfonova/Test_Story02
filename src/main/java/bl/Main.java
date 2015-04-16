/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import beans.Leg;
import beans.Location;
import dal.DAL;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.JSONException;

/**AT0625_AT0622

 *
 * @author Veronika
 */
public class Main
{

    private File selectedFile;
    private DAL dal = new DAL();

    public static void main(String[] args) throws IOException, URISyntaxException, JSONException
    {
        Main m = new Main();
        m.showLegs();
    }

    private void showLegs() throws JSONException
    {
       
        try
        {
            FileChooser fc = new FileChooser();
            File locationfile = fc.onFileChooser("Locations");
            File legfile = fc.onFileChooser("Legs");
            
            LinkedList<Leg> leglist = dal.loadLegs(new BufferedReader(new FileReader(legfile)));
         

            LinkedList<Location> loclist = dal.loadLocations(new BufferedReader(new FileReader(locationfile)));
            String gesamtKey = JOptionPane.showInputDialog(null, "KEY1_KEY2");

            Leg l = dal.getLegFromKey(leglist, gesamtKey);
            
        
            Location[] locs = dal.getLocationsFromLeg(l);
            System.out.println("src: " + locs[0].getKey());
            System.out.println("target: "+locs[1].getKey());
            if (l.checkDistance())
            {

                FileFiltering filefiltering = new FileFiltering();
                filefiltering.inputMissingColumns(l, locs);
            }
//"http://maps.googleapis.com/maps/api/distancematrix/json?origins=" + locs[0].getLatitude() + "," + locs[0].getLongitude() + "&destinations=" + locs[1].getLatitude() + "," + locs[1].getLongitude() + ""
            String url="http://maps.googleapis.com/maps/api/directions/json?origin=" + locs[0].getLatitude() + "," + locs[0].getLongitude() + "&destination=" + locs[1].getLatitude() + "," + locs[1].getLongitude() + "";
           //System.out.println(url);
            TextURL texturl = new TextURL(url);
            String response=texturl.read();
            JSONParser jsonparser = new JSONParser(response);
            


//System.out.println(texturl.read());
            // String response=texturl.read().substring(texturl.read().indexOf("km")-5,texturl.read().indexOf("km"));
            SPF spf=new SPF();
            boolean googleorleg=spf.compareLegs(locs[0], locs[1], loclist, leglist,100);
            System.out.println("Google distance: " + l.getDistance());
             System.out.println("Compared leg distance: " + spf.getLegdistance());
            System.out.println("--------------------------------------------------");
            String output = "Key: " + l.getKey() + "; Old Distance: " + l.getDistance() + " km; New Distance: " + jsonparser.getDistanceFromJSON();
            System.out.println(output);
            System.out.println("--------------------------------------------------");
            IntoCSV into = new IntoCSV();

     //temp. down       into.writecsv(l.getKey(), l.getDistance() + "", jsonparser.getDistanceFromJSON());

        } catch (IOException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
