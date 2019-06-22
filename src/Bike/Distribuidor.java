package Bike;

import java.util.ArrayList;

//import User.Usuario;

public class Distribuidor {
	private String nombre;
	private String id;
	private int numero_bicis;
	private ArrayList<Bicicleta> bicicletas = new ArrayList<>();
	
	public Distribuidor(String nombre, String id, int numero_bicis){
		this.nombre = nombre;
		this.id = id;
		this.numero_bicis = numero_bicis;
	}
	public void finalize() {
		
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
	public void setBicicletas(ArrayList<Bicicleta> bicicletas) {
		this.bicicletas = bicicletas;
	}
	
	public void transportar(int id, int q) {
		
	}
	public void arreglar(String id) {
		
	}
	/*public Bicicleta crearB(int q) {
		
	}*/
	
}
