/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.event.GMapMouseEvent;
import com.lynden.gmapsfx.javascript.event.MouseEventHandler;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.InfoWindow;
import com.lynden.gmapsfx.javascript.object.InfoWindowOptions;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.MapTypeIdEnum;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 *
 * @author achraf
 */
public class MapController implements Initializable, MapComponentInitializedListener {
    
    @FXML
    private Button button;
    
    @FXML
    private GoogleMapView mapView;
    
    private GoogleMap map;
    
    private String mode = "affichage";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        mapView.addMapInializedListener(this);
    }    

    @Override
    public void mapInitialized() {
        
        
        

        
        // -------------------------- Debut Affichage -----------------//
        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();
        
        mapOptions.center(new LatLong(36.8991156,10.1901885))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(true)
                .panControl(true)
                .rotateControl(true)
                .scaleControl(true)
                .streetViewControl(true)
                .zoomControl(true)
                .zoom(10);
                   
        map = mapView.createMap(mapOptions);

        //Add markers to the map
        
        // Creation d'un objet qui contient les coordonnees 
        LatLong joeSmithLocation = new LatLong(47.6197, -122.3231);
        // Creation d'un objet qui contient les options/proprietes/config d'un marqueur de position
        MarkerOptions markerOptions = new MarkerOptions();
        // Modification des parametres du marqueur (Affectation de la position
        markerOptions.position(joeSmithLocation);
        
        // Creation du marquer avec la configuration defini dans l'objet du type MarkerOptions
        Marker joeSmithMarker = new Marker(markerOptions);
        
       
        // L'ajout du marqueur à la map
        map.addMarker( joeSmithMarker );

        
        InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
        infoWindowOptions.content("<h2>Fred Wilkie</h2>"
                                + "Current Location: Safeway<br>"
                                + "ETA: 45 minutes" );

        InfoWindow fredWilkeInfoWindow = new InfoWindow(infoWindowOptions);
        // -------------------------- Fin Affichage -----------------//
        if(!mode.equals("affichage")){
        Marker mark_salah = new Marker(new MarkerOptions().position(new LatLong(0,0)));
        map.addMarker(mark_salah);
        map.addMouseEventHandler(UIEventType.click, new MouseEventHandler() {
            @Override
            public void handle(GMapMouseEvent gmme) {
                map.clearMarkers();
                mark_salah.setPosition(gmme.getLatLong());
                map.addMarker(mark_salah);
            }
        });
        }
        
        //fredWilkeInfoWindow.open(map, joeSmithMarker);
    }   
}
