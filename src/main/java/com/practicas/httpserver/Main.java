package com.practicas.httpserver;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.practicas.httpserver.config.Configuration;
import com.practicas.httpserver.config.ConfigurationManager;
import com.practicas.httpserver.core.ServerListenerThread;

public class Main {
    private final static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        LOGGER.info("\"Running server....");

        ConfigurationManager.getinstance().loadConfigurtionFile("src\\main\\resources\\http.json");
        Configuration conf = ConfigurationManager.getinstance().getCurrentConfiguration();
        LOGGER.info("-----configuration loaded----");
        LOGGER.info("-- Port: " + conf.getPort());
        LOGGER.info("-- WebRoot: " + conf.getWebRoot());

        try {
            ServerListenerThread sListenerThread = new ServerListenerThread(conf.getPort(), conf.getWebRoot());
            sListenerThread.start();
            LOGGER.info("----- Started -----");
        } catch (IOException e) {
            LOGGER.error("Err => ", e);
        }
    }
}
