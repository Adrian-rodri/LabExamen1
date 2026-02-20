
package labexamen1;

import java.util.ArrayList;
import java.util.Calendar; 
import java.util.Scanner; 

public class Game extends  RentItem {
    
    private Calendar fechaPublicacion; 
    private ArrayList<String> especificaciones; 
    private String imagen;
    
    public Game(String codigoUnico,String nombreItem, double precioRenta, Calendar fechaPulibcacion, ArrayList<String> especificaciones, String imagen){
    
        super(codigoUnico, nombreItem, 20); 
        this.fechaPublicacion = Calendar.getInstance(); 
        this.especificaciones = new ArrayList<String>(); 
        this.imagen = imagen; 
        
   }
    public double getPrecioRenta(){
        return 20.0; 
    }
    public void setFechaPublicacion(int year, int mes, int dia){
        
    }
     
    public ArrayList<String> listEspecificaciones(){
        return especificaciones; 
    }
   
    
}
