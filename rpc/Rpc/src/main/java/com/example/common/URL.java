package com.example.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class URL implements Serializable {
String hostname;
Integer port;

}
