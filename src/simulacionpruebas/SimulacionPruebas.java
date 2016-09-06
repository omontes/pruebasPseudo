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

        
        /*String pathPython = "./" + "NumerosPython" + "/" + "randspython.txt";
        int scalePython = 8;
        probarPruebas(pathPython, scalePython, "NumerosPython");*/
        
        /*String pathJava = "./" + "NumerosJava" + "/" + "numsjava.txt";
        int scaleJava = 8;
        probarPruebas(pathJava, scaleJava, "NumerosJava");*/
        
        String pathMaxima = "./" + "NumerosMaxima" + "/" + "numerosmaxima.txt";
        int scaleMaxima = 8;
        probarPruebas(pathMaxima, scaleMaxima, "NumerosMaxima");
        
        /*String pathC = "./" + "NumerosC" + "/" + "numsc.txt";
        int scaleC = 8;
        probarPruebas(pathC, scaleC, "NumerosC");*/
        
        
        
        
        
        //probarLibro("NumerosLibro");
        
        
        /*------GENERAR NUM RANDOMS EN JAVA -----*/
        /*String path = "./" + "NumerosJava" + "/" + "numsjava.txt";
        int totalnums = 1000000;
        generarRandomsJava(16,path,totalnums);*/
        
        
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

    private static void probarLibro(String folder){
        System.out.println("*******PRUEBA PROMEDIOS*********");
        String pathPromedioVarCorridas = "./" + folder + "/" + "nums.txt";
        int scalePromedioVarCorridas = 4;
        Prueba_Promedios promedios = new Prueba_Promedios();
        promedios.runPrueba(pathPromedioVarCorridas,scalePromedioVarCorridas);
        System.out.println("");
        
        System.out.println("*******PRUEBA VARIANZA*********");
        Prueba_Varianza varianza = new Prueba_Varianza();
        varianza.runPrueba(pathPromedioVarCorridas,scalePromedioVarCorridas);
        System.out.println("");
        
        System.out.println("*******PRUEBA CORRIDAS*********");
        Prueba_Corridas corridas = new Prueba_Corridas();
        corridas.runPrueba(pathPromedioVarCorridas, scalePromedioVarCorridas);
        System.out.println("");
        
        System.out.println("*******PRUEBA HUECOS CON DIGS*********");
        Prueba_HuecosDigs huecos_digitos = new Prueba_HuecosDigs();
        huecos_digitos.runPrueba(pathPromedioVarCorridas, scalePromedioVarCorridas, folder);
        System.out.println("Ver frecuencias en: tablafrecuenciasdigs.csv");
        System.out.println("Ver tabla huecos en: tablahuecosdigs.csv");
        System.out.println("");
        
        
        String pathHuecosNums = "./" + folder + "/" + "huecosnums.txt";
        
        System.out.println("*******PRUEBA HUECOS CON NUMS*********");
        int maxTamHueco = 4;//Los huecos empiezan en 0 hasta maxTamHueco-1
        Prueba_HuecosNums huecos_nums = new Prueba_HuecosNums();
        System.out.println("RANGO [0.30,0.70]");
        huecos_nums.runPrueba(pathHuecosNums, scalePromedioVarCorridas, 
                folder ,new BigDecimal("0.300")
        ,new BigDecimal("0.700"),maxTamHueco);
        System.out.println("Ver frecuencias en: tablafrecuenciasnumsX.csv");
        System.out.println("Ver tabla huecos en: tablahuecosnumsX.csv");
        System.out.println("");
        
        System.out.println("RANGO [0.40,0.60]");
        huecos_nums.runPrueba(pathHuecosNums, scalePromedioVarCorridas, 
                folder ,new BigDecimal("0.400")
        ,new BigDecimal("0.600"),maxTamHueco);
        System.out.println("Ver frecuencias en: tablafrecuenciasnumsX.csv");
        System.out.println("Ver tabla huecos en: tablahuecosnumsX.csv");
        System.out.println("");
        
        System.out.println("RANGO [0.20,0.80]");
        huecos_nums.runPrueba(pathHuecosNums, scalePromedioVarCorridas, 
                folder ,new BigDecimal("0.200")
        ,new BigDecimal("0.800"),maxTamHueco);
        System.out.println("Ver frecuencias en: tablafrecuenciasnumsX.csv");
        System.out.println("Ver tabla huecos en: tablahuecosnumsX.csv");
        System.out.println("");
        
        System.out.println("RANGO [0.15,0.85]");
        huecos_nums.runPrueba(pathHuecosNums, scalePromedioVarCorridas, 
                folder ,new BigDecimal("0.150")
        ,new BigDecimal("0.850"),maxTamHueco);
        System.out.println("Ver frecuencias en: tablafrecuenciasnumsX.csv");
        System.out.println("Ver tabla huecos en: tablahuecosnumsX.csv");
        System.out.println("");
        
        System.out.println("*******PRUEBA POKER*********");
        String pathPoker = "./" + folder + "/" + "pruebapoker.txt";
        Prueba_Poker poker = new Prueba_Poker();
        poker.runPrueba(pathPoker, folder);
        System.out.println("Ver frecuencias en: tablafrecpoker.csv");
        System.out.println("Ver tabla pokerestadisticas.csv");
        System.out.println("");
        
        System.out.println("*******PRUEBA SERIES*********");
        String pathSeriesNums = "./" + folder + "/" + "pruebaseries.txt";
        int celdas = 5; //5x5 matrix
        int scalePoker = 8;
        Prueba_Series series = new Prueba_Series();
        BigDecimal tamanocelda = new BigDecimal("0.2");
        series.runPrueba(pathSeriesNums, folder,scalePoker,celdas,tamanocelda);
        System.out.println("Ver frecuencias en: frecuenciaseries.csv");
        System.out.println("Ver tabla tablaseries.csv");
        System.out.println("");
    }
    private static void probarPruebas(String path, int scale, String folder) {
        System.out.println("*******PRUEBA PROMEDIOS*********");
        Prueba_Promedios promedios = new Prueba_Promedios();
        promedios.runPrueba(path,scale);
        System.out.println("");
        
        System.out.println("*******PRUEBA VARIANZA*********");
        Prueba_Varianza varianza = new Prueba_Varianza();
        varianza.runPrueba(path,scale);
        System.out.println("");
        
        System.out.println("*******PRUEBA CORRIDAS*********");
        Prueba_Corridas corridas = new Prueba_Corridas();
        corridas.runPrueba(path, scale);
        System.out.println("");
        
        System.out.println("*******PRUEBA HUECOS CON DIGS*********");
        Prueba_HuecosDigs huecos_digitos = new Prueba_HuecosDigs();
        huecos_digitos.runPrueba(path, scale, folder);
        System.out.println("Ver frecuencias en: tablafrecuenciasdigs.csv");
        System.out.println("Ver tabla huecos en: tablahuecosdigs.csv");
        System.out.println("");
        
        System.out.println("*******PRUEBA HUECOS CON NUMS*********");
        int maxTamHueco = 4;//Los huecos empiezan en 0 hasta maxTamHueco-1
        Prueba_HuecosNums huecos_nums = new Prueba_HuecosNums();
        System.out.println("RANGO [0.30,0.70]");
        huecos_nums.runPrueba(path, scale, folder ,new BigDecimal("0.300")
        ,new BigDecimal("0.700"),maxTamHueco);
        System.out.println("Ver frecuencias en: tablafrecuenciasnumsX.csv");
        System.out.println("Ver tabla huecos en: tablahuecosnumsX.csv");
        System.out.println("");
        
        System.out.println("RANGO [0.40,0.60]");
        huecos_nums.runPrueba(path, scale, folder ,new BigDecimal("0.400")
        ,new BigDecimal("0.600"),maxTamHueco);
        System.out.println("Ver frecuencias en: tablafrecuenciasnumsX.csv");
        System.out.println("Ver tabla huecos en: tablahuecosnumsX.csv");
        System.out.println("");
        
        System.out.println("RANGO [0.20,0.80]");
        huecos_nums.runPrueba(path, scale, folder ,new BigDecimal("0.200")
        ,new BigDecimal("0.800"),maxTamHueco);
        System.out.println("Ver frecuencias en: tablafrecuenciasnumsX.csv");
        System.out.println("Ver tabla huecos en: tablahuecosnumsX.csv");
        System.out.println("");
        
        System.out.println("RANGO [0.15,0.85]");
        huecos_nums.runPrueba(path, scale, folder ,new BigDecimal("0.150")
        ,new BigDecimal("0.850"),maxTamHueco);
        System.out.println("Ver frecuencias en: tablafrecuenciasnumsX.csv");
        System.out.println("Ver tabla huecos en: tablahuecosnumsX.csv");
        System.out.println("");
        
        System.out.println("*******PRUEBA POKER*********");
        Prueba_Poker poker = new Prueba_Poker();
        poker.runPrueba(path, folder);
        System.out.println("Ver frecuencias en: tablafrecpoker.csv");
        System.out.println("Ver tabla pokerestadisticas.csv");
        System.out.println("");
        
        System.out.println("*******PRUEBA SERIES*********");
        int celdas = 5; //5x5 matrix
        Prueba_Series series = new Prueba_Series();
        BigDecimal tamanocelda = new BigDecimal("0.2");
        series.runPrueba(path, folder,scale,celdas,tamanocelda);
        System.out.println("Ver frecuencias en: frecuenciaseries.csv");
        System.out.println("Ver tabla tablaseries.csv");
        System.out.println("");
        
        
    }

}
