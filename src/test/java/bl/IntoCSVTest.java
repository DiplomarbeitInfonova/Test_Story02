/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

import java.io.IOException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Veronika
 */
public class IntoCSVTest {
    private IntoCSV instance;
    public IntoCSVTest() {
        instance= new IntoCSV();
    }
    

    /**
     * Test of writecsv method, of class IntoCSV.
     */
    @Test(expected = IOException.class)
    public void testifwritecsv() {
        System.out.println("writecsv");
        String key = "";
        String oldDis = "";
        String newDis = "";
        
        instance.writecsv(key, oldDis, newDis);
    }
    
}
