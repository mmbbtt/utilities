package mbt.utilities;

import java.util.ResourceBundle;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

public class ResourceBundleReaderTest 
{
	ResourceBundleReader rbr = null;
	
	public ResourceBundleReaderTest()
	{
		try
		{
			ResourceBundle bundle = ResourceBundle.getBundle("messages-test", new Locale("es", "ES"));
			
			rbr = new ResourceBundleReader(bundle);
		}
		catch(Exception e)
		{
			System.out.printf("Error en ResourceBundleReaderTest.ResourceBundleReaderTest(): %s", e.getMessage());
		}
	}
	
	@Test
	public void ConstructorTest()
	{
		boolean ok = true;
		
		if(rbr == null)
		{
			ok = false;
		}
		
		assertEquals(true, ok);
	}
	
	@Test
	public void getBundleKeyTest()
	{
		assertEquals("messages-test_es_ES", ResourceBundleReader.getBundleKey("messages-test", new Locale("es", "ES")));
	}
	
	@Test
	public void getLocalizedMessageTest()
	{
		boolean ok = true;
		
		String msg = null;
		
		//Obtener un mensaje existente sin argumentos:
		// Esto e sun mensaje de test sin argumentos
		msg = this.rbr.getLocalizedMessage("MessageTestSinArgumentos", null);
		
		if(! "Esto e sun mensaje de test sin argumentos".equals(msg))
		{
			ok = false;
		}
		else
		{
			//Obtener un mensaje existente con 2 argumentos:
			//Esto es un mensaje de test con el primer argumento igua a {0} y el segundo igual {1}
			Object args[] = {"uno", 2};
			
			msg = this.rbr.getLocalizedMessage("MessageTestConA2rgumentos", args);
			
			if(! "Esto es un mensaje de test con el primer argumento igua a uno y el segundo igual 2".equals(msg))
			{
				ok = false;
			}
			else
			{
				//Pedir una mensaje no definido
				String msgKey = "MessageTestInexistente";
				msg = this.rbr.getLocalizedMessage(msgKey, null);
				
				String expectedValue = String.format("%s%s", this.rbr.getKeyNotDefinedPrefix(), msgKey);
				
				if(! expectedValue.equals(msg))
				{
					ok = false;
				}
			}
		}
		
		assertEquals(true, ok);
	}
}
