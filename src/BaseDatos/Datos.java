package BaseDatos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import gestorAplicacion.User.*;
import gestorAplicacion.Work.Multa;
import uiMain.MenuDeConsola;
import uiMain.OpcionDeMenu;
import gestorAplicacion.Bike.*;

public class Datos {

	public static HashMap<Long, Persona> hashPersona = new HashMap<>();
	public static HashMap<Long, Moderador> hashModerador = new HashMap<>();
	public static HashMap<Long, Usuario> hashUsuario = new HashMap<>();
	public static HashMap<String, Distribuidor> hashDistribuidor = new HashMap<>();
	public static HashMap<String, Multa> hashMulta = new HashMap<>();
	public static HashMap<String, Estacion> hashEstacion = new HashMap<>();
	public static HashMap<Integer, Bicicleta> hashBicicleta = new HashMap<>();
	public static HashMap<Long, MenuDeConsola> menus = new HashMap<>();
	public static HashMap<String, OpcionDeMenu> operations = new HashMap<String, OpcionDeMenu>();

	public static HashMap<Long, Integer> hashUsoP = new HashMap<>();
	public static HashMap<Integer, Integer> hashUsoB = new HashMap<>();
	public static HashMap<String, Integer> hashUsoE = new HashMap<>();
	public static HashMap<Long, Integer> hashCantM = new HashMap<>();

	public static void cargarDatos() {
		crearFilesYDirecciones();
		String ruta = System.getProperty("user.dir") + "\\src\\temp\\";
		CargarEstaciones(ruta);
		cargarUsuarios(ruta);
		cargarModeradores(ruta);
		cargarAdmin(ruta);
		cargarMenus(ruta);
		cargarDistribuidores(ruta);
		cargarBicicletas(ruta);
	}

	

	private static void CargarEstaciones(String ruta) {

		try {
			FileReader fr = new FileReader(ruta + "estaciones.txt");
			BufferedReader br = new BufferedReader(fr);
			String line;
			while ((line = br.readLine()) != null) {
				if (!line.isEmpty()) {
					String[] esta = line.split(";");
					String id = esta[0];
					String tipo = esta[1];
					String estado = esta[2];
					String cap_max = esta[3];
					new Estacion(id, tipo, estado, cap_max);
				}
			}
			br.close();
		} catch (Exception e) {
			// Error al leer
		}
	}

	private static void cargarUsuarios(String ruta) {
		try {
			FileReader fr = new FileReader(ruta + "usuarios.txt");
			BufferedReader br = new BufferedReader(fr);
			String line;
			while ((line = br.readLine()) != null) {
				if (!line.isEmpty()) {
					String[] user = line.split(";");
					String nombre = user[0];
					String edad = user[1];
					String genero = user[2];
					String id = user[3];
					String clave = user[4];
					String saldoTarjeta = user[5];
					ArrayList<Multa> multass = new ArrayList<>();
					for (byte i = 6; i < user.length; i++) {
						String mul = user[i];
						multass.add(hashMulta.get(mul));
					}
					Usuario aux = new Usuario(nombre, edad, id, genero, clave, saldoTarjeta);
					aux.setMultas(multass);
				}
			}
			br.close();
		} catch (Exception e) {
			// Error al leer
		}
	}

	private static void cargarModeradores(String ruta) {
		try {
			FileReader fr = new FileReader(ruta + "moderadores.txt");
			BufferedReader br = new BufferedReader(fr);
			String line;
			while ((line = br.readLine()) != null) {
				if (!line.isEmpty()) {
					String[] user = line.split(";");
					String nombre = user[0];
					String edad = user[1];
					String genero = user[2];
					String id = user[3];
					String clave = user[4];
					String saldoTarjeta = user[5];
					String EstacionId = user[6];
					Moderador aux = new Moderador(nombre, edad, id, genero, clave, saldoTarjeta);
					if (!EstacionId.equals("")) {
						aux.setEstacion(Estacion.getEstacionporId(EstacionId));
					}

				}
			}
			br.close();
		} catch (Exception e) {
			// Error al leer
		}
	}

	private static void cargarAdmin(String ruta) {
		try {
			FileReader fr = new FileReader(ruta + "admins.txt");
			BufferedReader br = new BufferedReader(fr);
			String line;
			while ((line = br.readLine()) != null) {
				if (!line.isEmpty()) {

					String[] user = line.split(";");
					String nombre = user[0];
					String edad = user[1];
					String id = user[2];
					String genero = user[3];
					String clave = user[4];
					new Admin(nombre, edad, id, genero, clave);
				}
			}
			br.close();
		} catch (Exception e) {
			// Error al leer
		}
	}

	private static void cargarMenus(String ruta) {
		try {
			FileReader fr = new FileReader(ruta + "menusUsuarios.txt");
			BufferedReader br = new BufferedReader(fr);
			String line;
			while ((line = br.readLine()) != null) {
				if (!line.isEmpty()) {
					String[] menu = line.split(";");
					long aux = (long) Integer.parseInt(menu[0]);
					Persona user = Usuario.getUsuarioPorUsername(aux);
					// slice de arrays
					String[] operations = Arrays.copyOfRange(menu, 1, menu.length);
					MenuDeConsola.newMenu(user, operations);
				}
			}
			br.close();
		} catch (Exception e) {
			// Error al leer
		}
	}

	private static void cargarDistribuidores(String ruta) {
		try {
			FileReader fr = new FileReader(ruta + "distribuidores.txt");
			BufferedReader br = new BufferedReader(fr);
			String line;
			while ((line = br.readLine()) != null) {
				if (!line.isEmpty()) {
					String[] distri = line.split(";");
					String id = distri[0];
					String nombre = distri[1];
					new Distribuidor(nombre, id);
				}
			}
			br.close();
		} catch (Exception e) {
			// Error al leer
		}

	}
	
	private static void cargarBicicletas(String ruta) {
		try {
			FileReader fr = new FileReader(ruta + "bicicletas.txt");
			BufferedReader br = new BufferedReader(fr);
			String line;
			while ((line = br.readLine()) != null) {
				if (!line.isEmpty()) {
					String[] bici = line.split(";");
					String id = bici[0];
					String danio = bici[1];
					String IdUsuario = bici[2];
					String IdDistribuidor = bici[3];
					String IdEstacion = bici[4];
					
					new Bicicleta(id, danio,IdUsuario, IdDistribuidor,IdEstacion);
				}
			}
			br.close();
		} catch (Exception e) {
			// Error al leer
		}
		
	}

	public static void guardarDatos() {
		crearFilesYDirecciones();
		String ruta = System.getProperty("user.dir") + "\\src\\BaseDatos\\";
		guardarEstaciones(ruta);
		guardarUsuarios(ruta);
		guardarMenus(ruta);
		guardarDistribuidores(ruta);
		guardarBicicletas(ruta);
	}

	private static void guardarEstaciones(String ruta) {
		try {
			FileWriter fw = new FileWriter(ruta + "estaciones.txt");
			PrintWriter pw = new PrintWriter(fw);
			for (Map.Entry<String, Estacion> esta : hashEstacion.entrySet()) {
				Estacion estacion = esta.getValue();
				// String id, String tipo, String esta, String cap_max
				String line = estacion.getIde() + ";";
				line += estacion.getTipo() + ";";
				line += estacion.isEstado() + ";";
				line += estacion.getCap_max();
				// Correccion por el ; extra
				pw.println(line);
			}
			pw.close();

		} catch (IOException ioObj) {
			// Ocurrio algo al guardar en txt los datos
			System.out.println("Ocurrio algo al guardar en txt los datos de las estaciones");
		}

	}

	private static void guardarUsuarios(String ruta) {
		try {
			FileWriter fw = new FileWriter(ruta + "usuarios.txt");
			FileWriter fwAdmin = new FileWriter(ruta + "admins.txt");
			FileWriter fwModerador = new FileWriter(ruta + "moderadores.txt");
			PrintWriter pw = new PrintWriter(fw);
			PrintWriter pwAdmin = new PrintWriter(fwAdmin);
			PrintWriter pwModerador = new PrintWriter(fwModerador);
			for (Entry<Long, Persona> user : hashPersona.entrySet()) {
				Persona userObj = user.getValue();

				String line = userObj.getNombre() + ";";
				line += userObj.getEdad() + ";";
				line += userObj.getGenero() + ";";
				line += userObj.getId() + ";";
				line += userObj.getClave() + ";";
				if (userObj instanceof Admin) {
					pwAdmin.println(line.substring(0, (line.length() - 1)));
				} else if (userObj instanceof Moderador) {

					line += ((Usuario) userObj).getTarjeta().getSaldo() + ";";
					if (((Moderador) userObj).getEstacion() != null) {
						line += ((Moderador) userObj).getEstacion().getIde();
					}

					pwModerador.println(line);

				} else {
					line += ((Usuario) userObj).getTarjeta().getSaldo() + ";";
					ArrayList<Multa> m = ((Usuario) userObj).getMultas();
					for (int i = 0; i < m.size(); i++) {
						line += m.get(i).getId() + ";";
					}
					pw.println(line.substring(0, (line.length() - 1)));
				}
			}
			pw.close();
			pwAdmin.close();
			pwModerador.close();

		} catch (IOException ioObj) {
			//
			System.out.println("Ocurrio algo al guardar en txt los datos de Usuarios");
		}
	}

	private static void guardarMenus(String ruta) {
		try {
			FileWriter fw = new FileWriter(ruta + "menusUsuarios.txt");
			PrintWriter pw = new PrintWriter(fw);
			for (Map.Entry<Long, MenuDeConsola> menu : menus.entrySet()) {
				MenuDeConsola menuObj = menu.getValue();
				String line = menuObj.getUser().getId() + ";";
				for (String opt : menuObj.getOperations()) {
					line += opt + ";";
				}
				// Correccion por el ; extra
				pw.println(line.substring(0, (line.length() - 1)));
			}
			pw.close();

		} catch (IOException ioObj) {
			// Ocurrio algo al guardar en txt los datos
			System.out.println("Ocurrio algo al guardar en txt los datos de Menus");
		}
	}

	private static void guardarDistribuidores(String ruta) {

		try {

			FileWriter fw = new FileWriter(ruta + "distribuidores.txt");
			PrintWriter pw = new PrintWriter(fw);

			for (Entry<String, Distribuidor> distri : hashDistribuidor.entrySet()) {
				Distribuidor dis = distri.getValue();

				String line = dis.getId() + ";";
				line += dis.getNombre();
				
				pw.println(line);

			}
			pw.close();

		} catch (IOException ioObj) {
			// Ocurrio algo al guardar en txt los datos
			System.out.println("Ocurrio algo al guardar en txt los datos de Distribuidores");
		}

	}

	private static void guardarBicicletas(String ruta) {
	
		try {

			FileWriter fw = new FileWriter(ruta + "bicicletas.txt");
			PrintWriter pw = new PrintWriter(fw);

			for (Entry<Integer, Bicicleta> bici: hashBicicleta.entrySet()) {
				Bicicleta bicicleta = bici.getValue();

				String line = bicicleta.getId() + ";";
				line += bicicleta.isDanio() + ";";
				if(bicicleta.getUsuario()==null) {
					line += "0;";
				}else {
					line += bicicleta.getUsuario().getId() + ";";
				}
				
				if(bicicleta.getDistribuidor()==null) {
					line += "0;";
				}else {
					line += bicicleta.getDistribuidor().getId() + ";";
				}
				
				if(bicicleta.getEstacion()==null) {
					line += "0";
				}else {
					line += bicicleta.getEstacion().getIde();
				}

				pw.println(line);

			}
			pw.close();

		} catch (IOException ioObj) {
			// Ocurrio algo al guardar en txt los datos
			System.out.println("Ocurrio algo al guardar en txt los datos de Distribuidores");
		}
	}

	private static void crearFilesYDirecciones() {
		try {
			String ruta = System.getProperty("user.dir") + "\\src\\BaseDatos\\";
			File directory = new File(ruta);
			if (!directory.exists()) {
				directory.mkdir();
			}
			File estacionesRegisteredFile = new File(ruta + "estaciones.txt");
			File distribuidoresRegisteredFile = new File(ruta + "distribuidores.txt");
			File moderadoresRegisteredFile = new File(ruta + "moderadores.txt");
			File usersRegisteredFile = new File(ruta + "usuarios.txt");
			File adminUsersFile = new File(ruta + "admins.txt");
			File usersMenus = new File(ruta + "menusUsuarios.txt");
			File bicicletasRegisteredFile = new File(ruta + "bicicletas.txt");
			estacionesRegisteredFile.createNewFile();
			distribuidoresRegisteredFile.createNewFile();
			moderadoresRegisteredFile.createNewFile();
			usersRegisteredFile.createNewFile();
			adminUsersFile.createNewFile();
			usersMenus.createNewFile();
			bicicletasRegisteredFile.createNewFile();
		} catch (IOException e) {
			// Ocurrio algo al crear las carpetas y los archivos
		}
	}

	// METODOS FUNCIONALES //
	public static StringBuffer getMayorUsoP() {
		StringBuffer r;
		Deque<Long> cola = new LinkedList<>();
		int max = 0;
		for (Map.Entry<Long, Integer> entry : hashUsoP.entrySet()) {
			if (entry.getValue() > max) {
				cola.clear();
				cola.add(entry.getKey());
				max = entry.getValue();
			} else if (entry.getValue() == max) {
				cola.add(entry.getKey());
			}
		}
		if (cola.isEmpty()) {
			r = new StringBuffer("No se han registrado usos");
		} else if (cola.size() == 1) {
			if (max == 1) {
				r = new StringBuffer("El usuario que mas usos realizo (1 uso), fue: ");
			} else {
				r = new StringBuffer("El usuario que mas usos realizo (" + max + " usos), fue: ");
			}
		} else {
			if (max == 1) {
				r = new StringBuffer("Los usuarios que mas usos realizaron (1 uso), fueron: ");
			} else {
				r = new StringBuffer("Los usuarios que mas usos realizaron (" + max + " usos), fueron: ");
			}
		}
		while (true) {
			if (cola.size() > 0) {
				r.append(cola.poll() + " ");
			} else {
				break;
			}
		}
		return r;
	}

	public static StringBuffer getMayorUsoB() {
		StringBuffer r;
		Deque<Integer> cola = new LinkedList<>();
		int max = 0;
		for (Map.Entry<Integer, Integer> entry : hashUsoB.entrySet()) {
			if (entry.getValue() > max) {
				cola.clear();
				cola.add(entry.getKey());
				max = entry.getValue();
			} else if (entry.getValue() == max) {
				cola.add(entry.getKey());
			}
		}
		if (cola.isEmpty()) {
			r = new StringBuffer("No se han registrado usos");
		} else if (cola.size() == 1) {
			if (max == 1) {
				r = new StringBuffer("La bicicleta mas usada (1 uso), fue: ");
			} else {
				r = new StringBuffer("La bicicleta mas usada (" + max + " usos), fue: ");
			}
		} else {
			if (max == 1) {
				r = new StringBuffer("Las bicicletas mas usada (1 uso), fueron: ");
			} else {
				r = new StringBuffer("Las bicicletas mas usada (" + max + " usos), fueron: ");
			}
		}
		while (true) {
			if (cola.size() > 0) {
				r.append(cola.poll() + " ");
			} else {
				break;
			}
		}
		return r;
	}

	public static StringBuffer getMayorUsoE() {
		StringBuffer r;
		Deque<String> cola=new LinkedList<>();
		int max=0;
		for (Map.Entry<String, Integer> entry : hashUsoE.entrySet()) {
			if (entry.getValue()>max) {
				cola.clear();
				cola.add(entry.getKey());
				max=entry.getValue();
			}
			else if (entry.getValue()==max) {
				cola.add(entry.getKey());
			}
		}
		if(cola.isEmpty()) {
			r = new StringBuffer("No se han registrado usos");
		}
		else if (cola.size()==1) {
			if (max==1) {
				r = new StringBuffer("La estacion mas usada (1 uso), fue: ");
			}
			else {
				r = new StringBuffer("La estacion mas usada ("+ max +" usos), fue: ");
			}
		}
		else {
			if (max==1) {
				r = new StringBuffer("Las estaciones mas usada (1 uso), fueron: ");
			}
			else {
				r = new StringBuffer("Las estaciones mas usada ("+ max +" usos), fueron: ");
			}
		}
		while (true){
			if (cola.size()>0) {
				r.append(cola.poll()+" ");
			}
			else {
				break;
			}
		}
		return r;
	}
	
	public static StringBuffer getMayorCantM() {
		StringBuffer r;
		Deque<Long> cola = new LinkedList<>();
		int max = 0;
		for (Map.Entry<Long, Integer> entry : hashCantM.entrySet()) {
			if (entry.getValue() > max) {
				cola.clear();
				cola.add(entry.getKey());
				max = entry.getValue();
			} else if (entry.getValue() == max) {
				cola.add(entry.getKey());
			}
		}
		if (cola.isEmpty()) {
			r = new StringBuffer("No se han registrado multas");
		} else if (cola.size() == 1) {
			if (max == 1) {
				r = new StringBuffer("El usuario con mayor cantidad de multas (fue multado 1 vez), fue: ");
			} else {
				r = new StringBuffer("El usuario con mayor cantidad de multas (fue multado " + max + " veces), fue: ");
			}
		} else {
			if (max == 1) {
				r = new StringBuffer("Los usuarios con mayor cantidad de multas (fueron multados 1 vez), fueron: ");
			} else {
				r = new StringBuffer(
						"Los usuarios con mayor cantidad de multas (fueron multados " + max + " veces), fueron: ");
			}
		}
		while (true) {
			if (cola.size() > 0) {
				r.append(cola.poll() + " ");
			} else {
				break;
			}
		}
		return r;
	}

	public static StringBuffer getMayorEdad() {
		StringBuffer r;
		Deque<Long> cola = new LinkedList<>();
		int max = 0;
		for (Map.Entry<Long, Persona> entry : hashPersona.entrySet()) {
			if (entry.getValue().getEdad() > max) {
				cola.clear();
				cola.add(entry.getKey());
				max = entry.getValue().getEdad();
			} else if (entry.getValue().getEdad() == max) {
				cola.add(entry.getKey());
			}
		}
		if (cola.isEmpty()) {
			r = new StringBuffer("No se han registrado datos");
		} else if (cola.size() == 1) {
			r = new StringBuffer("El usuario de mayor edad (" + max + " años), fue: ");
		} else {
			r = new StringBuffer("Los usuarios de mayor edad (" + max + " años), fueron: ");
		}
		while (true) {
			if (cola.size() > 0) {
				r.append(cola.poll() + " ");
			} else {
				break;
			}
		}
		return r;
	}

	public static StringBuffer getMenorEdad() {
		StringBuffer r;
		Deque<Long> cola = new LinkedList<>();
		int min = 200;
		for (Map.Entry<Long, Persona> entry : hashPersona.entrySet()) {
			if (entry.getValue().getEdad() < min) {
				cola = new LinkedList<>();
				cola.add(entry.getKey());
				min = entry.getValue().getEdad();
			} else if (entry.getValue().getEdad() == min) {
				cola.add(entry.getKey());
			}
		}
		if (cola.isEmpty()) {
			r = new StringBuffer("No se han registrado datos");
		} else if (cola.size() == 1) {
			r = new StringBuffer("El usuario de menor edad (" + min + " años), fue: ");
		} else {
			r = new StringBuffer("Los usuarios de menor edad (" + min + " años), fueron: ");
		}
		while (true) {
			if (cola.size() > 0) {
				System.out.print(cola.poll() + " ");
			} else {
				break;
			}
		}
		return r;
	}

	static public StringBuffer getPromEdad() {
		StringBuffer r;
		int prom = 0;
		int q = 0;
		for (Map.Entry<Long, Persona> entry : hashPersona.entrySet()) {
			prom += entry.getValue().getEdad();
			q += 1;
		}
		prom /= q;
		r = new StringBuffer("El promedio de edad de todos los usuarios registrados es de: " + prom + " años");
		return r;
	}

	static public StringBuffer getPromCantB() {
		StringBuffer r;
		int prom=0;
		int q=0;
		for (Map.Entry<String, Estacion> entry : hashEstacion.entrySet()) {
			prom+= entry.getValue().getCantBicis();
			q+=1;
		}
		prom/=q;
		r= new StringBuffer("El promedio de la cantidad bicicletas en todas las estaciones es de: "+ prom +" bicicletas");
		return r;
	}
	static public StringBuffer getPorcGen() {
		StringBuffer r;
		int q=0;
		int qh=0;
		for (Map.Entry<Long, Integer> entry : hashUsoP.entrySet()) {
			q+= 1;
			if (hashPersona.get(entry.getKey()).getGenero().contentEquals("M")) {
				qh+= 1;
			}
		}
		r=new StringBuffer("El porcentaje de hombres es de: "+(qh*100/q)+"%.");
		r.append("El porcentaje de mujeres es de: "+(100-(qh*100/q))+"%.");
		return r;
	}
	static public StringBuffer getPorcUsoGen() {
		StringBuffer r;
		int q=0;
		int qh=0;
		for (Map.Entry<Long, Integer> entry : hashUsoP.entrySet()) {
			q+= entry.getValue();
			if (hashPersona.get(entry.getKey()).getGenero().contentEquals("M")) {
				qh+= entry.getValue();
			}
		}
		r=new StringBuffer("El porcentaje de uso realizado por hombres es de: "+(qh*100/q)+"%.");
		r.append("El porcentaje de uso realizado por mujeres es de: "+(100-(qh*100/q))+"%.");
		return r;
	}
}