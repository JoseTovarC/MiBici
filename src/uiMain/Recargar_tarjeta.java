package uiMain;

import java.util.Scanner;

import BaseDatos.Datos;
import gestorAplicacion.User.Usuario;

public class Recargar_tarjeta extends OpcionDeMenu {

	public Recargar_tarjeta(String key) {
		super(key);
	}
	
	@Override
	public void ejecutar() {
		Scanner esc = new Scanner(System.in);
		while(true){
			System.out.print("Ingrese el valor con el que desea recargar a tarjeta (Recuerde que no se pueden hacer recargas superiores a $300000): ");
			int valor = esc.nextInt();
			if(valor>0 && valor<=300000){
				Usuario aux = (Usuario) Datos.hashPersona.get(Main.user.getId());				
				System.out.println(aux.recargarT(valor));
				break;
				
			}else if(valor==0){
				System.out.println("Es una perdida de iempo recargar $0 a tu tarjeta");
				break;
			}else{
				System.out.println("Ingrese un valor valido");
			}
		}
	}

	@Override
	public String toString() {
		return "Recargar Tarjeta";
	}

}
