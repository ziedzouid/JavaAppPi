package tests;

import interfaces.ICircleService;
import interfaces.IProduitService;
import models.Circle;
import models.Produit;
import services.CircleService;
import services.ProduitService;



public class comunitytest {
    public static void main(String[] args) {
        
        ICircleService ics=new CircleService();
        
        Circle circle = new Circle("anonymos", "informatics", "we lead to perfection");
        
        ics.add(circle);
        
        System.out.println(ics.findById(3));
        
        
       /* IProduitService ips = new ProduitService();
        
        Produit p = new Produit("valise", "25 kg");
        
        ips.add(p);*/
        
        
    }
}