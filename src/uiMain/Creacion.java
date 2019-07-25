package uiMain;

import java.util.ArrayList;

import gestorAplicacion.User.Admin;

public class Creacion extends OpcionDeMenu {
	public Creacion(String key) {
		super(key);
	}
	@Override
	public void ejecutar() {
		ArrayList<OpcionDeMenu> crearOptions = new ArrayList<OpcionDeMenu>();
		if (Main.user instanceof Admin) {
			crearOptions.add(BaseDatos.Datos.operations.get("30"));
			
		}
		crearOptions.add(BaseDatos.Datos.operations.get("36"));
		crearOptions.add(BaseDatos.Datos.operations.get("35"));
		crearOptions.add(BaseDatos.Datos.operations.get("34"));
		
		MenuDeConsola funcionsMenu = new MenuDeConsola(crearOptions);

		funcionsMenu.lanzarMenu();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Creación";
	}

}
