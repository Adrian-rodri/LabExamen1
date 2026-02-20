package labexamen1;

/**
 *
 * @author adria
 */
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.Calendar;
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
    JButton btnBack= new JButton("Regresar");
    //labels
    JLabel titulo= new JLabel("Tienda Multimedia"); 
    //
    JPanel panelItems= new JPanel();
    JScrollPane scroll = new JScrollPane(panelItems);
    tiendaGui(){
        this.setSize(800,800);
        this.setLayout(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.getContentPane().setBackground(bgr);
        this.setLocationRelativeTo(null);
        Movie m= new Movie("M-001","Toy Story",145);
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
        //
        btnBack.setBackground(btn);
        btnBack.setForeground(txt);
        btnBack.setVisible(false);
        //
        scroll.setBounds(0, 40, 800, 760);
        scroll.setVisible(false);
        add(scroll);
        //Actions
        //Boton regresar
        btnBack.addActionListener(e->{
            mostrarMenu(true);
            btnBack.setVisible(false);
       
            panelItems.setVisible(false);
            scroll.setVisible(false);
        
        });
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
                    if(codigo.isEmpty()|| codigo==null)
                        return;
                    if(noExisteItem(codigo)){
                        nombre=JOptionPane.showInputDialog("Ingrese el nombre de la MOVIE");
                        if(nombre.isEmpty()){
                            JOptionPane.showMessageDialog(this, "El nombre no puede estar vacio");
                            return;
                        }
                        precio= Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio de la MOVIE"));
                        
                        ImageIcon imagen =null;
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
                    if(codigo.isEmpty()|| codigo==null)
                        return;
                    if(noExisteItem(codigo)){
                        nombre=JOptionPane.showInputDialog("Ingrese el nombre de el GAME");
                        if(nombre.isEmpty()){
                            JOptionPane.showMessageDialog(this, "El nombre no puede estar vacio");
                            return;
                        }
                        ImageIcon imagen= null;
                        JFileChooser elegirImagen =new JFileChooser();
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
            String codigo = JOptionPane.showInputDialog(this, "Ingrese el código del ítem a rentar:");
            if (codigo== null) 
                return;
            RentItem itemEncontrado = null;
            
            for (RentItem item : items) {
                if (item.getCodigoUnico().equals(codigo)) {
                    itemEncontrado=item;
                    break;
                }
            }
            if (itemEncontrado== null) {
                JOptionPane.showMessageDialog(this, "Item No Existe");
                return;
            }
            JPanel panelInfo =new JPanel();
            panelInfo.setLayout(new BoxLayout(panelInfo, BoxLayout.Y_AXIS));

            if (itemEncontrado.getImagen()!= null) {
                panelInfo.add(new JLabel(itemEncontrado.getImagen()));
            }
            panelInfo.add(new JLabel("Código:" +itemEncontrado.getCodigoUnico()));
            panelInfo.add(new JLabel("Nombre: "+itemEncontrado.getNombreItem()));
            panelInfo.add(new JLabel("Precio base: Lps." +itemEncontrado.getPrecioRenta()));

            if (itemEncontrado instanceof Movie) {
                panelInfo.add(new JLabel("Estado: " +((Movie) itemEncontrado).getEstado()));
            }
            
            JOptionPane.showMessageDialog(this, panelInfo);
            String txtDias = JOptionPane.showInputDialog(this, "¿Cuántos días desea rentar?");
            if (txtDias == null|| txtDias.isEmpty()) 
                return;
            else if(txtDias.matches("\\d+")){
                int dias = Integer.parseInt(txtDias);
                double total = itemEncontrado.pagoRenta(dias);
                JOptionPane.showMessageDialog(this, "Total a pagar: " + total + " Lps por " + dias + " días");
            }else
                JOptionPane.showMessageDialog(this, "Ingrese un número válido");    
        });
        
        //submenu
        btnSubMenu.addActionListener(e -> {
            String codigo=JOptionPane.showInputDialog(this, "Ingrese el código del ítem:");
            if (codigo ==null) 
                return;
            RentItem itemEncontrado= null;
            for (RentItem item :items) {
                if (item.getCodigoUnico().equals(codigo)) {
                    itemEncontrado=item;
                    break;
                }
            }

            if (itemEncontrado==null) {
                JOptionPane.showMessageDialog(this, "Item No Existe");
                return;
            }
            
        if (itemEncontrado instanceof Movie) {
            Movie mo=(Movie)itemEncontrado;
            String[] opcionesMovie={"Cambiar fecha de estreno", "Cancelar"};
            int opcion=JOptionPane.showOptionDialog(this, "Submenu: " + mo.getNombreItem(),"Submenu Movie", JOptionPane.DEFAULT_OPTION,JOptionPane.PLAIN_MESSAGE, null, opcionesMovie, opcionesMovie[0]);

            if (opcion==0) {
                String txtYear=JOptionPane.showInputDialog("Ingrese el año (YYYY):");
                String txtMes=JOptionPane.showInputDialog("Ingrese el mes (1-12):");
                String txtDias= JOptionPane.showInputDialog("Ingrese el día (1-31):");

                if (txtYear!=null && txtMes!=null && txtDias!=null && txtYear.matches("\\d+") && txtMes.matches("\\d+") && txtDias.matches("\\d+")) {
                    int year= Integer.parseInt(txtYear);
                    int mes= Integer.parseInt(txtMes);
                    int dia=Integer.parseInt(txtDias);

                    Calendar fecha=Calendar.getInstance();
                    fecha.set(year, mes - 1, dia);

                    mo.setFechaEstreno(fecha);
                    JOptionPane.showMessageDialog(this, "Fecha actualizada. Estado: " + mo.getEstado());
                } else 
                    JOptionPane.showMessageDialog(this, "Operacion cancelada o datos no validos.");
            }

            } else if (itemEncontrado instanceof MenuActions) {
                Game g=(Game) itemEncontrado;
                String[] opcionesGame= { "Actualizar fecha de publicacion", "Agregar especificacion", "Ver especificaciones", "Cancelar"};

                int op=JOptionPane.showOptionDialog(this, "Submenu: " + g.getNombreItem(), "Submenú Game", JOptionPane.DEFAULT_OPTION, 
                        JOptionPane.PLAIN_MESSAGE, null, opcionesGame, opcionesGame[0]);

                if (op>=0 && op<3) {
                    g.ejecutarOpcionGUI(op + 1, this);
                }
            }
        });
        //print
        btnPrint.addActionListener(e->{
            mostrarMenu(false);
            btnBack.setVisible(true);
            btnBack.setBounds(0,0,100,30);
        //panel scroll
        panelItems.setVisible(true);
        panelItems.removeAll();
        panelItems.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelItems.setBackground(bgr);
        panelItems.repaint();

        for (RentItem item : items) {
            //Tarjeta por item
            JPanel tarjeta=new JPanel();
            tarjeta.setLayout(new BoxLayout(tarjeta, BoxLayout.Y_AXIS));
            tarjeta.setBackground(btn);
            tarjeta.setBorder(BorderFactory.createLineBorder(txt, 2));
            tarjeta.setPreferredSize(new Dimension(160, 280));

            //Imagen
            JLabel lblImg =new JLabel();
            lblImg.setAlignmentX(Component.CENTER_ALIGNMENT);
            if (item.getImagen()!=null) {
                lblImg.setIcon(item.getImagen());
            } else {
                lblImg.setText("Sin imagen");
            }

            //Nombre
            JLabel lblNombre=new JLabel(item.getNombreItem());
            lblNombre.setAlignmentX(Component.CENTER_ALIGNMENT);
            lblNombre.setForeground(txt);
            lblNombre.setFont(new Font("Calibri", Font.BOLD, 13));

            //Precio
            JLabel lblPrecio=new JLabel("Precio: " + item.getPrecioRenta() + " Lps");
            lblPrecio.setAlignmentX(Component.CENTER_ALIGNMENT);
            lblPrecio.setForeground(txt);

            //estreno o no
            if (item instanceof Movie) {
                Movie mo = (Movie) item;
                JLabel lblEstado=new JLabel("Estado: "+ mo.getEstado());
                lblEstado.setAlignmentX(Component.CENTER_ALIGNMENT);
                lblEstado.setForeground(txt);
                tarjeta.add(lblEstado);
            }

            //Tipo
            JLabel lblTipo=new JLabel(item instanceof Movie ? "Movie" : "Game");
            lblTipo.setAlignmentX(Component.CENTER_ALIGNMENT);
            lblTipo.setForeground(txt);

            tarjeta.add(lblImg);
            tarjeta.add(lblNombre);
            tarjeta.add(lblPrecio);
            tarjeta.add(lblTipo);

            panelItems.add(tarjeta);
        }

        // Si no hay items
        if (items.isEmpty()) {
            JLabel vacio = new JLabel("No hay items registrados");
            vacio.setForeground(txt);
            panelItems.add(vacio);
        }
        scroll.setViewportView(panelItems);
        scroll.setVisible(true);
        panelItems.repaint();
        scroll.repaint();
        this.setVisible(true);    
            });
            //add
            add(btnBack);
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
