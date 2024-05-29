package com.example.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * author:Ben
 */
@Data
@AllArgsConstructor
public class Invocation implements Serializable {
    private String InterfaceName;
    private String methodName;
    private Class[] parameterTypes;
    private Object[] parameters;
//    private String version;

}
