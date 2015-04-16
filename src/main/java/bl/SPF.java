
package bl;

import beans.Leg;
import beans.Location;
import dal.DAL;
import java.util.LinkedList;

public class SPF {
    DAL dal;
    private double legdistance;

    public double getLegdistance() {
        return legdistance;
    }
  public  boolean compareLegs(Location A, Location C, LinkedList<Location> loclist, LinkedList<Leg> leglist, double googledistance )  {
   //Leg x und y finden, die gemeinsamen Punkt B haben und bei A bzw. C beginnen
     dal=new DAL();
     LinkedList<Leg> xCandidates=new LinkedList<Leg>();
     LinkedList<Leg> yCandidates=new LinkedList<Leg>();
      Leg x=new Leg();
      Leg y=new Leg();
    for(Leg l:leglist){
        Location[] locarray=dal.getLocationsFromLeg(l);
        if(locarray[0].equals(A)||locarray[1].equals(A)){
            xCandidates.add(l);
        }
          if(locarray[0].equals(C)||locarray[1].equals(C)){
            yCandidates.add(l);
        }
          
          //Herausfinden, ob ein paar aus koordinaten eine gemeinsame Location haben
           Location[] xLocs,yLocs;
           int ix,iy;
          for (int i = 0; (i < xCandidates.size()) &&( i<yCandidates.size()); i++) {
            xLocs=dal.getLocationsFromLeg(xCandidates.get(i));
            yLocs=dal.getLocationsFromLeg(yCandidates.get(i));
           if(xLocs[0].equals(A)){
               ix=1;
           }else{
               ix=0;
           }
           
           if(yLocs[0].equals(C)){
               iy=1;
           }else{
               iy=0;
           }
           
           if(xLocs[ix].equals(yLocs[iy])){
               //Location B wurde gefunden!
               x=xCandidates.get(i);
               y=yCandidates.get(i);
           }
        }
    }
     legdistance=x.getDistance()+y.getDistance();
    if(legdistance<googledistance){
        return true;
    }else if(legdistance>googledistance){
        return false;
    }else{
        return false;
    }
  }
}
