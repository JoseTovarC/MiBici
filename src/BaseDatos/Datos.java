package BaseDatos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import gestorAplicacion.User.*;
import gestorAplicacion.Work.Multa;
import uiMain.MenuDeConsola;
import uiMain.OpcionDeMenu;
import gestorAplicacion.Bike.*;

public class Datos {

	public static HashMap<Long,Persona> hashPersona = new HashMap<>();
	public static HashMap<Long,Moderador> hashModerador = new HashMap<>();
	public static HashMap<Long,Usuario> hashUsuario = new HashMap<>();
	public static HashMap<String,Multa> hashMulta = new HashMap<>();
	public static HashMap<String,Estacion> hashEstacion = new HashMap<>();
	public static HashMap<Integer,Bicicleta> hashBicicleta = new HashMap<>();
	public static HashMap<Long, MenuDeConsola> menus = new HashMap<>();
	public static HashMap<String, OpcionDeMenu> operations = new HashMap<String, OpcionDeMenu>();
	
	
	
	public static void cargarDatos() {
		crearFilesYDirecciones();
		String ruta = System.getProperty("user.dir")+"\\src\\BaseDatos\\";
		cargarUsuarios(ruta);
		cargarAdmin(ruta);
		cargarMenus(ruta);
	}
	
	private static void cargarUsuarios(String ruta) {
		try{
            FileReader fr = new FileReader(ruta+"usuarios.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null){
            	if (!line.isEmpty()) {
            		String [] user = line.split(";");
            		String nombre = user[0];
            		String edad = user[1];
            		String genero = user[2];
            		String id = user[3];   
            		String clave = user[4];
            		String saldoTarjeta = user[5] ;	
            		
            		new Usuario(nombre, edad, id, genero, clave, saldoTarjeta,null);
            	}
            }
            br.close();
        }catch(Exception e){
            //Error al leer
        }
	}
	
	private static void cargarAdmin(String ruta) {
		try{
            FileReader fr = new FileReader(ruta+"admins.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null){
            	if (!line.isEmpty()) {
            		
            		String [] user = line.split(";");
            		String nombre = user[0];
            		String edad = user[1];
            		String id = user[2];    		
            		String genero = user[3];
            		String clave = user[4];        		
            		new Admin(nombre, edad, id, genero, clave);
            	}
            }
            br.close();
        }catch(Exception e){
        	//Error al leer
        }
	}
	
	private static void cargarMenus(String ruta) {
		try{
            FileReader fr = new FileReader(ruta+"menusUsuarios.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null){
            	if (!line.isEmpty()) {
            		String [] menu = line.split(";");
            		long aux = (long) Integer.parseInt(menu[0]);
            		Usuario user = Usuario.getUsuarioPorUsername(aux);
            		//slice de arrays
            		String[] operations = Arrays.copyOfRange(menu, 1, menu.length);
            		MenuDeConsola.newMenu(user, operations);
            	}
            }
            br.close();
        }catch(Exception e){
        	//Error al leer
        }
	}
	
	public static void guardarDatos() {
		crearFilesYDirecciones();
		String ruta = System.getProperty("user.dir")+"\\src\\BaseDatos\\";
		guardarUsuarios(ruta);
		guardarMenus(ruta);
	}
	
	private static void guardarUsuarios(String ruta){
		try {
            FileWriter fw = new FileWriter(ruta+"usuarios.txt");
            FileWriter fwAdmin = new FileWriter(ruta+"admins.txt");
            FileWriter fwModerador = new FileWriter(ruta+"moderadores.txt");
            PrintWriter pw = new PrintWriter(fw);
            PrintWriter pwAdmin = new PrintWriter(fwAdmin);
            PrintWriter pwModerador = new PrintWriter(fwModerador);
    		for (Entry<Long, Persona> user : hashPersona.entrySet()) {
    			Persona userObj = user.getValue();
    			
    			String line = userObj.getNombre()+";";
    			line += userObj.getEdad()+";";
    			line += userObj.getGenero()+";";
    			line += userObj.getId()+";";
    			line += userObj.getClave()+";";
    			if(userObj instanceof Admin) {
    				pwAdmin.println(line);
					
				}else if(userObj instanceof Moderador){
					pwModerador.println(line);
					
				}else {
					line += ((Usuario)userObj).getTarjeta().getSaldo() + ";";
					ArrayList<Multa> m = ((Usuario)userObj).getMultas();
					for(int i=0;i<m.size();i++) {
						line += m.get(i).getId() + ";";
					}
					pw.println(line.substring(0,(line.length()-1)));
    			}
    		}
            pw.close();
            pwAdmin.close();
            
        } catch (IOException ioObj) {
        	//
        	System.out.println("Ocurrio algo al guardar en txt los datos de Usuarios");
        }
	}
	
	private static void guardarMenus(String ruta){
		try {
            FileWriter fw = new FileWriter(ruta+"menusUsuarios.txt");
            PrintWriter pw = new PrintWriter(fw);
    		for (Map.Entry<Long, MenuDeConsola> menu : menus.entrySet()) {
    			MenuDeConsola menuObj = menu.getValue();
    			String line = menuObj.getUser().getId()+";";
    			for (String  opt : menuObj.getOperations()) {
    				line += opt+";";
				}
    			//Correccion por el ; extra
    			pw.println(line.substring(0,(line.length()-1)));
    		}
            pw.close();
            
        } catch (IOException ioObj) {
        	//Ocurrio algo al guardar en txt los datos
        	System.out.println("Ocurrio algo al guardar en txt los datos de Menus");
        }
	}
	
	private static void crearFilesYDirecciones() {
		try {
		String ruta = System.getProperty("user.dir")+"\\src\\BaseDatos\\";
		File directory = new File(ruta);
	    if (! directory.exists()){
	        directory.mkdir();
	    }
		File usersRegisteredFile = new File(ruta+"usuarios.txt");
		File adminUsersFile = new File(ruta+"admins.txt");
		File usersMenus = new File(ruta+"menusUsuarios.txt");
		usersRegisteredFile.createNewFile();
		adminUsersFile.createNewFile();
		usersMenus.createNewFile();
		}
		catch(IOException e){
			//Ocurrio algo al crear las carpetas y los archivos
		}
	}

}
