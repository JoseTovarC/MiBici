package gestorAplicacion.Work;

import gestorAplicacion.User.*;

public class Multa {

	private final byte id;
	private final String descripcion;
	private int precio;

	public Multa(byte id, String descripcion, int precio) {
		this.id = id;
		this.precio = precio;
		this.descripcion = descripcion;

	}

	public byte getId() {
		return id;
	}

	public int getPrecio() {
		return precio;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String descripm() {
		return "[" + id + ", " + descripcion + "]";
	}
	
	public String descrip() {
		return "Id: " + id + ", Descripción:" +  descripcion + ", Precio: $ " + precio + "\n";
	}
	@Override
	public String toString() {
		return "[" + id + ", $ " + precio + "]";
	}
	
}
