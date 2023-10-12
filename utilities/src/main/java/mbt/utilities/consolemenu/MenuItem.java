package mbt.utilities.consolemenu;

import java.util.Iterator;

import mbt.utilities.ActionResult;
import mbt.utilities.BussinesException;
import mbt.utilities.EResult;
import mbt.utilities.GenericActionResult;

/**
 * Clase para los items de un Menu
 * 
 */
public class MenuItem
{
	private Command command;
	private String name;
	private String key;
	
	/**
	 * Constructor obligatorio
	 * 
	 * @param command (Opcional) Objeto Command a ejectuar cuando este item sea seleccionado.
	 * @param name Nombre del Item. El que se mostrará en el menú.
	 * @param key Cadena a introducir por consola para seleccionar el item.
	 */
	public MenuItem(Command command, String name, String key)
	{
		this.command = command;
		this.name = name;
		this.key= key;
	}

	/**
	 * Comando asociado al item de menú.
	 * @return
	 */
	public Command getCommand() { return command; }

	/**
	 * Nombre del item en el menú. Se muestra por consola.
	 * @return
	 */
	public String getName() { return name; }

	/**
	 * Clave asociada al item. 
	 * Tecla a pulsar para seleccinar el item.
	 * 
	 * @return
	 */
	public String getKey() { return key; }
	
	/**
	 * Método que será invodado desde el Menu al cual pertenece cuando este items sea seleccionado.
	 * Si al constructor se le ha pasado un Command, ejecta el métode execute() de este.
	 * 
	 * @return GenericActionResult<String> con el resultado de la ejecución, donde el objeto asociado es la clave del item.
	 */
	public GenericActionResult<String> onItemSelectedHandler()
	{
		GenericActionResult<String>  result = new GenericActionResult<String> ();
		result.setResultObject(this.key);
		
		if(this.command != null)
		{
			ActionResult commandResult =  this.command.execute();
			result.setActionResult(commandResult.getResult());
			Iterator<BussinesException> iterator = commandResult.getExceptionIterator();
			while(iterator.hasNext())
			{
				result.addException(iterator.next());
			}
		}
		else
		{
			result.setActionResult(EResult.OK);
		}
		
		return result;
	}
}
