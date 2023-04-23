package com.cm.tdbPublisher.dto;

import java.io.Serializable;

public class GenericResponse implements Serializable {
    public String msg;
    public int statusCode;
    public Object payload;
}
