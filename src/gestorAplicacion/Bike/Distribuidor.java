package gestorAplicacion.Bike;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;
import BaseDatos.Datos;

//import User.Usuario;

public class Distribuidor {
	private String nombre;
	private String id;
	private int numero_bicis;
	private ArrayList<Bicicleta> bicicletas = new ArrayList<>();
	Scanner entrada = new Scanner(System.in);
	
	public Distribuidor(String nombre, String id){
		this.nombre = nombre;
		this.id = id;
		numero_bicis = bicicletas.size();
		BaseDatos.Datos.hashDistribuidor.put(id,this);
	}
	public Distribuidor(String nombre, String id, int num_bicis){
		this.nombre = nombre;
		this.id = id;
		this.numero_bicis = num_bicis;
		BaseDatos.Datos.hashDistribuidor.put(id,this);
	}
	public void finalize() {
		
	}
	
	public static int getTotalBicicletas() {
		int i=0;
		for (Entry<String, Distribuidor> distri : BaseDatos.Datos.hashDistribuidor.entrySet()) {
			Distribuidor dis = distri.getValue();

			i += dis.getNumero_bicis();
			

		}
		return i;
	}
	
	
	public void addBicicleta(Bicicleta bici) {
		this.numero_bicis++;
		this.bicicletas.add(bici);
	}
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getNumero_bicis() {
		return numero_bicis;
	}
	public void setNumero_bicis(int numero_bicis) {
		this.numero_bicis = numero_bicis;
	}
	
	public ArrayList<Bicicleta> getBicicletas() {
		return bicicletas;
	}
	public void setBicicletas(Bicicleta bicicleta) { //recibe una bicicleta y la agrega al arreglo bicicletas de la clase.
		this.bicicletas.add(bicicleta); 
	}
	
	public static void transportar(Estacion estacion) {
		Estacion quitar=null;
		for(Entry<String, Estacion> est: Datos.hashEstacion.entrySet()) {
			Estacion esta = est.getValue();
			int max=0;
			if(esta.getCantBicis()>max) {
				quitar = esta;
				max = esta.getCantBicis();
			}
		}
		if(quitar.getCantBicis()!=0) {
				int max = (int) (quitar.getCantBicis()*0.5);
				for(int i = 0;i<max;i++) {
					Bicicleta b = quitar.sacarBicicletas();
					estacion.addBicicleta(b);
					b.setEstacion(estacion);
				}
		}
	}
	public void arreglar(int id) {
		for (Entry<Integer, Bicicleta> bici: Datos.hashBicicleta.entrySet()) {
			Bicicleta bicicleta = bici.getValue();
			if(bicicleta.getId()==id) {
				Estacion estacion = bicicleta.getEstacion();
				for(int x=0;x<estacion.getBicicletas().length;x++) {
					if(estacion.getBicicletas()[x].getId()==bicicleta.getId()) {
						estacion.getBicicletas()[x].setDanio(false);
					}
				}
				break;
			}
		}
	}
	public String crearB(Estacion estacion) {
		return "Se ha creado la bicicleta: "+new Bicicleta(BaseDatos.Datos.hashBicicleta.size()+1, estacion, this).toString();
	}
	
}
