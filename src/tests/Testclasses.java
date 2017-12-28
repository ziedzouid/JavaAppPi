/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tests;
import interfaces.IVoitureServices;
import interfaces.IEvenementServices;
import models.Voiture;
import models.Evenement;
import services.EvenementServices;
import services.VoitureServices;

      

/**
 *
 * @author DELL
 */
public class Testclasses {
    public static void main(String[] args) {
        IVoitureServices voituresServices = new VoitureServices();
        IEvenementServices evenementServices =new EvenementServices();
        
        
        //Voiture v =new Voiture(7,"audi","a4",1);
        //Voiture v1 =new Voiture(2,"seat","leaon",1);
        //Evenement e1 =new Evenement(1, "un challenge de cooding");
        Evenement e2 =new Evenement(2, "informatique","challenge de cooding");
        Evenement e3 =new Evenement(8,"aaaa","foire");
        //Evenement e4 =new Evenement(2, "lecture + écriture");
        //evenementServices.add(e3);
        //evenementServices.add(e4);
        System.out.println(evenementServices.findById(8));
        /*for (Evenement event : evenementServices.getAll() )
        {
            System.out.println(event);
        }*/
        
       evenementServices.update(e3);
        
        
        //evenementServices.add(e1);
        //evenementServices.add(e2);
        //evenementServices.remove(2);
        
        //voituresServices.update(v);
        //System.out.println(voituresServices.findById(7));
        //System.out.println(voituresServices.search(v));
        
        //Voiture v2 =new Voiture(2,"BMW","Série7",3);
        //Voiture v3 =new Voiture(3,"Mercedez_benz","CLASSE",2);
        /*for (Voiture voi : voituresServices.getAll())
         {
             System.out.println(voi);
             
         }*/
        //VoitureServices voitureServices =new VoitureServices();
       
         //voitureServices.add(v3);
         //voitureServices.add(v);
         //voitureServices.add(v2);
         //voitureServices.remove(17);
         
         
         
         
       
    }
}
