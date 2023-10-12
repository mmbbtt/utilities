package mbt.utilities;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PropertiesFileReaderTest 
{
	private PropertiesFileReader pfr = null;
	
	public PropertiesFileReaderTest()
	{
		try
		{
			pfr = new PropertiesFileReader("utilities_test.properties");
		}
		catch(Exception e)
		{
			System.out.printf("Error en PropertiesFileReaderTest.PropertiesFileReaderTest(): %s", e.getMessage());
		}
	}
	
	@Test
	public void ConstructorTest()
	{
		boolean ok = true;
		
		if(pfr == null)
		{
			ok = false;
		}
		
		assertEquals(true, ok);
	}
	
	@Test
	public void getNamePropertiesFileTest()
	{
		assertEquals("utilities_test.properties", pfr.getNamePropertiesFile());
	}
	
	@Test
	public void getPropertyTest()
	{
		boolean ok = true;
		
		if(! "es".equals(pfr.getProperty("Locale.Language")))
		{
			ok = false;
			System.out.println("PropertiesFileReaderTest.getPropertyTest() falla el método 'getProperty(String name)'.");
		}
		else
		{
			if(! "valor por defecto".equals(pfr.getProperty("propiedad.que.no.existe", "valor por defecto")))
			{
				ok = false;
				System.out.println("PropertiesFileReaderTest.getPropertyTest() falla el método 'getProperty(String name, String defaultValue)'.");
			}
		}
		
		assertEquals(true, ok);
	}
}
