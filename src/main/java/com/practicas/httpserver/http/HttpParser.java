package com.practicas.httpserver.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HttpParser {

    private BufferedReader bufferedReader;
    private InputStream inputStream;

    public HttpParser(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String getHttpMethod() throws IOException {
        this.bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        return this.bufferedReader.readLine();
    }
}
