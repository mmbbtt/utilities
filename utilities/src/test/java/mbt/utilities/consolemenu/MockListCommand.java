package mbt.utilities.consolemenu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import mbt.utilities.ActionResult;
import mbt.utilities.EResult;
import mbt.utilities.GenericActionResult;

/**
 * Mock List Command para usar en los test de Menu.
 * Muestra una lista de opciones por consola y devuelve el elemento seleccionado.
 * 
 */
public class MockListCommand extends Command 
{
	public MockListCommand(Scanner inputScanner) 
	{
		super(inputScanner);
	}

	@Override
	public ActionResult execute() 
	{
		ActionResult ar = new ActionResult();
		
		List<String> listItemsToSelect = new ArrayList<String>();
		listItemsToSelect.add("Opción uno");
		listItemsToSelect.add("Opción dos");
		listItemsToSelect.add("Opción tres");
		
		List<String> listExitKeys = new ArrayList<String>();
		listExitKeys.add("s");
		listExitKeys.add("S");
		
		GenericActionResult<String> garSelectedItem = this.selectItemList(
				listItemsToSelect, 
				"Opciones", 
				listExitKeys, 
				"Selecciona una opción (s para salir)"
				);
		
		if(garSelectedItem.getResult() != EResult.OK)
		{
			System.out.printf("Error: %s", garSelectedItem.getExceptionsMessages());
		}
		else
		{
			System.out.printf("Opción seleccionada: %s", garSelectedItem.getResultObject());
		}
		
		return ar;
	}

}
