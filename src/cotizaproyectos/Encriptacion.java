/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cotizaproyectos;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 *
 * @author Idalia
 */
public class Encriptacion {
    
    public String recibirCadena(String cadena){
        
        String cadenaEncriptada = "";
        try {
            System.out.println("Cadena original > "+cadena);
            cadenaEncriptada = encriptar(cadena);
            System.out.println("Cadena encriptada > "+cadenaEncriptada);
        } catch (UnsupportedEncodingException uee) {
            System.out.println("Ups!! > "+uee);
        }
        
        return cadenaEncriptada;
    }
    
    public String recibirCadenaEncriptada(String cadenaEncriptada){
        
        String cadenaDesencriptada = "";
        try {
              cadenaDesencriptada = desencriptar(cadenaEncriptada);
            System.out.println("Cadena desencriptada > "+cadenaDesencriptada);

        } catch (UnsupportedEncodingException uee) {
            System.out.println("Ups!! > "+uee);
        }
        
        return cadenaDesencriptada;
    }
    
     private static String encriptar(String s) throws UnsupportedEncodingException{
        return Base64.getEncoder().encodeToString(s.getBytes("utf-8"));
    }
    
    private static String desencriptar(String s) throws UnsupportedEncodingException{
        byte[] decode = Base64.getDecoder().decode(s.getBytes());
        return new String(decode, "utf-8");
    }
    
   
}
