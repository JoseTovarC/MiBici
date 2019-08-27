package uiMain;

import java.util.ArrayList;

public class Funcionalidades extends OpcionDeMenu {
	
	public Funcionalidades(String key) {
		super(key);
		Options = new ArrayList<OpcionDeMenu>() {
			{
				add(BaseDatos.Datos.operations.get("19"));
				add(BaseDatos.Datos.operations.get("20"));
				add(BaseDatos.Datos.operations.get("21"));
				add(BaseDatos.Datos.operations.get("22"));
				add(BaseDatos.Datos.operations.get("23"));
				add(BaseDatos.Datos.operations.get("24"));
				add(BaseDatos.Datos.operations.get("25"));
				add(BaseDatos.Datos.operations.get("26"));
				add(BaseDatos.Datos.operations.get("27"));
				add(BaseDatos.Datos.operations.get("28"));
			}
		};
	}

	@Override
	public void ejecutar() {
		ArrayList<OpcionDeMenu> Options = new ArrayList<OpcionDeMenu>() {
			{
				add(BaseDatos.Datos.operations.get("19"));
				add(BaseDatos.Datos.operations.get("20"));
				add(BaseDatos.Datos.operations.get("21"));
				add(BaseDatos.Datos.operations.get("22"));
				add(BaseDatos.Datos.operations.get("23"));
				add(BaseDatos.Datos.operations.get("24"));
				add(BaseDatos.Datos.operations.get("25"));
				add(BaseDatos.Datos.operations.get("26"));
				add(BaseDatos.Datos.operations.get("27"));
				add(BaseDatos.Datos.operations.get("28"));
			}
		};

		MenuDeConsola funcionsMenu = new MenuDeConsola(Options);

		funcionsMenu.lanzarMenu();
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Funcionalidades";
	}

}
