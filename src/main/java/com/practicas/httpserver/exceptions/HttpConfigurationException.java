package com.practicas.httpserver.exceptions;

public class HttpConfigurationException extends RuntimeException
{
    public HttpConfigurationException() {}

    public HttpConfigurationException(String msg) 
    {
        super(msg);
    }

    public HttpConfigurationException(String msg, Throwable cause) 
    {
        super(msg, cause);
    }

    public HttpConfigurationException(Throwable cause) 
    {
        super(cause);
    }
}
