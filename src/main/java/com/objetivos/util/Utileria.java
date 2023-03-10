package com.objetivos.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class Utileria {

    public static String guardarArchivo(MultipartFile multipart, String ruta){
        String nombreOriginal = multipart.getOriginalFilename();
        try {
            File imageFile = new File(ruta + nombreOriginal);
            System.out.println("Archivo: " + nombreOriginal);
            multipart.transferTo(imageFile);
            return nombreOriginal;
        } catch (IOException e){
            System.out.println("ERROR: " + e.getMessage());
            return null;
        }
    }
}
