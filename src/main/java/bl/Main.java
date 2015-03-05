/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import beans.Leg;
import beans.Location;
import dal.DAL;
import java.io.File;
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
        System.out.println("TESTCOMMIT");
        try
        {
            FileChooser fc = new FileChooser();
            File locationfile = fc.onFileChooser("Locations");
            File legfile = fc.onFileChooser("Legs");
            LinkedList<Leg> leglist = dal.loadLegs(legfile);
            for (int i = 0; i < leglist.size(); i++)
            {
                System.out.println(leglist.get(i).toString());
            }

            LinkedList<Location> loclist = dal.loadLocations(locationfile);
            String gesamtKey = JOptionPane.showInputDialog(null, "KEY1_KEY2");

            Leg l = dal.getLegFromKey(leglist, gesamtKey);
            System.out.println("distanz: " + l.getDistance());
            System.out.println("");
            Location[] locs = dal.getLocationsFromLeg(l);
            System.out.println("distanz: " + l.getDistance());
            if (l.getDistance() == -1.0)
            {

                FileFiltering filefiltering = new FileFiltering();
                filefiltering.inputMissingColumns(l, locs);
            }

            TextURL texturl = new TextURL("http://maps.googleapis.com/maps/api/distancematrix/json?origins=" + locs[0].getLatitude() + "," + locs[0].getLongitude() + "&destinations=" + locs[1].getLatitude() + "," + locs[1].getLongitude() + "");
            JSONParser jsonparser = new JSONParser(texturl.read());
            // String response=texturl.read().substring(texturl.read().indexOf("km")-5,texturl.read().indexOf("km"));
            System.out.println("distanz: " + l.getDistance());
            System.out.println("--------------------------------------------------");
            String output = "Key: " + l.getKey() + "; Old Distance: " + l.getDistance() + " km; New Distance: " + jsonparser.getDistanceFromJSON();
            System.out.println(output);
            System.out.println("--------------------------------------------------");
            IntoCSV into = new IntoCSV();

            into.writecsv(l.getKey(), l.getDistance() + "", jsonparser.getDistanceFromJSON());

        } catch (IOException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
