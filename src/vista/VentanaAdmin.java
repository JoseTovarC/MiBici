package vista;

import javax.swing.*;
import javax.swing.event.MenuListener;

import control.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import images.Imagen;
import vista.InterfazVista;


public class VentanaAdmin extends JFrame implements InterfazVista {
	Container panelventana;
	Control control;
	// Definicion de variables/componentes
	public JPanel panelPrincipal;// panel principal = T O D O
	JPanel panel1; // Panel Norte, contiene el label1
	JPanel panel2; // Panel Sur, alias botonera.
	JPanel panel3; // Panel centro, ingresar datos.
	
	public JLabel label1; // Bienvenida del sistema a la opcion del sistema
	public JLabel label2; // Mensaje de ayuda
	public JLabel label3; // Boton para el registro
	public JLabel label4; // Ingrese su código de usuario y su clave.
	public JLabel label5; //Codigo de usuario
	public JLabel label6; // Clave
	
	private JButton boton1;// boton 1 = “Login Usuario”
	public JButton boton2; // boton 2 = “Inicio”
	
	TextArea texto1; // texto 1 = guia del sistema
	public JTextField texto2; // texto 2 = codigo de usuario
	public JPasswordField texto3; // texto 3 = clave del usuario

	Image imagen;
	JMenuBar arri;
	JMenu cuenta;
	JMenuItem inicio; 
	JMenuItem cerrarSesion; 
	JMenuItem nuevoAdmin; 
	JMenuItem nuevoModerador;
	JMenuItem modificar;
	
	JMenu opcionesa;
	JMenu registros;
	Imagen imagenpresentacion;

	public VentanaAdmin(String nom) {

		super("Administrador: " + nom);
		
		
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//MENU
		arri = new JMenuBar();
		cuenta = new JMenu("Cuenta");
		inicio = new JMenuItem("Inicio."); 
		cerrarSesion = new JMenuItem("Cerrar sesión."); 
		cuenta.add(inicio);
		cuenta.add(cerrarSesion);
		arri.add(cuenta);
		opcionesa = new JMenu("Procesos y Consultas");
		registros = new JMenu("Registros");
		nuevoAdmin = new JMenuItem("Nuevo Administrador.");
		nuevoModerador = new JMenuItem("Registrar un moderador.");

		modificar = new JMenuItem("Añadir/Quitar opciones de menu de un usuario");
		
		registros.add(nuevoAdmin);
		registros.add(nuevoModerador);
		opcionesa.add(registros);
		opcionesa.add(modificar);
		arri.add(cuenta);
		arri.add(opcionesa);
		setJMenuBar(arri);
		
		//CREACIONDE PANELES QUE USARE DESPUES
		panel3 = new JPanel();		
		
		//DISEÑO PANEL
		panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BorderLayout());
		
		imagenpresentacion = new Imagen();
		
		String ruta = System.getProperty("user.dir") + "\\src\\images\\";
		imagenpresentacion.setImage(ruta + "presentacion.png");
		panelPrincipal.add(imagenpresentacion, BorderLayout.CENTER);
		label1 = new JLabel("Bienvenido Administrador,", SwingConstants.CENTER);
		Font auxFont=label1.getFont(); 
		label1.setFont(new Font(auxFont.getFontName(), Font.BOLD, 18));
		label2 = new JLabel("Estas en el nivel mas alto de supremacia del sistema, Bienvenido amo " + nom, SwingConstants.CENTER);
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2,1,15, 15));
		panel1.add(label1);
		panel1.add(label2);
		panelPrincipal.add(panel1, BorderLayout.NORTH);
		panel2 = new JPanel();
		panel2.setLayout(new GridLayout(2,1,50, 15));
        boton2 = new JButton("Inicio");
        boton1 = new JButton("Iniciar Sesión");
        boton1.setActionCommand("UsuarioDatos");

		getContentPane().add(panelPrincipal);
	}

//Métodos de la interfaz InterfazVista:
	public void escribeCambio(String s) {
		// resultado.setText(s);
	}

	public double getCantidad() {
		try {
			return 0.0;// Double.parseDouble(cantidad.getText())
		} catch (NumberFormatException e) {
			return 0.0;
		}
	}

	public void setControlador(Control c) {
		inicio.addActionListener(c); 
		cerrarSesion.addActionListener(c); ; 
		nuevoAdmin.addActionListener(c); 
		nuevoModerador.addActionListener(c); 
		modificar.addActionListener(c); 
		
	}

	public void arranca() {

		pack();// coloca los componentes
		setSize(1000, 500);// Da el tamaño inicial de la ventana
		Dimension d= new Dimension(910,400);
		setMinimumSize(d);
		setLocationRelativeTo(null); // centra la ventana en la pantalla
		String ruta = System.getProperty("user.dir") + "\\src\\images\\";
		ImageIcon ImageIcono = new ImageIcon(ruta + "mibiciicon.png");
		imagen = ImageIcono.getImage();
		setIconImage(imagen);
		setVisible(true);// visualiza la ventana
	}	
	
	
	public void ingresardatos() {	
		
		panelPrincipal.removeAll();
		panel3.removeAll();
		panel2.removeAll();
		panel1.removeAll();
		
		label1 = new JLabel("Ingreso como usuario registrado y registro: ", SwingConstants.CENTER);
		Font auxFont=label1.getFont(); 
		label1.setFont(new Font(auxFont.getFontName(), Font.BOLD, 18));
		label2 = new JLabel("Ingresa los datos para ingresar al sistema como usuario, y si no estas registrado, lo puedes hacer con la función 'Registrarme en el sistema' ", SwingConstants.CENTER);
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2,1,15, 15));
		panel1.add(label1);
		panel1.add(label2);
		panelPrincipal.add(panel1, BorderLayout.NORTH);
		
		panel3.setLayout(new GridLayout(3, 1));
		label4 = new JLabel("Ingrese su código de usuario y su clave.");
		label4.setHorizontalAlignment(JLabel.CENTER);
		panel3.add(label4);
		label5 = new JLabel("        Codigo de usuario: ");
		texto2 = new JTextField(15);
		JPanel f2 = new JPanel();
		f2.setLayout(new FlowLayout(FlowLayout.CENTER));
		f2.add(label5);
		f2.add(texto2);
		panel3.add(f2);
		label6 = new JLabel("        Clave:                        ");
		texto3 = new JPasswordField("secreto",15);
	    texto3.setToolTipText("Ingrese Contraseña");
		JPanel f3 = new JPanel();
		f3.setLayout(new FlowLayout(FlowLayout.CENTER));
		f3.add(label6);
		f3.add(texto3);
		panel3.add(f3);
		panelPrincipal.add(panel3, BorderLayout.CENTER);
		
        boton1.setText("Ingresar");
        boton1.setActionCommand("login");		
        label3.setText("Registrarme en el sistema");
        panel2.setLayout(new GridLayout(2,1,50, 0));
        panel2.add(boton1);
        panel2.add(label3);

		panelPrincipal.add(panel2, BorderLayout.SOUTH);
			
		setVisible(true);
	}
	
public void Usuariocomun() {	
	
		panelPrincipal.removeAll();
		panel3.removeAll();
		panel2.removeAll();
		panel1.removeAll();
		Font auxFont=label1.getFont(); 
		label1.setFont(new Font(auxFont.getFontName(), Font.BOLD, 18));
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2,1,15, 15));
		panel1.add(label1);
		panel1.add(label2);
		panelPrincipal.add(panel1, BorderLayout.NORTH);
		
		panelPrincipal.add(imagenpresentacion, BorderLayout.CENTER);
		
        boton1.setText("Iniciar Sesión");
        boton1.setActionCommand("UsuarioDatos");
        panel2.setLayout(new GridLayout(2,1,50, 15));
        panel2.add(boton2);
        panel2.add(boton1);
		panelPrincipal.add(panel2, BorderLayout.SOUTH);
			
		setVisible(true);
	}
	
	public void checkLogIn() {
		
		
		
		
		setVisible(true);
	}

	public void cerrarventana() {
		setVisible(false);
		
	}
	public void mostrartexto(String muestra) {
		
		panelPrincipal.removeAll();
		panel3.removeAll();
		panel1.removeAll();
		panel2.removeAll();
		
		label1 = new JLabel("Bicicletas por estacion [ cantidad / capacidad maxima ]:", SwingConstants.CENTER);
		Font auxFont=label1.getFont(); 
		label1.setFont(new Font(auxFont.getFontName(), Font.BOLD, 18));
		label2 = new JLabel("Aqui miramos la cantidad de bicicletas que hay por estación", SwingConstants.CENTER);
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2,1,15, 15));
		panel1.add(label1);
		panel1.add(label2);
		panelPrincipal.add(panel1, BorderLayout.NORTH);
		
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
		texto1 = new TextArea(muestra,0 , 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
		texto1.setEditable(false);
		texto1.setBackground(Color.white);
		panel3.add(texto1);
		panelPrincipal.add(panel3, BorderLayout.CENTER);
		

			
		setVisible(true);
		
	}
	public void setPanelPrincipal(JPanel p) {
		getContentPane().removeAll();
		this.panelPrincipal=p;
		getContentPane().add(p);
		pack();
		setVisible(true);
	}
	
}