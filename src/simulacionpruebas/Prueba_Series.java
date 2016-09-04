/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacionpruebas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
public class Prueba_Series {
    BigDecimal chiTotal = BigDecimal.ZERO;
    int cantNums = 0;
    int[][] celdas;
    
    public Prueba_Series(){}
    
    public void runPrueba(String path, String folder, 
            int scale,int Celdas, BigDecimal tamanocelda) {
        
        celdas = new int [Celdas][Celdas];
        
        
        pruebaSeries(path,tamanocelda,scale,Celdas);
        
        System.out.println("TABLA DE CELDAS");
        for (int i = 0; i < celdas.length; i++) {
            for (int j = 0; j < celdas.length; j++) {
                System.out.print(celdas[i][j]+" ");
            }
            System.out.println("");
        }
        
        System.out.println("Cant total nums: " + cantNums);
        
        writeTablaSeries(folder);
        
        
        System.out.println("");
        
        writeTablaFrecuencias(folder,scale);
        
        
        System.out.println("Sumatoria chi: " + chiTotal);
         
        //Con la tabla del libro con alpha=0.05 y 24 grados --> 36.4150 
        BigDecimal chiTabla = new BigDecimal("36.4150");
        if (chiTotal.compareTo(chiTabla) <= 0) {
           System.out.println("PASO LA PRUEBA PARA alpha=0.05 y n grados=24");
        }
     }

    private void pruebaSeries(String path, BigDecimal tamanocelda, int scale
    ,int longMatrix) {
        BufferedReader input = null;

        try {

            FileReader fstream = new FileReader(path);
            input = new BufferedReader(fstream);
            String sCurrentLine = "";
            

            BigDecimal numX = new BigDecimal
            (sCurrentLine = input.readLine());
            cantNums++;
           
            
            while ((sCurrentLine = input.readLine()) != null) {
                cantNums++;
                BigDecimal numY = new BigDecimal(sCurrentLine);
                
                int coordy = numX.divide(tamanocelda,scale,
                            RoundingMode.HALF_UP).abs().intValue();
                
                
                
                int coordx = numY.divide(tamanocelda,scale,
                            RoundingMode.HALF_UP).abs().intValue();
                
                              
                
                numX = numY;
                
                coordx=(longMatrix-1)-coordx;
                celdas[coordx][coordy]++;
                
                
                

            }

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ex) {
                    Logger.getLogger(Prueba_Series.class.getName()).
                            log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void writeTablaSeries(String folder) {
        String path = "./" + folder + "/" + "tablaseries.csv";

        BufferedWriter out = null;
        try {
            FileWriter fstream = new FileWriter(path, true);
            out = new BufferedWriter(fstream);
            for (int i = 0; i < celdas.length; i++) {
                for (int j = 0; j < celdas.length; j++) {
                    out.write(celdas[i][j]+",");
                }
                out.newLine();
        }
        }
            

        catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                    Logger.getLogger(Prueba_Series.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void writeTablaFrecuencias(String folder, int scale) {
        String path = "./" + folder + "/" + "frecuenciaseries.csv";

        BufferedWriter out = null;
        try {
            FileWriter fstream = new FileWriter(path, true);
            out = new BufferedWriter(fstream);
            out.write("Celda,f0,chi");
            out.newLine();
            int size = celdas.length;
            int contador =1;
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    int f0 = celdas[i][j];

                    BigDecimal fe = new BigDecimal(cantNums-1).divide(
                            new BigDecimal(size*size),scale,RoundingMode.HALF_UP);
                //System.out.println("fe es: "+fe.toString());

                    BigDecimal chi_aux = (new BigDecimal(f0).subtract(fe)).pow(2).
                            setScale(scale, RoundingMode.HALF_UP);
                    BigDecimal chi = chi_aux.divide(fe, scale, RoundingMode.HALF_UP);
                //System.out.println("chi es: "+chi.toString());

                    //System.out.println(i);
                    out.write(contador + "," + f0 + "," + chi.toString());
                    out.newLine();
                    chiTotal = chiTotal.add(chi);
                    contador++;
                }

            }

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                    Logger.getLogger(Prueba_Series.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
