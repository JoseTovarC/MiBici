package gestorAplicacion.User;


import java.util.ArrayList;

import BaseDatos.Datos;
import uiMain.*;


public class UsuarioInvitado extends Usuario {
	
	public UsuarioInvitado(MenuDeConsola menu) {
		super("", (byte) 0 , (long) 0 , "", "", 0, menu);
		BaseDatos.Datos.hashPersona.put((long) -1, this);
	}
	
	public static UsuarioInvitado nuevoUsuarioInvitado() {
		ArrayList<OpcionDeMenu> OpcionesInvitado = new ArrayList<OpcionDeMenu>(){
			{
				add(Datos.operations.get("1"));
				add(Datos.operations.get("2"));
			}
		};
		
		MenuDeConsola InvitadoMenu = new MenuDeConsola(OpcionesInvitado);
		return new UsuarioInvitado(InvitadoMenu);
	}
	
}