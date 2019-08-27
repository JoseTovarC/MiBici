package uiMain;

import java.util.ArrayList;

public class CreacionA extends OpcionDeMenu {

	public ArrayList<OpcionDeMenu> Options;
	public CreacionA(String key) {
		super(key);
		Options = new ArrayList<OpcionDeMenu>() {
			{
				add(BaseDatos.Datos.operations.get("30"));
				add(BaseDatos.Datos.operations.get("31"));
			}
		};
	}
	
	@Override
	public void ejecutar() {
		ArrayList<OpcionDeMenu> Options = new ArrayList<OpcionDeMenu>() {
			{
				add(BaseDatos.Datos.operations.get("30"));
				add(BaseDatos.Datos.operations.get("31"));
			}
		};

		MenuDeConsola ConsultMenu = new MenuDeConsola(Options);

		ConsultMenu.lanzarMenu();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Creación";
	}

}
