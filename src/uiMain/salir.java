
package uiMain;



public class salir extends OpcionDeMenu {
	
	public salir(String key){
		super(key);
	}
    public salir() {
    }
    
    @Override
	public void ejecutar() {
		Main.aux = false;
	}

	@Override
	public String toString() {
		return "Salir";
	}
}
