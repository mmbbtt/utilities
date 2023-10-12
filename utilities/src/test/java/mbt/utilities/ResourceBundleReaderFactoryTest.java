package mbt.utilities;

import static org.junit.Assert.assertEquals;

import java.util.Locale;

import org.junit.Test;

public class ResourceBundleReaderFactoryTest 
{
	@Test
	public void getResourceBundleReaderTest()
	{
		boolean ok = true;
		
		ResourceBundleReader rbr = null;
		
		try
		{
			rbr = ResourceBundleReaderFactory.getResourceBundleReader("messages-test", new Locale("es", "ES"));
			
			if(rbr == null)
			{
				ok = false;
			}
		}
		catch(Exception e)
		{
			ok = false;
			System.out.printf("Error en ResourceBundleReaderFactoryTest.getResourceBundleReaderTest(): %s", e.getMessage());
		}
		
		assertEquals(true, ok);
	}
}
