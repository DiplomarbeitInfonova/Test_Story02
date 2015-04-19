
package bl;

import beans.Leg;
import beans.Location;
import dal.DAL;
import java.util.LinkedList;

public class SPF {
    DAL dal;
    private double legdistance;
    Leg x,y;
    public double getLegdistance() {
        return legdistance;
    }
    
    public SPF(DAL dal){
        this.dal=dal;
    }
  public  boolean compareLegs(Location A, Location C, LinkedList<Location> loclist, LinkedList<Leg> leglist, double googledistance )  {
   //Leg x und y finden, die gemeinsamen Punkt B haben und bei A bzw. C beginnen
      System.out.println("------------------------------------\nCOMPARE LEGS");
      System.out.println("A: "+A.getKey()+" -> C: "+C.getKey());
     
     LinkedList<Leg> xCandidates=new LinkedList<Leg>();
     LinkedList<Leg> yCandidates=new LinkedList<Leg>();
       x=new Leg();
      y=new Leg();
    for(Leg l:leglist){
        Location[] locarray=dal.getLocationsFromLeg(l);
        //herausfinden, ob es Legs gibt die bei A oder C anfangen bzw. aufhören
        if(locarray[0].equals(A)){
            xCandidates.add(l);
        }
          if(locarray[1].equals(C)){
            yCandidates.add(l);
        }
    }  
//      System.out.println("Candidates for X");
//    for(Leg xc:xCandidates){
//        System.out.println(xc.getKey());
//    }
//    System.out.println("Candidates for Y");
//    for(Leg yc:yCandidates){
//        System.out.println(yc.getKey());
//    }
      System.out.println("------");
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
    
      
      
     legdistance=x.getDistance()+y.getDistance();
     if(x==null||y==null||legdistance==0){
         return false;
     }else 
    if(legdistance<googledistance){
        return true;
    }else if(legdistance>googledistance){
        return false;
    }else{
        return false;
    }
  }
}
