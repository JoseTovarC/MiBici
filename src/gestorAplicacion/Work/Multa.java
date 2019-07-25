package gestorAplicacion.Work;
import gestorAplicacion.Bike.*;
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
	public String toString() {
		return "Id: "+id+". Precio: "+precio+". Descripcion: "+descripcion;
	}
}
