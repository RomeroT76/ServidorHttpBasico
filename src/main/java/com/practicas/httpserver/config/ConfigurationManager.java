package com.practicas.httpserver.config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.practicas.httpserver.exceptions.HttpConfigurationException;
import com.practicas.httpserver.util.Json;

public class ConfigurationManager 
{
    private static ConfigurationManager cManager;
    private static Configuration currentConfiguration;

    private ConfigurationManager() {}

    public static ConfigurationManager getinstance() 
    {
        if (cManager == null) {
            cManager = new ConfigurationManager();
         }
        return cManager;
    }

    /**
     * Usado para cargar el archivo de configuracion por la ruta proporcionada como argumento.
     * @throws IOException 
     */
    public void loadConfigurtionFile(String filePath)
    {
        FileReader fReader;
        try {
            fReader = new FileReader(filePath);
            StringBuffer sBuffer = new StringBuffer();
            int i;
            while ((i = fReader.read()) != -1)
            {
                sBuffer.append((char)i);
            }
            JsonNode config = Json.parse(sBuffer.toString());
            currentConfiguration = Json.fromJson(config, Configuration.class);
            fReader.close();
        } catch (FileNotFoundException e) {
            throw new HttpConfigurationException(e);
        } catch (IOException e) {
            throw new HttpConfigurationException("Error al analizar el archivo de configuracion", e);
        }
    }

    /**
     * Devuelve la configuracion actual.
     */
    public Configuration getCurrentConfiguration()
    {
        if (currentConfiguration == null)
        {
            throw new HttpConfigurationException("Configuracion Actual no establecida");
        }
        return currentConfiguration;
    }
}
