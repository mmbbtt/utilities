package mbt.utilities;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Clase de utilidad para leer archivos de recursos de mensajes localizados.
 * 
 */
public class ResourceBundleReader
{
	private ResourceBundle bundle;
	private MessageFormat formatter;
	private String name;
	private String keyNotDefinedPrefix = "KEY_NOT_DEFINED_";
	
	/**
	 * Devuevle el nombre del archivo de recursos localizados.
	 * 
	 * @return
	 */
	public String getName() { return this.name; }
	
	/**
	 * Prefijo que se usa en el método getLocalizedMessage(String localizedMessageKey, Object[] messageArguments)
	 * para construir el valor devuelto cuando la clave no está definida.
	 * 
	 * @return
	 */
	public String getKeyNotDefinedPrefix() { return this.keyNotDefinedPrefix; }
	
	/**
	 * Constructor obligatorio.
	 * 
	 * @param bundle Archivo de recursos localizado.
	 */
	public ResourceBundleReader(ResourceBundle bundle)
	{
		this.bundle = bundle;
		formatter = new MessageFormat(""); 
		formatter.setLocale(this.bundle.getLocale());
		
		this.name = ResourceBundleReader.getBundleKey(bundle.getBaseBundleName(), bundle.getLocale());
	}
	
	/**
	 * Devuelve el valor asociado a la clave pasada por argumento del archivo de recursos asociado al Reader.
	 * Si se le pasan argumentos, estos se usan para formatear el valor devuelto.
	 * Si la clave no está definida devuelve un texto formateado como <getKeyNotDefinedPrefix><localizedMessageKey>
	 * 
	 * @param localizedMessageKey Clave en el archivo de recursos
	 * @param messageArguments	Argumentos del valor asociados a la clave.
	 * @return
	 */
	public String getLocalizedMessage(String localizedMessageKey, Object[] messageArguments)
	{
		String localizedMessage = localizedMessageKey;
		
		boolean keyExists = false;
		try
		{
			localizedMessage = this.bundle.getString(localizedMessageKey);
			keyExists = true;
		}
		catch(Exception e)
		{
			//Si la propiedad no existe o no tiene valor, lo advertimos por log y devolvemos la clave
			Logger logger = LogManager.getLogger(ResourceBundleReader.class);
			logger.error(String.format(
				"Error en getLocalizedMessage(): El archivo de propiedades %s no contiene la clave %s"
				,this.name
				,localizedMessageKey
				));
			
			localizedMessage = String.format("%s%s", this.keyNotDefinedPrefix, localizedMessageKey);
		}
		
		if(keyExists && (messageArguments != null) && (messageArguments.length > 0))
		{
			this.formatter.applyPattern(localizedMessage);
			localizedMessage = this.formatter.format(messageArguments);
		}
		
		return localizedMessage;
	}
	
	/**
	 * Devuelve la clave de un objeto ResourceBundleReader con nombre base baseBundleName y Locale locale.
	 * 
	 * La que usa ResourceBundleReaderFactory para los ResourceBundleReader que tiene inicializados.
	 * 
	 * @param baseBundleName
	 * @param locale
	 * @return
	 */
	public static String getBundleKey(String baseBundleName, Locale locale)
	{
		return String.format("%s_%s", baseBundleName, locale.toString());
	}
	
}
