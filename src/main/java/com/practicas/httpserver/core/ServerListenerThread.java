package com.practicas.httpserver.core;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServerListenerThread extends Thread {

    private int port;
    private String webRoot;
    private ServerSocket sSocket;
    private Socket socket;
    private final Logger LOGGER; 
    private HttpConnectionWorkerThread hConnectionWorkerThread;

    public ServerListenerThread(int port, String webRoot) throws IOException {
        this.port = port;
        this.webRoot = webRoot;
        this.sSocket = new ServerSocket(this.port);
        this.LOGGER = LoggerFactory.getLogger(ServerListenerThread.class);
    }

    @Override
    public void run() {
        try {
            while (this.sSocket.isBound() && !this.sSocket.isClosed()) {
                this.socket = this.sSocket.accept();
                LOGGER.info("Conexion aceptada exitosamente: " + socket.getInetAddress());

                this.hConnectionWorkerThread = new HttpConnectionWorkerThread(socket);
                this.hConnectionWorkerThread.start();
            }
        } catch (IOException e) {
            LOGGER.error("Err => ", e);
        } finally {
            if (this.sSocket != null) {
                try {
                    this.sSocket.close();
                    LOGGER.info("Ejecucion Finalizada.....");
                } catch (IOException e) { }
            }
        }

    }

    public String getWebRoot() {
        return webRoot;
    }

    public void setWebRoot(String webRoot) {
        this.webRoot = webRoot;
    }

}
