/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import models.Admin;
import models.Conducteur;
import models.Passager;

import interfaces.IAdminService;
import interfaces.IConducteurService;
import services.PassagerService;
     
import interfaces.IPassagerService;
import java.sql.SQLException;
import models.Circle;
import models.Trajet;
import models.User;
import services.AdminService;
import services.CircleService;
import services.ConducteurService;
import services.TrajetService;
import services.UserService;


/**
 *
 * @author esprit
 */
public class Pidev4infob {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException, SQLException  {
         
        
     
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd"); 
        Date d2 = sdf.parse("2017/09/25");
     // Circle c1 =new Circle(2,"informatics","educatifs","Partagons nos connaisances "); 
     Circle c1=null;
     CircleService cercles=new CircleService();
     c1=cercles.findById(3);
        System.out.println(c1);
   //Admin a1 =new Admin("aya","aya","Feminin",d2,92714774,"insafchihi@hotmail.com",714,"insaf.jpg");

//Admin a =new Admin("insaf","chihi","Feminin",d2,92714774,"insafchihi@hotmail.com",714,"insaf.jpg");
//Passager p=new Passager("ayouta","chihi","Feminin",d2,92714774,"ayachihi@hotmail.com",714,"insaf.jpg",8);

Conducteur c=new Conducteur(8,"lobna","manel","Male",d2,927,"medchihi@live.com","714","insaf.jpg",c1);
        System.out.println(c.getCircle());
      //  System.out.println(c);
IPassagerService ps =new PassagerService();
IConducteurService cs=new ConducteurService();
IAdminService as =new AdminService();
TrajetService ts=new TrajetService();
        System.out.println(ts.UserInTarget(c));

//cs.update(c);
//cs.add(c);
cs.update(c);
       // System.out.println(c);
//ps.add(p);
/*ps.add(p1);*/
/*ps.add(p);*/
//as.add(a);
//cs.add(c);
/*cs.update(c);
ps.update(p);
as.update(a);*/
//as.remove(4);
//as.add(a1);
//as.update(a1);
//System.out.println(as.getAll());
//System.out.println(cs.getAll());
//System.out.println(ps.getAll());
//System.out.println(as.findById(6));
//System.out.println(cs.findById(7));*/
UserService us=new UserService();
int x;
 User u=null;
 Passager p=null;
  p=ps.findById(4);
//System.out.println(p.getCircle());
  // u=us.findById(3);
        //System.out.println(u.getSexe().equals("Female"));
    //    System.out.println(x);

    }
    
}
