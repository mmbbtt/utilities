package mbt.utilities.consolemenu;

import mbt.utilities.ActionResult;
import mbt.utilities.EResult;

/**
 * Mock Command para usar en los test de Menu.
 * Escribe una línea por consola.
 * 
 */
public class MockCommand extends Command 
{

	public MockCommand() 
	{
		super(null);
	}

	@Override
	public ActionResult execute() 
	{
		System.out.println("MockCommand se ha ejectuado correctamente.");
		
		return new ActionResult(EResult.OK);
	}

}
