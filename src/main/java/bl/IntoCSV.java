/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Veronika
 */
public class IntoCSV {

    private FileChooser fc = new FileChooser();

    public void writecsv(String key, String oldDis, String newDis) {

        try {
            FileWriter fw = new FileWriter(fc.onFileChooser("Speicher Datei"),true);
            BufferedWriter bw = new BufferedWriter(fw);
            
            bw.append(key+";"+oldDis+";"+newDis+"\n");
            bw.newLine();
            bw.flush();
            bw.close();

        } catch (IOException ex) {
            Logger.getLogger(IntoCSV.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
