package User;
import Bike.*;

public class Moderador extends Persona {
	private Estacion estacion;

	public Moderador(String nombre, byte edad, long doc_id, String genero, long clave, Estacion estacion) {
		super(nombre, edad, doc_id, genero, clave);
		this.estacion = estacion;
	}

	public void multar(Usuario usuario, String tipo) {
		usuario.setMulta(new Multa(tipo));
		usuario.setDeuda(true);
		//usuario.tarjeta.setMultas(new Multa(tipo));
		System.out.println("Se ha multado al usuario: " + usuario.getNombre());

	}

	public void danioB(Usuario usuario, Bicicleta bicicleta/*, Distribuidor distribuidor*/) {
		this.multar(usuario, "danio");
		//distribuidor.arreglar(bicicleta);
		System.out.println("Se ha detectado un da�o en la bicicleta " + bicicleta.getId()
				+ " ,se ha multado al usuario " + usuario.getNombre()
				+ " y se ha solicitado arreglo de la bicileta con el distribuidor: " + distribuidor.getNombre());
	}

	public Estacion getEstacion() {
		return estacion;
	}

}
