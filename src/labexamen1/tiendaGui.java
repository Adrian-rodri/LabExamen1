package labexamen1;

/**
 *
 * @author adria
 */
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.filechooser.FileNameExtensionFilter;
public class tiendaGui extends JFrame {
    //ArrayList
    ArrayList<RentItem> items= new ArrayList<>();
    //Colores
    Color bgr= new Color(0xA5C89E);
    Color txt= new Color(0x30364F);
    Color btn= new Color(0xD8E983);
    Color sub= new Color(0xAEB877);
    
    //Botones
    JButton btnAddItem= new JButton("Agregar Item");
    JButton btnRentar= new JButton("Rentar");
    JButton btnSubMenu= new JButton("Ejecutar Submenu");
    JButton btnPrint= new JButton("Imprimir Todo");
    //labels
    JLabel titulo= new JLabel("Tienda Multimedia"); 
    tiendaGui(){
        this.setSize(800,800);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setBackground(bgr);
        this.setLocationRelativeTo(null);
        Movie m= new Movie("1","1",1);
        items.add(m);
        //Labels
        titulo.setForeground(txt);
        titulo.setBounds(280,160,500,30);
        titulo.setFont(new Font("Calibri",Font.BOLD,30));
        //Botones
        btnAddItem.setBackground(btn);
        btnAddItem.setBounds(300,200,200,30);
        btnAddItem.setForeground(txt);
        //
        btnRentar.setBackground(btn);
        btnRentar.setBounds(300,240,200,30);
        btnRentar.setForeground(txt);
        //
        btnSubMenu.setBackground(btn);
        btnSubMenu.setBounds(300,280,200,30);
        btnSubMenu.setForeground(txt);
        //
        btnPrint.setBackground(btn);
        btnPrint.setBounds(300,320,200,30);
        btnPrint.setForeground(txt);
        //Actions
        //Add item
        btnAddItem.addActionListener(e->{
            String[] options={"Movie","Game","Cancelar"};
            String nombre;
            String codigo;
            double precio;
            int eleccion=JOptionPane.showOptionDialog(this, "Que deseas Agregar", "Add Item", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,options, options[0]);
            switch (eleccion){
                case 0:
                    codigo=JOptionPane.showInputDialog("Ingrese el Codigo Unico de la MOVIE");
                    if(noExisteItem(codigo)){
                        nombre=JOptionPane.showInputDialog("Ingrese el nombre de la MOVIE");
                        precio= Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio de la MOVIE"));
                        ImageIcon imagen = null;
                        JFileChooser elegirImagen = new JFileChooser();
                        elegirImagen.setDialogTitle("Eliga una imagen para la MOVIE");
                        elegirImagen.setFileFilter( new FileNameExtensionFilter("Imágenes (jpg, png)","png","jpg","jpeg"));
                        
                        int resultado= elegirImagen.showOpenDialog(this);
                        if (resultado== JFileChooser.APPROVE_OPTION) {
                            String ruta= elegirImagen.getSelectedFile().getAbsolutePath();
                            Image img = new ImageIcon(ruta).getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH);
                            imagen = new ImageIcon(img);
                        }
                        Movie nuevaMovie= new Movie(codigo,nombre,precio);
                        if(imagen!=null)
                        nuevaMovie.setImagen(imagen);
                        items.add(nuevaMovie);
                        JOptionPane.showMessageDialog(this, "MOVIE agregada correctamente");
                        
    
                        break;
                    }else{
                        JOptionPane.showMessageDialog(this, "El codgio ya esta en uso");
                        break;
                    }
                    
                case 1:
                    codigo=JOptionPane.showInputDialog("Ingrese el Codigo Unico del GAME");
                    if(noExisteItem(codigo)){
                        nombre=JOptionPane.showInputDialog("Ingrese el nombre de el GAME");
                        ImageIcon imagen = null;
                        JFileChooser elegirImagen = new JFileChooser();
                        elegirImagen.setDialogTitle("Eliga una imagen para el Game");
                        elegirImagen.setFileFilter( new FileNameExtensionFilter("Imágenes (jpg, png)","png","jpg","jpeg"));
                        
                        int resultado = elegirImagen.showOpenDialog(this);
                        if(resultado==JFileChooser.APPROVE_OPTION){
                            String ruta= elegirImagen.getSelectedFile().getAbsolutePath();
                            Image img= new ImageIcon(ruta).getImage().getScaledInstance(150,200, Image.SCALE_SMOOTH);
                            imagen= new ImageIcon(img);
                        }
                        Game nuevoGame= new Game(codigo,nombre);
                        if(imagen!=null)
                        nuevoGame.setImagen(imagen);
                        items.add(nuevoGame);
                        JOptionPane.showMessageDialog(this, "Game agregado correctamente");
                        break;
                    }else{
                        JOptionPane.showMessageDialog(this, "El codgio ya esta en uso");
                        break;
                    }
                    
                    
            }
        });
        //rentar
        btnRentar.addActionListener(e->{
        
        
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
        });
        //submenu
        btnSubMenu.addActionListener(e->{
        
        
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
        });
        //print
        btnPrint.addActionListener(e->{
            JDialog dialog = new JDialog(this, "Todos los Items", true);
    dialog.setSize(800, 600);
    dialog.setLocationRelativeTo(this);

    // Panel con scroll
    JPanel panelItems = new JPanel();
    panelItems.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
    panelItems.setBackground(bgr);

    for (RentItem item : items) {
        // Tarjeta por item
        JPanel tarjeta = new JPanel();
        tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
        tarjeta.setBackground(btn);
        tarjeta.setBorder(BorderFactory.createLineBorder(txt, 2));
        tarjeta.setPreferredSize(new Dimension(160, 280));

        // Imagen
        JLabel lblImg = new JLabel();
        lblImg.setAlignmentX(Component.CENTER_ALIGNMENT);
        if (item.getImagen() != null) {
            lblImg.setIcon(item.getImagen());
        } else {
            lblImg.setText("Sin imagen");
        }

        // Nombre
        JLabel lblNombre = new JLabel(item.getNombreItem());
        lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblNombre.setForeground(txt);
        lblNombre.setFont(new Font("Calibri", Font.BOLD, 13));

        // Precio
        JLabel lblPrecio = new JLabel("Precio: " + item.getPrecioRenta() + " Lps");
        lblPrecio.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblPrecio.setForeground(txt);

        // Estado (solo si es Movie)
        if (item instanceof Movie) {
            Movie ma = (Movie) item;
            JLabel lblEstado = new JLabel("Estado: " + ma.getEstado());
            lblEstado.setAlignmentX(Component.CENTER_ALIGNMENT);
            lblEstado.setForeground(txt);
            tarjeta.add(Box.createVerticalStrut(5));
            tarjeta.add(lblEstado);
        }

        // Tipo
        JLabel lblTipo = new JLabel(item instanceof Movie ? "🎬 Movie" : "🎮 Game");
        lblTipo.setAlignmentX(Component.CENTER_ALIGNMENT);
        lblTipo.setForeground(txt);

        tarjeta.add(Box.createVerticalStrut(5));
        tarjeta.add(lblImg);
        tarjeta.add(Box.createVerticalStrut(5));
        tarjeta.add(lblNombre);
        tarjeta.add(Box.createVerticalStrut(3));
        tarjeta.add(lblPrecio);
        tarjeta.add(Box.createVerticalStrut(3));
        tarjeta.add(lblTipo);

        panelItems.add(tarjeta);
    }

    // Si no hay items
    if (items.isEmpty()) {
        JLabel vacio = new JLabel("No hay items registrados");
        vacio.setForeground(txt);
        panelItems.add(vacio);
    }

    JScrollPane scroll = new JScrollPane(panelItems);
    dialog.add(scroll);
    dialog.setVisible(true);
        
        
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
        });
        //add
        add(titulo);
        add(btnAddItem);
        add(btnRentar);
        add(btnSubMenu);
        add(btnPrint);
        this.setVisible(true);
    }
    void mostrarMenu(boolean t){
        titulo.setVisible(t);
        btnAddItem.setVisible(t);
        btnRentar.setVisible(t);
        btnSubMenu.setVisible(t);
        btnPrint.setVisible(t);
    }
    boolean noExisteItem(String codigo){
        for(RentItem item:items){
            if(item.getCodigoUnico().equals(codigo))
                return false;
        }
        return true;
    }
    
    
}
