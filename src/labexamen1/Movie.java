/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labexamen1;
import java.util.Calendar;
/**
 *
 * @author user
 */
public class Movie extends RentItem {
    
    private Calendar fechaEstreno;
    public String Categoria = " ";
    
    
     Movie(String codigoUnico,String nombreItem, double precioRenta){
     super (codigoUnico,nombreItem, precioRenta);
    this.fechaEstreno = Calendar.getInstance();
}

    public Calendar getFechaEstreno() {
        return fechaEstreno;
    }

    public void setFechaEstreno(Calendar fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }
     
    
    @Override
    public String toString(){
    return super.toString() + "\nEstado: "+getEstado() + "\n Movie";
            
    }
    
    public String getEstado(){
        Calendar hoy = Calendar.getInstance();
        Calendar limite = (Calendar) fechaEstreno.clone();
        limite.add(Calendar.MONTH, 3);
        
        if (hoy.before(limite)){
            Categoria = "ESTRENO";
        }
        else {
            Categoria = "Normal";
        }
        return Categoria;
    }
    
    
    
    
    
     @Override
    public double pagoRenta(int dias){
        double precioRenta = getPrecioRenta() * dias;
        if ("ESTRENO".equals(getEstado())) {
            if (dias > 2) {
                precioRenta += 50.0 * (dias - 2);
            }
        } else { 
            if (dias > 5) {
                precioRenta += 30.0 * (dias - 5);
            }
        }
        return precioRenta;
        
    }
    
    
    
    
}
