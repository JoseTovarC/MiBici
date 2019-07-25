package uiMain;

import java.util.*;

import BaseDatos.*;
import gestorAplicacion.User.*;
import gestorAplicacion.Work.Multa;

public class Multar extends OpcionDeMenu {
	public Multar(String key) {
		super(key);
	}

	@Override
	public void ejecutar() {
		Scanner ent=new Scanner(System.in);
		System.out.println("Por favor ingrese el id del usuario que va a multar:");
		Usuario a = BaseDatos.Datos.hashUsuario.get(ent.nextLong());
		if (a!=null) {
			System.out.println("Ingrese el id de la multa (0 para cancelar):");
			byte i= ent.nextByte();
			Multa multa=Datos.hashMulta.get(i);
			
			while (multa==null) {
				if (i==0) {
					System.out.println("Se ha cancelado la multa.");
					return;
				}
				System.out.println("Id invalido.");
				System.out.println("Ingrese el id de la multa (0 para cancelar):");
				i= ent.nextByte();
				multa= Datos.hashMulta.get(i);
			}
			a.addMulta(multa);
			System.out.println("Se ha multado al usuario "+a.getNombre()+", por "+multa.getDescripcion());
		}
		else {
			System.out.println("Usuario no encontrado.");
		}
	}

	@Override
	public String toString() {
		return "Multar";
	}

}
