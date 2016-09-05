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
public class Prueba_Varianza {
    
    int cantNums = 0;

    public Prueba_Varianza() {
    }

    public void runPrueba(String path, int scale) {
        /*metodo para la lectura del archivo con numeros*/
        BufferedReader input = null;
        BigDecimal promedio= calcularPromedio(path,scale);
        
        try {

            FileReader fstream = new FileReader(path);
            input = new BufferedReader(fstream);
            
            
            String sCurrentLine = "";
            //input.readLine();//LA PRIMERA LINEA ES VACIA
            BigDecimal varianzaUniforme
                    = new BigDecimal("0.08333333333");
            

            
            //itera todos los numeros del txt para encontrar s^2
            BigDecimal s2 = BigDecimal.ZERO;
            sCurrentLine = "";
            while ((sCurrentLine = input.readLine()) != null) {
                BigDecimal X =new BigDecimal(sCurrentLine);
                BigDecimal Y = (X.subtract(promedio)).pow(2).
                        setScale(scale, RoundingMode.HALF_UP);
                s2=s2.add(Y.divide(new BigDecimal(cantNums-1),
                        scale, RoundingMode.HALF_UP));
                
            };
            
            //Calcular xi cuadrado
            BigDecimal denominador=new BigDecimal(cantNums-1).multiply(s2);
            BigDecimal chicuadrado = denominador.
                    divide(varianzaUniforme,scale,
                    RoundingMode.HALF_UP);
            
            System.out.println("S^2 es: " + s2.toString());
            System.out.println("Denominador es: " + denominador.toString());
            System.out.println("Chi2 es: " + chicuadrado.toString());
            
           
            //Prueba con limites superior e inferior para alfa=0.05
            BigDecimal limInferior = new BigDecimal("16.0471");
            BigDecimal limSuperior = new BigDecimal("45.7223");

            if (chicuadrado.compareTo(limSuperior) <= 0
                    && chicuadrado.compareTo(limInferior) >= 0) {
                System.out.println("PASO LA PRUEBA CON alpha=0.05 y "
                        + "29 grados de libertad");
            }

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
    }

    private BigDecimal calcularPromedio(String path, int scale) {
        BufferedReader input = null;
        BigDecimal promedio = BigDecimal.ZERO;
        try {

            FileReader fstream = new FileReader(path);
            input = new BufferedReader(fstream);
            
            
            String sCurrentLine = "";
            //input.readLine();//LA PRIMERA LINEA ES VACIA
            
            BigDecimal sumNum = BigDecimal.ZERO;
            
            //itera todos los numeros del txt para sumarlos por el promedio
            while ((sCurrentLine = input.readLine()) != null) {
                //System.out.println(sCurrentLine);
                sumNum = sumNum.add(new BigDecimal(sCurrentLine));
                cantNums++;
            };
            System.out.println("Cant total nums: " + cantNums);
            System.out.println("Total Sumado: " + sumNum.toString());

            promedio = sumNum.divide(
                    new BigDecimal(cantNums), scale, RoundingMode.HALF_UP);
            System.out.println("Promedio es: " + promedio.toString());
            
            return promedio;

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
        return promedio;
    }
}
