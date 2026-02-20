package labexamen1;

import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import javax.swing.*;

public class Game extends RentItem implements MenuActions{

    private Calendar fechaPublicacion;
    private ArrayList<String> especificaciones;


   
    public Game(String codigoUnico, String nombreItem){
        super(codigoUnico, nombreItem, 20.0); 
        this.fechaPublicacion = Calendar.getInstance(); 
        this.especificaciones = new ArrayList<String>(); 
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
        int day= fechaPublicacion.get(Calendar.DAY_OF_MONTH);
        int mes= fechaPublicacion.get(Calendar.MONTH) + 1;
        int year= fechaPublicacion.get(Calendar.YEAR);

        return super.toString()
             + "  Fecha publicación: " + day + "/" + mes + "/" + year
             + " – PS3 Game";
    }

 
    public Calendar getFechaPublicacion(){ 
        return fechaPublicacion; 
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
        Scanner entrada = new Scanner(System.in);

        switch (opcion) {
            case 1:
                System.out.print("Año: "); 
                int y=entrada.nextInt();
                System.out.print("Mes: ");  
                int m= entrada.nextInt();
                System.out.print("Día: ");  
                int d= entrada.nextInt();
                setFechaPublicacion(y, m, d);
                System.out.println("Fecha actualizada.");
                break;

            case 2:
                System.out.print("Especificacion: ");
                entrada.nextLine();
                String spec = entrada.nextLine();
                agregarEspecificacion(spec);
                System.out.println(" Especificacion agregada.");
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
                System.out.println("Opcion no válida.");
        }
    }

    @Override
    public void submenu() {
       Scanner sc = new Scanner(System.in);
    int opcion = -1;

    do {
        mostrarMenu(); 
        System.out.print("Ingrese una opcion: ");

        
        if (sc.hasNextInt()) {
            opcion = sc.nextInt();
            ejecutarOpcion(opcion); 
        } else {
            System.out.println("Por favor ingrese un número valido.");
            sc.next(); 
        }

    } while (opcion != 0);
    }
    public void ejecutarOpcionGUI(int opcion, Component parent) {
    switch (opcion) {
        case 1:
            try {
                int y=Integer.parseInt(JOptionPane.showInputDialog(parent, "Año:"));
                int m=Integer.parseInt(JOptionPane.showInputDialog(parent, "Mes:"));
                int d= Integer.parseInt(JOptionPane.showInputDialog(parent, "Día:"));
                setFechaPublicacion(y, m, d);
                JOptionPane.showMessageDialog(parent, "Fecha actualizada.");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(parent, "Valor invalido");
            }
            break;
        case 2:
            String spec=JOptionPane.showInputDialog(parent, "Ingrese la especificacion:");
            if (spec!=null && !spec.isEmpty()) {
                agregarEspecificacion(spec);
                JOptionPane.showMessageDialog(parent, "Especificacion agregada.");
            }
            break;
        case 3:
            if (especificaciones.isEmpty()) {
                JOptionPane.showMessageDialog(parent, "No hay especificaciones.");
            } else {
                StringBuilder sb=new StringBuilder();
                for (int i = 0; i < especificaciones.size(); i++) {
                    sb.append((i+1) + ". " + especificaciones.get(i) + "\n");
                }
                JOptionPane.showMessageDialog(parent, sb.toString(), "Especificaciones", JOptionPane.PLAIN_MESSAGE);
            }
            break;
    }
}
}