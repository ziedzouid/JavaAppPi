/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;
//import java.time.Clock;
//import java.time.LocalTime;

import java.time.LocalDateTime;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import interfaces.IEvaluationService;
import interfaces.IPassagerService;
import interfaces.IPosteService;
import models.Admin;
import models.Circle;
import models.Evaluation;
import models.Passager;
import models.Poste;
import models.Trajet;
import services.EvaluationService;
import services.PassagerService;
import services.PosteService;

/**
 * @Temporal(TemporalType.TIMESTAMP)
 * @author Mohamed Shil < Mohamed shil at esprit.tn>
 */
public class MyTaskPi {

    /**
     * @param args the command line arguments
     * @throws java.text.ParseException
     */
    public static void main(String[] args) throws ParseException {
        /*   // TODO code application logic here
      //   System.out.println(Date.);
        System.out.println(LocalDate.now().toString());
        System.out.println(LocalTime.now().toString());
        String s =LocalDate.now().toString()+ " "+LocalTime.now().toString() ;
     // Timestamp date = Timestamp.;
       // System.out.println(date);
      Date date =Date.from(Instant.now());
        System.out.println(s);
       // System.out.println( LocalTime.now());
       // String str="2015-03-31"; 
        Date date = java.sql.Date.valueOf( LocalDateTime.now() );
       // SimpleDateFormat d1= new SimpleDateFormat("MM-dd-yyyy").format(date);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     //date = sdf.parse("2017-10-04 01:07:03");
     //date=Date.from(DateTime.atZone(ZoneId.systemDefault()).toInstant());
        //System.out.println(d2);
        
         */
        Date date = Date.valueOf(LocalDate.now());
        Timestamp a = Timestamp.valueOf(LocalDateTime.now());
        IEvaluationService evaluationService = new EvaluationService();
         IPosteService posteService = new PosteService();
         IPassagerService ps=new PassagerService();
        Evaluation e;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        java.util.Date d2 = sdf.parse("2017/09/25");

        Circle c1 =new Circle(2,"txt","azert","description");

        Passager p = new Passager(25, "ayouta", "chihi", "Feminin", d2, 92714774, "ayachihi@hotmail.com", "714", "insaf.jpg", c1);
        Trajet trajet = new Trajet(1, "ville_depart", "ville_arrive", date, a, 0, 0, 0, 0);
         Poste poste = new Poste("oussema", p);
        // e = new Evaluation((byte) 2, "commentair", 5, 5);
        e = new Evaluation((byte) 20, "commentaire", trajet, p);
        // Evaluation e1 = new Evaluation((byte) 2, a, "update", 7, 6);
        // evaluationService.addEvaluationWithStatement(e);
     //      evaluationService.add(e);
      //  System.out.println(e.getId());
        //evaluationService.update(e);
        //evaluationService.remove(2);
       // System.out.println( evaluationService.getAll());
        // posteService.addEmvaluationWithStatement(p);
        //  evaluationService.remove(1);
        Timestamp t= Timestamp.valueOf("2017-10-23 22:25:04");
        //Poste
      //    posteService.add(poste);
          //poste.setDate_poste(t);
          //posteService.update(poste);
       //   System.out.println(poste);
        
          p=ps.findById(/*e.getPassager().getId())*/3);
          System.out.println(p);
        /* Poste p1 =new Poste(1, "desole", date, u);
        posteService.update(p1);*/
    }

}
