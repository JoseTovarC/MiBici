package User;

import Bike.*;

public class Usuario extends Persona {
	private Multa multa;
	private boolean deuda = false;
	private Bicicleta bicicleta;
	Tarjeta tarjeta;

	public void usuario(String nombre, byte edad, long id, String genero long clave) {
		super(nombre, edad, id, genero, clave); 
		// crear tarjeta
	}

	public Multa getMulta() {
		return multa;
	}

	public void setMulta(Multa multa) {
		this.multa = multa;
	}

	public boolean isDeuda() {
		return deuda;
	}

	public void setDeuda(boolean deuda) {
		this.deuda = deuda;
	}

	public Bicicleta getBicicleta() {
		return bicicleta;
	}

	public void setBicicleta(Bicicleta bicicleta) {
		this.bicicleta = bicicleta;
	}

	public Tarjeta getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(Tarjeta tarjeta) {
		this.tarjeta = tarjeta;
	}

	public void finalize() {
		System.out.println("Se ha eliminado el usuario "+nombre);
		// borrar tarjeta
	}

	public void prestar(int idB) {
		 if (!bicicleta){
			if (/*tarjeta.saldo>cuanto vamos a cobrar?*/ && !deuda{
				//conectar bicicleta a la persona
				//set estacion
				System.out.println("Prestamo aceptado");
			} 
			else if(deuda){
				System.out.println("Tiene una deuda");
			}
			else{
				System.out.println("saldo insuficiente");
			}  
		} 
		else{ 
			System.out.println("ya tiene un prestamo");
		}
		 
	}

	public void devolver(int idE) {
		if (/*bicicleta.existe(?) && estacion.max<=estacion.actual+1*/){
			 //desvincular bicicleta
			 //set estacion
			 //check multa por medio de bicicleta
		} 
		else{
			System.out.println("Usted no tiene un prestamo actualmente");
		}
	}

	public void recargarT(int q$) {
		tarjeta.recargar(q$);
		System.out.println("Recarga de: $"+q$+" realizada correctamente.");
	}

	public void pagarM() {
		if(deuda && /*tarjeta.saldo>multa.costo*/){
			tarjeta.pagarM();
			System.out.println("Pago realizado.");
		}
		else if(deuda){
			System.out.println("Saldo insuficiente.");
		}
		else{
			System.out.println("Usted no tiene multas o deudas actualmente.");
		}
	}
}
