package uiMain;

import java.util.*;

public class ConsultasB extends OpcionDeMenu {
	public ConsultasB(String key) {
		super(key);
	}
	@Override
	public void ejecutar() {
		Scanner ent= new Scanner(System.in);
		System.out.println("Elija la opcion que quiera consultar: ");
		System.out.println(" 1. Numero de Usuarios. \n 2. Numero de Moderadores. \n 3. Numero de Bicicletas \n 4. Numero de Estaciones \n 5. Numero de Distribuidores. \n 6. Salir.");
		byte o= ent.nextByte();
		if (o==1) {
			System.out.println("Numero de Usuarios: "+BaseDatos.Datos.hashUsuario.size());
		}
		else if(o==2) {
			System.out.println("Numero de Moderadores: "+BaseDatos.Datos.hashModerador.size());
		}
		else if(o==3) {
			System.out.println("Numero de Bicicletas: "+BaseDatos.Datos.hashBicicleta.size());
		}
		else if(o==4) {
			System.out.println("Numero de Estaciones: "+BaseDatos.Datos.hashEstacion.size());
		}
		else if(o==5) {
			System.out.println("Numero de Distribuidores: "+BaseDatos.Datos.hashDistribuidor.size());
		}
		else {
			return;
		}
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Consultas especiales";
	}

}
