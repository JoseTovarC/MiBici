package uiMain;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuDeConsola {
	
	public ArrayList<OpcionDeMenu> options = new ArrayList<OpcionDeMenu> ();
	
	public MenuDeConsola(ArrayList<OpcionDeMenu> options){
		this.options = options;
	}
	
	public void lanzarMenu(){
		Scanner esc = new Scanner(System.in);
		
		int i = 1;
		for (OpcionDeMenu option : options) {
			
			System.out.println(i+" "+option);
			i++;
		}
		System.out.print("Ingrese la opcion: ");
		int opt = esc.nextInt();
		options.get((opt-1)).ejecutar();
		
	}
	
	public void añadirOpcion(OpcionDeMenu option) {
		options.add(option);
	}
}