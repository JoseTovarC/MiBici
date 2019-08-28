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
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import BaseDatos.Datos;
import gestorAplicacion.User.Admin;
import gestorAplicacion.User.Persona;
import gestorAplicacion.User.Usuario;
import vista.InterfazVista;
import vista.VentanaLogin;
import vista.VentanaUsuarioInvitado;
import vista.VentanaUsuarioR;
import uiMain.MenuDeConsola;
import uiMain.OpcionDeMenu;
import uiMain.cant_bicicletas_esta;

public class ControlVentanaUsuarioInvitado implements Control {

	String ruta = System.getProperty("user.dir") + "\\src\\images\\";
	ImageIcon ImageIconoerror = new ImageIcon(ruta + "iconfinder_Error_381599.png");
	ImageIcon ImageIconoerrorsign = new ImageIcon(ruta + "iconfinder_sign-error_299045.png");
	ImageIcon ImageIconowarning = new ImageIcon(ruta + "iconfinder_101_Warning_183416.png");
	ImageIcon ImageIconocheck = new ImageIcon(ruta + "iconfinder_sign-check_299110.png");
	ImageIcon ImageIconohelp = new ImageIcon(ruta + "iconfinder_Help_1493288.png");
	private InterfazVista vista;

	public ControlVentanaUsuarioInvitado(InterfazVista vista) {
		this.vista = vista;

	}

	public void actionPerformed(ActionEvent evento) {

		/*if (evento.getActionCommand().equals("login")) {
			// JOptionPane.showMessageDialog(null, "Loque sea", "InfoBox: " + " Funciona
			// perro", JOptionPane.INFORMATION_MESSAGE);

			try {
				if (((VentanaUsuarioInvitado) vista).texto2.getText().isEmpty()
						|| ((VentanaUsuarioInvitado) vista).texto3.getText().isEmpty()) {

					JOptionPane.showMessageDialog(null, "Has dejado las casillas de usuario o contraseña vacias.",
							"Casillas Vacias", JOptionPane.ERROR_MESSAGE, ImageIconowarning);
					// Aqui es donde se debe hacer el Login de nuestro modelo
				} else {
					long ide = (Integer.parseInt(((VentanaUsuarioInvitado) vista).texto2.getText()));
					String r = Persona.login(ide, ((VentanaUsuarioInvitado) vista).texto3.getText());

					if (r.equals("Usuario no encontrado") || (Persona.currentUser instanceof Admin)) {
						JOptionPane.showMessageDialog(null, "Usuario no encontrado", "login fail",
								JOptionPane.WARNING_MESSAGE, ImageIconoerrorsign);
						Persona.currentUser = null;
					} else {

						InterfazVista vistaur = new VentanaUsuarioR(r);
						// y el control:
						Control controlU = new ControlVentanaR(vistaur);
						// configura la vista
						vistaur.setControlador(controlU);
						// y arranca la interfaz (vista):
						((VentanaUsuarioInvitado) vista).cerrarventana();

						JMenuBar menuUsuario = new JMenuBar();
						JMenu cuenta = new JMenu("Cuenta");
						JMenuItem inicio = new JMenuItem("Inicio");
						inicio.addActionListener(controlU);
						JMenuItem cerrarSesion = new JMenuItem("Cerrar sesión");
						cerrarSesion.addActionListener(controlU);
						cuenta.add(inicio);
						cuenta.add(cerrarSesion);
						menuUsuario.add(cuenta);
						JMenu opcionesm = new JMenu("Opciones");
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
						menuUsuario.add(opcionesm);
						((VentanaUsuarioR) vistaur).setJMenuBar(menuUsuario);
						((VentanaUsuarioR) vistaur).arranca();
					}
				}
			} catch (NumberFormatException e) {

				JOptionPane.showMessageDialog(null, "El usuario debe ser un número de Documento",
						"tipo de dato incorecto", JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			} catch (Exception e) {

			}

		} else */if (evento.getActionCommand().equals("Usuario Comun")) {
			((VentanaUsuarioInvitado) vista).Usuariocomun();

		} else if (evento.getActionCommand().equals("Volver a inicio.")) {
			// la vista:
			InterfazVista vistainicio = new VentanaLogin();
			// y el control:
			Control controlinicio = new ControlPFicticio(vistainicio);
			// configura la vista
			vistainicio.setControlador(controlinicio);
			// y arranca la interfaz (vista):
			((VentanaUsuarioInvitado) vista).cerrarventana();
			vistainicio.arranca();

		} else if (evento.getActionCommand().equals("Bicicletas por estacion")) {
			((VentanaUsuarioInvitado) vista).mostrartexto(cant_bicicletas_esta.getEstacioness());

		} else if (evento.getActionCommand().equals("crearUsuario")) {
			try {
				if (((VentanaUsuarioInvitado) vista).texto2.getText().isEmpty()
						|| ((VentanaUsuarioInvitado) vista).texto4.getText().isEmpty()
						|| ((VentanaUsuarioInvitado) vista).texto3.getText().isEmpty()
						|| ((VentanaUsuarioInvitado) vista).texto5.getText().isEmpty()
						|| ((VentanaUsuarioInvitado) vista).texto6.getText().isEmpty()) {
					// error dato vacio
					JOptionPane.showMessageDialog(null, "Has dejado las casillas de usuario o contraseña vacias.",
							"Registro Usuario", JOptionPane.ERROR_MESSAGE, ImageIconowarning);
				} else if ((Integer.parseInt(((VentanaUsuarioInvitado) vista).texto5.getText())) < 18) {
					JOptionPane.showMessageDialog(null, "Debe ser mayor de edad.", "Registro Usuario",
							JOptionPane.ERROR_MESSAGE, ImageIconowarning);
				} else if (!((VentanaUsuarioInvitado) vista).texto6.getText().equals("F")
						&& !((VentanaUsuarioInvitado) vista).texto6.getText().equals("M")) {
					JOptionPane.showMessageDialog(null, "Ingrese un género válido (M/F).", "Registro Usuario",
							JOptionPane.ERROR_MESSAGE, ImageIconowarning);
				} else {
					long id = (Integer.parseInt(((VentanaUsuarioInvitado) vista).texto4.getText()));
					if (Usuario.getUsuarioPorUsername(id) == null) {
						ArrayList<OpcionDeMenu> userOptions = new ArrayList<OpcionDeMenu>() {
							{
								add(BaseDatos.Datos.operations.get("5"));
								add(BaseDatos.Datos.operations.get("9"));
								add(BaseDatos.Datos.operations.get("10"));
								add(BaseDatos.Datos.operations.get("11"));
								add(BaseDatos.Datos.operations.get("12"));
							}
						};
						String nombre = ((VentanaUsuarioInvitado) vista).texto2.getText();
						String edad = ((VentanaUsuarioInvitado) vista).texto5.getText();
						String genero = ((VentanaUsuarioInvitado) vista).texto6.getText();
						String contra = ((VentanaUsuarioInvitado) vista).texto3.getText();
						String ide = ((VentanaUsuarioInvitado) vista).texto4.getText();

						MenuDeConsola userMenu = new MenuDeConsola(userOptions);

						new Usuario(nombre, edad, ide, genero, contra, "0");
						Usuario.getUsuarioPorUsername(id).setMenu(userMenu);
						JOptionPane.showMessageDialog(null, "Registro realizado exitosamente", "Registro Usuario",
								JOptionPane.OK_OPTION, ImageIconocheck);
						((VentanaUsuarioInvitado) vista).Usuariocomun();
						Datos.guardarDatos();
					} else {
						JOptionPane.showMessageDialog(null, "No se pudo realizar el registro, el usuario ya existe",
								"Registro Usuario", JOptionPane.ERROR_MESSAGE, ImageIconoerror);
					}
				}
			} catch (NumberFormatException e) {

				JOptionPane.showMessageDialog(null, "Ha ingresado un tipo de dato erróneo", "Tipo de dato incorecto",
						JOptionPane.ERROR_MESSAGE, ImageIconowarning);
			} catch (Exception e) {

			}
		}else if (evento.getActionCommand().equals("Ayuda")) {
			JOptionPane.showMessageDialog(null, "Ayuda Usuario invitado", "Ayuda.",
					JOptionPane.INFORMATION_MESSAGE, ImageIconohelp);
		}
		
		
		else
			vista.escribeCambio("ERROR");
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		((VentanaUsuarioInvitado) vista).cambiarazul();

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		((VentanaUsuarioInvitado) vista).cambiarnegro();

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		((VentanaUsuarioInvitado) vista).registro();
	}

}