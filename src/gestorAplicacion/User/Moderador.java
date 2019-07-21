package gestorAplicacion.User;
//import java.util.ArrayList;

//import Work.*;
import gestorAplicacion.Bike.*;
import uiMain.MenuDeConsola;

public class Moderador extends Usuario{
	private Estacion estacion;


	public Moderador(String nombre, byte edad, long id, String genero, String clave, int saldo, Estacion estacion) {
		super(nombre, edad, id, genero, clave, saldo, "No Agregar a la hash de Usuarios");
		this.estacion = estacion;
		BaseDatos.Datos.hashModerador.put(id,this);
	}

	public void multar(Usuario usuario, String tipo) {
		
		
		
		/*
		usuario.setDeuda(true);
		Tarjeta a = new Tarjeta();
		a = usuario.getTarjeta();
		ArrayList<Multa> b = new ArrayList<>();
		b = a.getMultas();
		b.add(null);
		a.setMultas(b);
		usuario.setTarjeta(a);
		System.out.println("Se ha multado al usuario: " + usuario.getNombre());
		*/
	}

	public void danioB(Usuario usuario, Bicicleta bicicleta/*, Distribuidor distribuidor*/) {
		this.multar(usuario, "danio");
		//distribuidor.arreglar(bicicleta);
		System.out.println("Se ha detectado un daño en la bicicleta " +  bicicleta.getId()
				+ " ,se ha multado al usuario "/* + usuario.getNombre()*/
				+ " y se ha solicitado arreglo de la bicileta con el distribuidor: "/* + distribuidor.getNombre()*/);
	}

	public Estacion getEstacion() {
		return estacion;
	}

	@Override
	public void finalize() {
		// TODO Auto-generated method stub
		
	}



}
