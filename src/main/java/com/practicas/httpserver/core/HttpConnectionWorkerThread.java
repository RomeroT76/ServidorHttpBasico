package com.practicas.httpserver.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpConnectionWorkerThread extends Thread {

    private Socket socket;
    private InputStream iStream;
    private OutputStream oStream;
    private final String CRLF = "\n\r";
    private final static Logger LOGGER = LoggerFactory.getLogger(HttpConnectionWorkerThread.class);

    public HttpConnectionWorkerThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            this.iStream = socket.getInputStream();
            this.oStream = socket.getOutputStream();
            String html = "<html><head><title>Http Server</title></head><body><h1>Respuesta del servidor exitosa</h1></body></html>";
            String response = "HTTP/1.1 200 OK" + CRLF + "Content-Length: " + html.getBytes().length + CRLF + CRLF
                    + html + CRLF + CRLF;
            this.oStream.write(response.getBytes());
        } catch (IOException e) {
            LOGGER.error("Err => ", e);
        } finally {
            try {
                if (this.iStream != null && this.oStream != null && this.socket != null) {
                    this.iStream.close();
                    this.oStream.close();
                    this.socket.close();
                    LOGGER.info("Conexion Finalizada......");
                }
            } catch (IOException e) { }
        }
    }
}
