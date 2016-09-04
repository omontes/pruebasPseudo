/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacionpruebas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oscar Montes
 */
public class SimulacionPruebas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        
        //String path = "./" + "NumerosJava" + "/" + "numsjava.txt";
        /*int scale = 8;*/
        
        
        //int totalnums = 1000000;
        //generarRandomsJava(5,path,totalnums);
        
        /****EJEMPLO DEL LIBRO ***/
        //String path = "./" + "NumerosJava" + "/" + "nums.txt";
        //int scale = 4;
        
        //Prueba_Promedios promedios = new Prueba_Promedios();
        //promedios.runPrueba(path,scale);
        
        //Prueba_Varianza varianza = new Prueba_Varianza();
        //varianza.runPrueba(path,scale);
        
        //Prueba_Corridas corridas = new Prueba_Corridas();
        //corridas.runPrueba(path, scale);
        
        //Prueba_HuecosDigs huecos_digitos = new Prueba_HuecosDigs();
        //huecos_digitos.runPrueba(path, scale, "NumerosJava");
        
        
        /*String path = "./" + "NumerosJava" + "/" + "huecosnums.txt";
        int scale = 4;*/
        
        /*int maxTamHueco = 4;//Los huecos empiezan en 0 hasta maxTamHueco-1
        Prueba_HuecosNums huecos_nums = new Prueba_HuecosNums();
        huecos_nums.runPrueba(path, scale, "NumerosJava",new BigDecimal("0.300")
        ,new BigDecimal("0.700"),maxTamHueco);*/
        
       
        //String path = "./" + "NumerosJava" + "/" + "pruebapoker.txt";
        //String path = "./" + "NumerosJava" + "/" + "numsjavapoker.txt";
        //Prueba_Poker poker = new Prueba_Poker();
        //poker.runPrueba(path, "NumerosJava");
        
        String path = "./" + "NumerosJava" + "/" + "pruebaseries.txt";
        int scale = 4;
        int celdas = 5; //5x5 matrix
        Prueba_Series series = new Prueba_Series();
        BigDecimal tamanocelda = new BigDecimal("0.2");
        series.runPrueba(path, "NumerosJava",scale,celdas,tamanocelda);
    }

    public static BigDecimal generateRandomBigDecimalFromRange
        (BigDecimal min, BigDecimal max, int scale) {
        BigDecimal randomBigDecimal = min.add(new BigDecimal(Math.random())
                .multiply(max.subtract(min)));
        return randomBigDecimal.setScale(scale, BigDecimal.ROUND_HALF_UP);
    }

    public static String generarRandom(int scale) {
        BigDecimal random = generateRandomBigDecimalFromRange(
                new BigDecimal(0.00).setScale(scale, BigDecimal.ROUND_HALF_UP),
                new BigDecimal(1.00).setScale(scale, BigDecimal.ROUND_HALF_UP),
                scale
        );
        return random.toString();

    }
    
    public static void generarRandomsJava(int scale, String path, int totalnums){
        BufferedWriter out = null;
        try {
            FileWriter fstream = new FileWriter(path, true);
            out = new BufferedWriter(fstream);
            for (int i = 0; i < totalnums-1; i++) {
                out.write(generarRandom(scale));
                out.newLine();
            }
            //Escribir el ultimo numero para que no quede espacio
            out.write(generarRandom(scale));
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                    Logger.getLogger(SimulacionPruebas.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        }

        
    }

}
