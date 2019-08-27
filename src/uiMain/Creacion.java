package uiMain;

import java.util.ArrayList;

import gestorAplicacion.User.Admin;

public class Creacion extends OpcionDeMenu {
	
	
	public ArrayList<OpcionDeMenu> Options;
	public Creacion(String key) {
		super(key);
		Options = new ArrayList<OpcionDeMenu>() {
			{
				add(BaseDatos.Datos.operations.get("36"));
				add(BaseDatos.Datos.operations.get("35"));
				add(BaseDatos.Datos.operations.get("34"));
			}
		};
	}
	@Override
	public void ejecutar() {
		ArrayList<OpcionDeMenu> Options = new ArrayList<OpcionDeMenu>();
		
		Options.add(BaseDatos.Datos.operations.get("36"));
		Options.add(BaseDatos.Datos.operations.get("35"));
		Options.add(BaseDatos.Datos.operations.get("34"));
		
		MenuDeConsola funcionsMenu = new MenuDeConsola(Options);

		funcionsMenu.lanzarMenu();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Creación";
	}

}
