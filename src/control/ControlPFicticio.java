package control;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import BaseDatos.Datos;
import gestorAplicacion.User.Admin;
import gestorAplicacion.User.Persona;
import gestorAplicacion.User.Usuario;
import uiMain.OpcionDeMenu;
import vista.InterfazVista;
import vista.VentanaAdmin;
import vista.VentanaLogin;
import vista.VentanaUsuarioInvitado;
import vista.VentanaUsuarioR;

public class ControlPFicticio implements Control {

	private InterfazVista vista;
	String ruta = System.getProperty("user.dir") + "\\src\\images\\";
	ImageIcon ImageIconoerror = new ImageIcon(ruta + "iconfinder_Error_381599.png");
	ImageIcon ImageIconoerrorsign = new ImageIcon(ruta + "iconfinder_sign-error_299045.png");
	ImageIcon ImageIconowarning = new ImageIcon(ruta + "iconfinder_101_Warning_183416.png");
	ImageIcon ImageIconocheck = new ImageIcon(ruta + "iconfinder_sign-check_299110.png");
	private Queue<String> colaImagenes = new LinkedList();

	// private ConversorEurosPesos modelo;
	public ControlPFicticio(InterfazVista vista) {
		this.vista = vista;
		// ImageIcon ImageIcono3 = new ImageIcon(ruta + "relleno2.jpeg");
		colaImagenes.add(ruta + "relleno.jpeg");
		colaImagenes.add(ruta + "relleno2.jpeg");
		colaImagenes.add(null);

	}

	public void actionPerformed(ActionEvent evento) {
		double cantidad = vista.getCantidad();

		if (evento.getActionCommand().equals("AdminDatos")) {
			((VentanaLogin) vista).ingresardatosAdmin();

		} else if (evento.getActionCommand().equals("Ingresar")) {
			// JOptionPane.showMessageDialog(null, "Loque sea", "InfoBox: " + " Funciona
			// perro", JOptionPane.INFORMATION_MESSAGE);

			try {
				if (((VentanaLogin) vista).texto2.getText().isEmpty()
						|| ((VentanaLogin) vista).texto3.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Has dejado las casillas de usuario o contraseña vacias.",
							"Casillas Vacias", JOptionPane.ERROR_MESSAGE, ImageIconoerror);
					// Aqui es donde se debe hacer el Login de nuestro modelo
					((VentanaLogin) vista).checkLogIn();
				} else {
					long ide = (Integer.parseInt(((VentanaLogin) vista).texto2.getText()));
					String r = Persona.login(ide, ((VentanaLogin) vista).texto3.getText());

					if ((!r.equals("Usuario no encontrado")) && (Persona.currentUser instanceof Admin)) {
						r = Persona.currentUser.getNombre();
						InterfazVista vistaA = new VentanaAdmin(r);
						// y el control:
						Control controlA = new ControlVentanaAdmin(vistaA);
						// configura la vista
						vistaA.setControlador(controlA);
						// y arranca la interfaz (vista):
						((VentanaLogin) vista).cerrarventana();
						((VentanaAdmin) vistaA).arranca();

					} else if ((Persona.currentUser instanceof Usuario)) {
						JOptionPane.showMessageDialog(null, "Usuario no Administrador", "Login fail",
								JOptionPane.WARNING_MESSAGE, ImageIconoerrorsign);
					} else {
						JOptionPane.showMessageDialog(null, "Usuario no encontrado", "login fail",
								JOptionPane.WARNING_MESSAGE, ImageIconoerrorsign);
						Persona.currentUser = null;
					}
				}
			} catch (NumberFormatException e) {

				JOptionPane.showMessageDialog(null, "El usuario debe ser un número de Documento",
						"tipo de dato incorecto", JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			} catch (Exception e) {

			}

		} else if (evento.getActionCommand().equals("Cambiar foto")) {
			String fondo = colaImagenes.poll();
			((VentanaLogin) vista).cambiarFondoBoton(fondo);
			colaImagenes.add(fondo);
		} else if (evento.getActionCommand().equals("Usuario Comun")) {
			((VentanaLogin) vista).ingresardatosUser();

			/*
			 * InterfazVista vistaInvitado = new VentanaUsuarioInvitado(); // y el control:
			 * Control controlV = new ControlVentanaUsuarioInvitado(vistaInvitado); //
			 * configura la vista vistaInvitado.setControlador(controlV); // y arranca la
			 * interfaz (vista): ((VentanaLogin) vista).cerrarventana();
			 * vistaInvitado.arranca();
			 */
		} else if (evento.getActionCommand().equals("IngresarUsuario")) {
			// JOptionPane.showMessageDialog(null, "Loque sea", "InfoBox: " + " Funciona
			// perro", JOptionPane.INFORMATION_MESSAGE);

			try {
				if (((VentanaLogin) vista).texto2.getText().isEmpty()
						|| ((VentanaLogin) vista).texto3.getText().isEmpty()) {

					JOptionPane.showMessageDialog(null, "Has dejado las casillas de usuario o contraseña vacias.",
							"Casillas Vacias", JOptionPane.ERROR_MESSAGE, ImageIconowarning);
					((VentanaLogin) vista).checkLogIn();
					// Aqui es donde se debe hacer el Login de nuestro modelo
				} else {
					long ide = (Integer.parseInt(((VentanaLogin) vista).texto2.getText()));
					String r = Persona.login(ide, ((VentanaLogin) vista).texto3.getText());

					if (r.equals("Usuario no encontrado")) {
						JOptionPane.showMessageDialog(null, "Usuario no encontrado", "Login fail",
								JOptionPane.WARNING_MESSAGE, ImageIconoerrorsign);
						Persona.currentUser = null;
					} else if ((Persona.currentUser instanceof Admin)) {
						JOptionPane.showMessageDialog(null, "Usuario Administrador", "Login fail",
								JOptionPane.WARNING_MESSAGE, ImageIconoerrorsign);
					} else {

						InterfazVista vistaur = new VentanaUsuarioR(r);
						// y el control:
						Control controlU = new ControlVentanaR(vistaur);
						// configura la vista
						vistaur.setControlador(controlU);
						// y arranca la interfaz (vista):
						((VentanaLogin) vista).cerrarventana();

						JMenuBar menuUsuario = new JMenuBar();
						JMenu cuenta = new JMenu("Cuenta");
						JMenuItem inicio = new JMenuItem("Inicio");
						inicio.addActionListener(controlU);
						JMenuItem cerrarSesion = new JMenuItem("Cerrar sesión");
						cerrarSesion.addActionListener(controlU);
						cuenta.add(inicio);
						cuenta.add(cerrarSesion);
						menuUsuario.add(cuenta);
						JMenu opcionesm = new JMenu("Procesos y Consultas");
						for (OpcionDeMenu option : Persona.currentUser.getMenu().options) {

							if (option.Options != null) {
								JMenu op = new JMenu(option.toString());
								for (OpcionDeMenu suboption : option.Options) {
									JMenuItem subop = new JMenuItem(suboption.toString());
									subop.addActionListener(controlU);
									op.add(subop);
									opcionesm.add(op);
								}
							} else {
								JMenuItem op = new JMenuItem(option.toString());
								op.addActionListener(controlU);
								opcionesm.add(op);
							}

						}
						JMenuItem help = new JMenuItem("Ayuda");
					    help.addActionListener(controlU);
						menuUsuario.add(opcionesm);
						menuUsuario.add(help);
						((VentanaUsuarioR) vistaur).setJMenuBar(menuUsuario);
						((VentanaUsuarioR) vistaur).arranca();
					}
				}
			} catch (NumberFormatException e) {

				JOptionPane.showMessageDialog(null, "El usuario debe ser un número de Documento",
						"tipo de dato incorecto", JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error al ingresar usuario", "Inicio de sesión",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			}

		} else if (evento.getActionCommand().equals("Salir.")) {
			
			Object [] textoDeOpciones = {"Si", "No"};
			int opcion = JOptionPane.showOptionDialog(null, "¿Está seguro que desea salir del programa?", "Salir del programa", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, ImageIconocheck, textoDeOpciones, null);
			if(opcion == 0) {
				Datos.guardarDatos();
				System.exit(0);
			}
		} else if (evento.getActionCommand().equals("Invitado")) {
			InterfazVista vistaInvitado = new VentanaUsuarioInvitado();
			// y el control:
			Control controlV = new ControlVentanaUsuarioInvitado(vistaInvitado);
			// configura la vista
			vistaInvitado.setControlador(controlV);
			// y arranca la interfaz (vista):
			((VentanaLogin) vista).cerrarventana();
			vistaInvitado.arranca();
		} else
			vista.escribeCambio("ERROR");
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		((VentanaLogin) vista).cambiarazul();

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		((VentanaLogin) vista).cambiarnegro();

	}

	@Override
	public void mousePressed(MouseEvent arg0) {

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}