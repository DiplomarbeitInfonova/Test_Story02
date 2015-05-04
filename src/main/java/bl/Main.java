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

/**
 * AT0625_AT0622
 *
 *
 * @author Veronika
 */
public class Main {

    private DAL dal = new DAL();

    public static void main(String[] args) throws IOException, URISyntaxException, JSONException {
        Main m = new Main();
        m.showLegs();
    }

    private void showLegs() throws JSONException {

        try {
            FileChooser fc = new FileChooser();
            File locationfile = fc.onFileChooser("Locations");
            File legfile = fc.onFileChooser("Legs");

            LinkedList<Leg> leglist = dal.loadLegs(new BufferedReader(new FileReader(legfile)));

            LinkedList<Location> loclist = dal.loadLocations(new BufferedReader(new FileReader(locationfile)));
            String gesamtKey = JOptionPane.showInputDialog(null, "KEY1_KEY2");

            Leg l = dal.getLegFromKey(leglist, gesamtKey);

            Location[] locs = dal.getLocationsFromLeg(l);
            System.out.println("src: " + locs[0].getKey());
            System.out.println("target: " + locs[1].getKey());
            if (l.checkDistance()) {

                FileFiltering filefiltering = new FileFiltering();
                filefiltering.inputMissingColumns(l, locs);
            }
            String url = "http://maps.googleapis.com/maps/api/directions/json?origin=" + locs[0].getLatitude() + "," + locs[0].getLongitude() + "&destination=" + locs[1].getLatitude() + "," + locs[1].getLongitude() + "";
            TextURL texturl = new TextURL(url);
            String response = texturl.read();
            JSONParser jsonparser = new JSONParser(response);
            double googledistance = Double.parseDouble(jsonparser.getDistanceFromJSON().split(" ")[0]);

            SPF spf = new SPF(dal);

            System.out.println("Google distance: " + googledistance);
            boolean googleorleg = spf.compareLegs(locs[0], locs[1], loclist, leglist, googledistance);
            if (spf.getLegdistance() == 0) {
                System.out.println("Kein zusammengesetzer Leg gefunden!");
            } else {
                System.out.println("Zusammengesetzte leg distance: " + spf.getLegdistance());
            }

            if (googleorleg) {
                //zusammengesetzte distanz aus 2 Legs ist kleiner
                System.out.println("Zusammengesetze Distanz ist kleiner: ");
                System.out.println("Dafür verwendete legs:");
                System.out.println("Leg x: " + spf.x.getKey() + ", distance: " + spf.x.getDistance());
                System.out.println("Leg y: " + spf.y.getKey() + ", distance: " + spf.y.getDistance());
                System.out.println("--------------------------------------------------");
                String output = "Key: " + l.getKey() + "; Old Distance: " + l.getDistance() + " km; New Distance: " + spf.getLegdistance() + " km";
                System.out.println(output);
            } else {
                //Neue Distanz ist kleiner
                System.out.println("Neue Google- Distanz ist kleiner:");
                System.out.println("--------------------------------------------------");
                String output = "Key: " + l.getKey() + "; Old Distance: " + l.getDistance() + " km; New Distance: " + googledistance + " km";
                System.out.println(output);
            }

            IntoCSV into = new IntoCSV();

            //into.writecsv(l.getKey(), l.getDistance() + "", jsonparser.getDistanceFromJSON());
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
