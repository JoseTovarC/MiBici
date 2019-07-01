package BaseDatos;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import gestorAplicacion.User.*;
import uiMain.MenuDeConsola;
import uiMain.OpcionDeMenu;
import gestorAplicacion.Bike.*;

public class Datos {

	public static HashMap<Long,Persona> hashPersona = new HashMap<>();
	public static HashMap<Integer,Estacion> hashEstacion = new HashMap<>();
	public static HashMap<Integer,Bicicleta> hashBicicleta = new HashMap<>();
	public static HashMap<String, MenuDeConsola> menus = new HashMap<String, MenuDeConsola>();
	public static HashMap<String, OpcionDeMenu> operations = new HashMap<String, OpcionDeMenu>();
	public static void cargarDatos() {
		try{
			/*File directory = new File("temp");
		    if (! directory.exists()){
		        directory.mkdir();
		        // If you require it to make the entire directory path including parents,
		        // use directory.mkdirs(); here instead.
		    }*/
			String ruta = System.getProperty("user.dir")+"\\src\\BaseDatos\\";
			/*File yourFile = new File("users.txt");
			yourFile.createNewFile();*/
            FileReader fr = new FileReader(ruta+"usuarios.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            int lines = 0;
            while((line = br.readLine()) != null){
            	lines++;
                String [] user = line.split(";");
                System.out.println(line);
                //TODO: decidir que hacer
            }
            br.close();
        }catch(Exception e){
            System.out.println("Error al leer");
        }
	}
	
	public static void escribirUsuarios(Usuario user) {

		try {
			String ruta = System.getProperty("user.dir")+"\\src\\BaseDatos\\";
            FileWriter fw = new FileWriter(ruta+"usuarios.txt", true);
            PrintWriter pw = new PrintWriter(fw);
            String line = "hola;";
            line += "mundo;";
            pw.println(line);
            pw.close();
        } catch (IOException ioObj) {
            System.out.println("Ocurrio un problema");
        }
	}

}
