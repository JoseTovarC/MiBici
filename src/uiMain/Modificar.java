package uiMain;

import java.util.ArrayList;

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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Modificar Permisos";
	}

}
