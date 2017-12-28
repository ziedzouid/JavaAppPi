/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import models.Actualite;
import models.Circle;
import models.Conducteur;
import models.Feedback;
import models.Message;
import models.User;
import services.ActualiteService;
import services.CircleService;
import services.ConducteurService;
import services.FeedbackService;
import services.MessageService;
import services.UserService;

/**
 *
 * @author ASUS
 */




public class CoolVoituarge {

 
    
    
    public static void main(String[] args) throws ParseException {
        // TODO code application logic here
        //String timeStamp = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());
        //  System.out.println(timeStamp);

        //IFeedbackService feedbackService = new FeedbackService();
        //Feedback feedback = new Feedback("Reclamation", "J'ai trouvé quelque bugs dans la phase d'ajout d'un trajet", 123);
        //feedbackService.add(feedback);
        //Feedback feedback1 = feedbackService.findById(1);
        //System.out.println(feedback1);
        //feedback.setTitre("autre");
        // System.out.println(feedback.getId());  
        // feedbackService.remove(4);
        /*  IActualiteService actualiteService=new ActualiteService();
        
        Actualite actualite = new Actualite("Temoiniage", "Culture", "tunisie 3000 ans d'histoire",159);
        
        actualiteService.add(actualite);*/
 /*IMessageService messageService = new MessageService();
        
        Message message = new Message("testing some bugs", "achraf", 11);
        
        messageService.add(message);*/
        //Message message1= messageService.findById(2);
        //System.out.println(message1);
        // messageService.remove(1);
       
        
        
        /*Circle c = new Circle("PIdev", "Education", "Ici on développe!!");
        CircleService cs = new CircleService();
        //cs.add(c);
        Circle c1 =cs.findById(4);
        c1.getId();
        //System.out.println(c1.getId());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date d2 = sdf.parse("2017/09/25");
        Conducteur u = new Conducteur("kahla", "hayder", "masculin", d2, 251425148, "haydeer@gmail.com", "haahaha", "defaultavatar", c1);
        ConducteurService conducteurService = new ConducteurService();
        //conducteurService.add(u);
        
        User u2 = new UserService().findById2(12);
        System.out.println(u2);
        
        Actualite a = new Actualite("Validation", "Java", "En train de faire l'integration", u2);
        ActualiteService as = new ActualiteService();
        //as.add(a);
        
        Feedback feedback = new Feedback("BienSatisfait", "Un peu de lenteur a ameliorer !!", u2);
        FeedbackService feedbackService = new FeedbackService();
        //feedbackService.add(feedback);
        
        Message m = new Message("Bonsoir !! Comment vas tu ?", "hayder", 12, 6);
        MessageService ms = new MessageService();
        ms.add(m);*/
        
        CircleService circleService = new CircleService();
        
        System.out.println(circleService.findUsersByCircle(2)); 
        
        
        

    }


}
