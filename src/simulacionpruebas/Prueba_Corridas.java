/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacionpruebas;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oscar Montes
 */
public class Prueba_Corridas {
    int cantNums = 0;
    
    public Prueba_Corridas(){};
    
    public void runPrueba(String path, int scale) {
                
        int h = calcularCorridas(path);
        BigDecimal esperanza= calcularEsperanza(scale);
        BigDecimal varianza= calcularVarianza(scale);
        BigDecimal z0 = (new BigDecimal(h).subtract(esperanza)).
                divide(varianza,scale,RoundingMode.HALF_UP);
        System.out.println("Esperanza E(h): "+esperanza.toString());
        System.out.println("Varianza s(h): "+varianza.toString());
        System.out.println("z0: " + z0.toString());
        //Con alpha = 0.05 y n--> infinito la tabla normal da 1.960
        BigDecimal normal = new BigDecimal("1.960");

        //Prueba de promedios seria
        if (z0.compareTo(normal) <= 0
                && z0.compareTo(normal.negate()) >= 0) {
            System.out.println("PASO LA PRUEBA");
        }
    }

    private int calcularCorridas(String path) {
        BufferedReader input = null;
        int corridas = 0;
        try {

            FileReader fstream = new FileReader(path);
            input = new BufferedReader(fstream);
            
            
            String sCurrentLine = "";
            //input.readLine();//LA PRIMERA LINEA ES VACIA
            String symbol_before = "*";
            String symbol_next = "";
            BigDecimal numBefore = new BigDecimal(input.readLine());
            cantNums++;
            
            //itera todos los numeros del txt para sumarlos por el promedio
            while ((sCurrentLine = input.readLine()) != null) {
                //System.out.println(sCurrentLine);
                BigDecimal nextNum = new BigDecimal(sCurrentLine);
                if(numBefore.compareTo(nextNum)>0){
                    symbol_next="-";
                }
                else{
                    symbol_next="+";
                }
                if(!symbol_before.equals(symbol_next)){
                   corridas++; 
                    
                }
                numBefore=nextNum;
                symbol_before= symbol_next;
                cantNums++;
            }
                
            
            System.out.println("Cant total nums: " + cantNums);
            System.out.println("Total corridas: " + corridas);

            
            
            return corridas;

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ex) {
                    Logger.getLogger(Prueba_Varianza.class.getName()).
                            log(Level.SEVERE, null, ex);
                }
            }
        }
        return corridas;
    }

    private BigDecimal calcularEsperanza(int scale) {
        BigDecimal esperanza = new BigDecimal(2*cantNums-1).
                divide(new BigDecimal(3),scale,RoundingMode.HALF_UP);
        return esperanza;
    }

    private BigDecimal calcularVarianza(int scale) {
        BigDecimal denomidador = new BigDecimal(16*cantNums-29).
                divide(new BigDecimal(90),scale,RoundingMode.HALF_UP);
        BigDecimal varianza = sqrt(denomidador).setScale(scale,
                RoundingMode.HALF_UP);
        return varianza;
    }
    
    /*Solo permite hasta 32 digitos tomado de 
    http://stackoverflow.com/questions/13649703/square-root-of-bigdecimal-in-java*/
    private BigDecimal sqrt(BigDecimal value) {
        BigDecimal x = new BigDecimal(Math.sqrt(value.doubleValue()));
        return x.add(new BigDecimal(value.subtract(x.multiply(x)).doubleValue() 
                / (x.doubleValue() * 2.0)));
    }
    
}
