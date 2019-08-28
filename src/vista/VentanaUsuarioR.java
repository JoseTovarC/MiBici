package vista;

import javax.swing.*;
import javax.swing.event.MenuListener;

import control.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;

import images.Imagen;


public class VentanaUsuarioR extends JFrame implements InterfazVista {
	Container panelventana;
	Control control;
	// Definicion de variables/componentes
	public JPanel panelPrincipal;// panel principal = T O D O
	JPanel panel1; // Panel Norte, contiene el label1
	JPanel panel2; // Panel Sur, alias botonera.
	JPanel panel3; // Panel centro, ingresar datos.
	
	public JLabel label1; // Bienvenida del sistema al sistema
	public JLabel label2; // Mensaje de ayuda
	public JLabel label3; // Boton para el registro
	public JLabel label4; // Ingrese su c�digo de usuario y su clave.
	public JLabel label5; //Codigo de usuario
	public JLabel label6; // Clave
	public JLabel label7; // Bienvenida del sistema a la opcion del sistema
	public JLabel label8; //  Mensaje de ayuda
	
	private JButton boton1;// boton 1 = �Login Usuario�
	public JButton boton2; // boton 2 = �Inicio�
	
	TextArea texto1; // texto 1 = guia del sistema
	public JTextField texto2; // texto 2 = codigo de usuario
	public JPasswordField texto3; // texto 3 = clave del usuario

	Image imagen;
	Imagen imagenpresentacion;

	public VentanaUsuarioR(String tipo, long id) {

		super(tipo);
		
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
		
		//CREACIONDE PANELES QUE USARE DESPUES
		panel3 = new JPanel();		
		
		//DISE�O PANEL
		panelPrincipal = new JPanel();
		panelPrincipal.setLayout(new BorderLayout());
		
		imagenpresentacion = new Imagen();
		
		String ruta = System.getProperty("user.dir") + "\\src\\images\\";
		imagenpresentacion.setImage(ruta + "presentacion.png");
		panelPrincipal.add(imagenpresentacion, BorderLayout.CENTER);
		label1 = new JLabel("Bienvenido,", SwingConstants.CENTER);
		Font auxFont=label1.getFont(); 
		label1.setFont(new Font(auxFont.getFontName(), Font.BOLD, 18));
		label2 = new JLabel("Hola esta es la ventana del " + tipo+". ID: "+id, SwingConstants.CENTER);
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2,1,15, 15));
		panel1.add(label1);
		panel1.add(label2);
		panelPrincipal.add(panel1, BorderLayout.NORTH);
		panel2 = new JPanel();
		panel2.setLayout(new GridLayout(2,1,50, 15));
        boton2 = new JButton("Inicio");
        boton1 = new JButton("Iniciar Sesi�n");
        boton1.setActionCommand("UsuarioDatos");		
        label3 = new JLabel("Registrarme en el sistema", SwingConstants.CENTER);
       
        getContentPane().add(panelPrincipal);
	}
	public void setPanelPrincipal(JPanel p) {
		getContentPane().removeAll();
		this.panelPrincipal=p;
		getContentPane().add(p);
		pack();
		setVisible(true);
	}

//M�todos de la interfaz InterfazVista:
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
		boton1.addActionListener(c);
		boton2.addActionListener(c);
		label3.addMouseListener(c);
			
	}

	public void arranca() {

		pack();// coloca los componentes
		setSize(1000, 500);// Da el tama�o inicial de la ventana
		Dimension d= new Dimension(910,400);
		setMinimumSize(d);
		setLocationRelativeTo(null); // centra la ventana en la pantalla
		String ruta = System.getProperty("user.dir") + "\\src\\images\\";
		ImageIcon ImageIcono = new ImageIcon(ruta + "mibiciicon.png");
		imagen = ImageIcono.getImage();
		setIconImage(imagen);
		setVisible(true);// visualiza la ventana
	}	
	
	public void cambiarazul() {	
		label3.setForeground(Color.BLUE);
		Font font = label3.getFont();
	    Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
	    attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
	    label3.setFont(font.deriveFont(attributes));
		setVisible(true);
			
	}
	
	public void cambiarnegro() {	
		label3.setForeground(Color.BLACK);
		Font font = label3.getFont();
	    Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
	    attributes.put(TextAttribute.UNDERLINE, -1);
        label3.setFont(font.deriveFont(attributes));
		setVisible(true);

			
	}
	public void ingresardatos() {	
		
		panelPrincipal.removeAll();
		panel3.removeAll();
		panel2.removeAll();
		panel1.removeAll();
		
		label1 = new JLabel("Ingreso como usuario registrado y registro: ", SwingConstants.CENTER);
		Font auxFont=label1.getFont(); 
		label1.setFont(new Font(auxFont.getFontName(), Font.BOLD, 18));
		label2 = new JLabel("Ingresa los datos para ingresar al sistema como usuario, y si no estas registrado, lo puedes hacer con la funci�n 'Registrarme en el sistema' ", SwingConstants.CENTER);
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2,1,15, 15));
		panel1.add(label1);
		panel1.add(label2);
		panelPrincipal.add(panel1, BorderLayout.NORTH);
		
		panel3.setLayout(new GridLayout(3, 1));
		label4 = new JLabel("Ingrese su c�digo de usuario y su clave.");
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
	    texto3.setToolTipText("Ingrese Contrase�a");
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
		
		pack();
		setVisible(true);
	}
	
public void inicio() {	
		
		panelPrincipal.removeAll();
		panel3.removeAll();
		panel2.removeAll();
		panel1.removeAll();
		
		imagenpresentacion = new Imagen();
		
		String ruta = System.getProperty("user.dir") + "\\src\\images\\";
		imagenpresentacion.setImage(ruta + "presentacion.png");
		panelPrincipal.add(imagenpresentacion, BorderLayout.CENTER);
		Font auxFont=label1.getFont(); 
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2,1,15, 15));
		panel1.add(label1);
		panel1.add(label2);
		panelPrincipal.add(panel1, BorderLayout.NORTH);
		
		panelPrincipal.add(imagenpresentacion, BorderLayout.CENTER);
			
		pack();
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
		
		label7 = new JLabel("Bicicletas por estacion [ cantidad / capacidad maxima ]:", SwingConstants.CENTER);
		Font auxFont=label7.getFont(); 
		label7.setFont(new Font(auxFont.getFontName(), Font.BOLD, 18));
		label8 = new JLabel("Aqui miramos la cantidad de bicicletas que hay por estaci�n", SwingConstants.CENTER);
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2,1,15, 15));
		panel1.add(label7);
		panel1.add(label8);
		panelPrincipal.add(panel1, BorderLayout.NORTH);
		
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
		texto1 = new TextArea(muestra,0 , 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
		texto1.setEditable(false);
		texto1.setBackground(Color.white);
		panel3.add(texto1);
		panelPrincipal.add(panel3, BorderLayout.CENTER);
		
		pack();
		setVisible(true);
	}
	
	public void mostrarmultas(String muestra) {
		
		panelPrincipal.removeAll();
		panel3.removeAll();
		panel1.removeAll();
		panel2.removeAll();
		
		label7 = new JLabel("Multas del Usuario:", SwingConstants.CENTER);
		Font auxFont=label7.getFont(); 
		label7.setFont(new Font(auxFont.getFontName(), Font.BOLD, 18));
		label8 = new JLabel("Aqui puede ver una descripcion detallada de sus multas y la suma de la deuda", SwingConstants.CENTER);
		panel1 = new JPanel();
		panel1.setLayout(new GridLayout(2,1,15, 15));
		panel1.add(label7);
		panel1.add(label8);
		panelPrincipal.add(panel1, BorderLayout.NORTH);
		
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 15));
		texto1 = new TextArea(muestra,0 , 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
		texto1.setEditable(false);
		texto1.setBackground(Color.white);
		panel3.add(texto1);
		panelPrincipal.add(panel3, BorderLayout.CENTER);
		
		pack();
		setVisible(true);
	}
}