package gestorAplicacion.Bike;
import java.util.ArrayList;
import java.util.Scanner;

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
	
	public void addBicicleta(Bicicleta bici) {
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
	
	public void transportar(int id, int q) {
		
	}
	public void arreglar(String id) {
		
	}
	/*public ArrayList<Bicicleta> crearB(int q) { 
		ArrayList<Bicicleta> lote = new ArrayList<Bicicleta>();
		for(int x=1;x<=q;x++) {
			System.out.println("Ingrese el ID de bicicleta "+x);
			int id = entrada.nextInt();
			
		}
	}*/
	
}
