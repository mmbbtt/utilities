package mbt.utilities;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PropertiesFileReaderFactoryTest 
{
	@Test
	public void getPropertiesFileReaderTest()
	{
		boolean ok = true;
		
		PropertiesFileReader pfr = null;
		
		try
		{
			pfr = PropertiesFileReaderFactory.getPropertiesFileReader("utilities_test.properties");
			
			if(pfr == null)
			{
				ok = false;
			}
		}
		catch(Exception e)
		{
			ok = false;
			System.out.printf("Error en PropertiesFileReaderFactoryTest.getPropertiesFileReaderTest()(): %s", e.getMessage());
		}
		
		assertEquals(true, ok);
	}
}
