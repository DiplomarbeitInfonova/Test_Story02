/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bl;

import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;

/**
 *
 * @author David
 */
public class FileChooser {
    
    public File onFileChooser(String type) throws IOException
    {
//        if(type.equals("Speicher Datei"))
//        {
//            JFileChooser chooser = new JFileChooser("E:/");
//           chooser.setCurrentDirectory(new File("E:\\"));
//            chooser.showOpenDialog(null);
//            return chooser.getSelectedFile();
//        }
        
        
        //JFileChooser fc = new JFileChooser("."+System.getProperty("file.separator")+"src"+System.getProperty("file.separator")+"res");
        String mypath=System.getProperty("user.dir")+System.getProperty("file.separator")+"src/main/java/res";
        JFileChooser fc = new JFileChooser(mypath);
        fc.setDialogTitle("Bitte die " + type + " auswählen!");
        if (fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
        {
            return fc.getSelectedFile();
        }
        return null;
    }
    
}
