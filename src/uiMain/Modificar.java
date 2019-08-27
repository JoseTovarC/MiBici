package uiMain;

import java.util.ArrayList;
import java.util.Map;

import gestorAplicacion.User.Admin;
import gestorAplicacion.User.Persona;

public class Modificar extends OpcionDeMenu {
	
	
	public Modificar(String key) {
		super(key);
	}

	@Override
	public void ejecutar() {
		ArrayList<OpcionDeMenu> consultOptions = new ArrayList<OpcionDeMenu>() {
			{
				add(BaseDatos.Datos.operations.get("33"));
				add(BaseDatos.Datos.operations.get("34"));
				add(BaseDatos.Datos.operations.get("35"));
			}
		};

		MenuDeConsola ConsultMenu = new MenuDeConsola(consultOptions);

		ConsultMenu.lanzarMenu();

	}
	public static String getUsuarios() {
		String r;
		r=new String();
		if(BaseDatos.Datos.hashUsuario.isEmpty()){
			r= new String("No hay usuarios registados.");
			return r;
		}
		
		for (Map.Entry<Long, Persona> entry : BaseDatos.Datos.hashPersona.entrySet()) {
			if(!(entry.getValue() instanceof Admin)) {
				r += "* "+". Documento: "+entry.getValue().getId() + ", Nombre: "+ entry.getValue().getNombre()+".\n";
				
			}
					
		}
		
		return r;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Modificar Permisos";
	}

}
