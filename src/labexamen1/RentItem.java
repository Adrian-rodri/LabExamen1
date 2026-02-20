package labexamen1;

/**
 *
 * @author adria
 */
import javax.swing.ImageIcon;
import java.awt.Image;
public abstract class RentItem {
    private String codigoUnico;
    private String nombreItem;
    private double precioRenta;
    private int copiasDisponibles;
    private ImageIcon imagen;
    
    RentItem(String codigoUnico,String nombreItem, double precioRenta){
        this.codigoUnico=codigoUnico;
        this.nombreItem=nombreItem;
        this.precioRenta=precioRenta;
        this.copiasDisponibles=0;
        this.imagen=null;
    }
    public abstract double pagoRenta(int dias);
    
    @Override
    public String toString(){
        return "\nCodigo del Item: "+ codigoUnico+
                "\nNombre dle item: "+nombreItem+
                "\nPrecio de la renta:  Lps." +precioRenta+
                "\nCopias Disponibles"+ copiasDisponibles;
    }

    public String getCodigoUnico() {
        return codigoUnico;
    }

    public String getNombreItem() {
        return nombreItem;
    }

    public double getPrecioRenta() {
        return precioRenta;
    }

    public void cargarImagen(String ruta){
        this.imagen= new ImageIcon(ruta);
        this.imagen.getImage().getScaledInstance(150,200, Image.SCALE_SMOOTH);
    }

    public ImageIcon getImagen() {
        return imagen;
    }
    
    
}
