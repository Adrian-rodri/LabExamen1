package labexamen1;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import javax.swing.ImageIcon;

public class Game extends RentItem implements MenuActions{

    private Calendar fechaPublicacion;
    private ArrayList<String> especificaciones;
    private ImageIcon imagen; 

   
    public Game(String codigoUnico, String nombreItem, String imagen){
        super(codigoUnico, nombreItem, 20.0); 
        this.fechaPublicacion = Calendar.getInstance(); 
        this.especificaciones = new ArrayList<String>();
        this.imagen = null; 
    }

   
    @Override
    public double getPrecioRenta(){
        return 20.0;
    }

    
    @Override
    public double pagoRenta(int dias){
        return getPrecioRenta() * dias;
    }

   
    public void setFechaPublicacion(int year, int mes, int dia){
        fechaPublicacion.set(Calendar.YEAR, year);
        fechaPublicacion.set(Calendar.MONTH, mes - 1); // Calendar usa 0–11
        fechaPublicacion.set(Calendar.DAY_OF_MONTH, dia);
    }

 
    public void listEspecificaciones(){
        listEspecificacionesRecursivo(0);
    }

    private void listEspecificacionesRecursivo(int index){
        if (index >= especificaciones.size()) return; 
        System.out.println("  [" + (index + 1) + "] " + especificaciones.get(index));
        listEspecificacionesRecursivo(index + 1); 
    }

    
    public void agregarEspecificacion(String spec){
        especificaciones.add(spec);
    }

  
    @Override
    public String toString() {
        int day  = fechaPublicacion.get(Calendar.DAY_OF_MONTH);
        int mes  = fechaPublicacion.get(Calendar.MONTH) + 1;
        int year = fechaPublicacion.get(Calendar.YEAR);

        return super.toString()
             + "  Fecha publicación: " + day + "/" + mes + "/" + year
             + " – PS3 Game";
    }

 
    public Calendar getFechaPublicacion(){ 
        return fechaPublicacion; 
    }
    
    @Override 
    public ImageIcon getImagen(){ 
        return imagen; 
    }

 

    public void mostrarMenu() {
        System.out.println("\n <<<< MENÚ GAME: " + getNombreItem() + " >>>>");
        System.out.println("1. Actualizar fecha de publicación");
        System.out.println("2. Agregar especificación");
        System.out.println("3. Ver especificaciones");
        System.out.println("0. Volver");
    }

    @Override
    public void ejecutarOpcion(int opcion) {
        Scanner sc = new Scanner(System.in);

        switch (opcion) {
            case 1:
                System.out.print("Año: "); 
                int y = sc.nextInt();
                System.out.print("Mes: ");  
                int m = sc.nextInt();
                System.out.print("Día: ");  
                int d = sc.nextInt();
                setFechaPublicacion(y, m, d);
                System.out.println("Fecha actualizada.");
                break;

            case 2:
                System.out.print("Especificación: ");
                sc.nextLine(); // limpiar buffer
                String spec = sc.nextLine();
                agregarEspecificacion(spec);
                System.out.println(" Especificación agregada.");
                break;

            case 3:
                if (especificaciones.isEmpty()) {
                    System.out.println("No hay especificaciones registradas.");
                } else {
                    System.out.println("── Especificaciones de " + getNombreItem() + " ──");
                    listEspecificaciones();
                }
                break;

            case 0:
                System.out.println("Volviendo");
                break;

            default:
                System.out.println("Opción no válida.");
        }
    }

    @Override
    public void submenu() {
       
    }
}