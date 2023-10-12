package mbt.utilities.consolemenu;

import java.util.ArrayList;
import java.util.List;

public class MenuTest 
{
	/**
	 * Crea un Menu de test no localizado.
	 * 
	 * @return
	 */
	public static Menu createMenuNotLocalize()
	{
		Menu menu = null;
		
		//Teclas para salir de la aplicación
		List<String> exitKeys = new ArrayList<String>();
		exitKeys.add("S");
		exitKeys.add("s");
		exitKeys.add("E");
		exitKeys.add("e");
		exitKeys.add("Q");
		exitKeys.add("q");
		
		//Crear el objeto Menú
		menu = new Menu(
			 "Test Menú"
			,exitKeys
			,"Introduce una opción (s para salir)"
			);
		
		//Crear los items del menú
		//Menu item 1, que ejecuta un TestCommand
		MenuItem miUno = new MenuItem(
			 new MockCommand()
			,"Primer item" 
			,"1"
			);
		
		menu.addMenuItem(1, miUno);
		
		//Menu item2, que pide seleccionar una valor de una lista
		MenuItem miDos = new MenuItem(
				 new MockListCommand(menu.getScanner())
				,"Segundo item" 
				,"2"
				);
		
		menu.addMenuItem(2, miDos);
		
		return menu;
	}
	
	public static void main(String[] args) 
	{
		Menu menu = MenuTest.createMenuNotLocalize();
		
		menu.run();
	}
}
