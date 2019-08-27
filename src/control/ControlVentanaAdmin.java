package control;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import BaseDatos.Datos;
import gestorAplicacion.User.Admin;
import gestorAplicacion.User.Moderador;
import gestorAplicacion.User.Persona;
import gestorAplicacion.User.Usuario;
import uiMain.Modificar;
import uiMain.Multar;
import vista.InterfazVista;
import vista.PanelModificar;
import vista.PanelMultar;
import vista.PanelOpciones;
import vista.PanelPerfilTarjeta;
import vista.VentanaAdmin;
import vista.VentanaUsuarioR;

public class ControlVentanaAdmin implements Control {

	private InterfazVista vista;
	String ruta = System.getProperty("user.dir") + "\\src\\images\\";
	ImageIcon ImageIconoerror = new ImageIcon(ruta + "iconfinder_Error_381599.png");
	ImageIcon ImageIconoerrorsign = new ImageIcon(ruta + "iconfinder_sign-error_299045.png");
	ImageIcon ImageIconowarning = new ImageIcon(ruta + "iconfinder_101_Warning_183416.png");
	ImageIcon ImageIconocheck = new ImageIcon(ruta + "iconfinder_sign-check_299110.png");

	// private ConversorEurosPesos modelo;
	public ControlVentanaAdmin(InterfazVista vista) {
		this.vista = vista;	
		

	}

	public void actionPerformed(ActionEvent evento) {

		
		if (evento.getActionCommand().equals("UsuarioDatos")) {
			//((VentanaAdmin) vista).ingresardatos();

		}else if (evento.getActionCommand().equals("Añadir/Quitar opciones de menu de un usuario")) {
			((VentanaAdmin) vista).panelPrincipal.removeAll();
			JPanel j = new PanelModificar(Modificar.getUsuarios());
			((VentanaAdmin) vista).setPanelPrincipal(j);
			((PanelModificar) j).setControlador(new ControlVentanaAdmin(vista));
		
		}else if (evento.getActionCommand().equals("Escogerusuario")) {
			try {
				String u = ((PanelModificar) ((VentanaAdmin) vista).panelPrincipal).texto2.getText();
				long id = (long) Integer.parseInt(u);
				if (Persona.getUsuarioPorUsername(id) == null) {
					JOptionPane.showMessageDialog(null, "Usuario no encontrado.", "Modificar.", JOptionPane.ERROR_MESSAGE,
							ImageIconowarning);

				} else if ((Persona.getUsuarioPorUsername(id) instanceof Admin)) {
					JOptionPane.showMessageDialog(null,
							"El documento pertenece a otro tipo de usuario, el cual no se le puede modificar las opciones", "Modificar.",
							JOptionPane.ERROR_MESSAGE, ImageIconowarning);

				} else {

					((VentanaAdmin) vista).panelPrincipal.removeAll();
					JPanel j = new PanelOpciones();
					/*//Parte que podemos pasar a controlador
					JPanel panel2 = new JPanel();
					panel2.setLayout(new GridLayout(, 2, 15));
					
					
					texto1 = new TextArea(r, 0, 0, TextArea.SCROLLBARS_VERTICAL_ONLY);
					texto1.setEditable(false);
					texto1.setBackground(Color.white);
					panel2.add(texto1);
					this.add(panel2, BorderLayout.CENTER);*/
					((VentanaAdmin) vista).setPanelPrincipal(j);
					
				}

			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Se ha ingresado un dato incorrecto", "Tipo de dato incorecto",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			} catch (IndexOutOfBoundsException e) {
				JOptionPane.showMessageDialog(null, "Ingresa un numero valido", "tipo de dato incorecto",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "No se han creado Bicicleta", "Creacion Bicicleta.",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			}
		}
		
		
		
		
		
		
		else
			vista.escribeCambio("ERROR");
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}


}
