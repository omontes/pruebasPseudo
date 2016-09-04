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
                System.out.print(celdas[i][j]);
            }
            System.out.println("");
        }
        
        //System.out.println("Cant total nums: " + cantNums);
        
        //writeTablaSeries(folder);
        
        
        System.out.println("");
        
        //writeTablaFrecuencias(folder);
        
        
        System.out.println("Sumatoria chi: " + chiTotal);
         
        //Con la tabla del libro con alpha=0.05 y 24 grados --> 36.4150 
        /*BigDecimal chiTabla = new BigDecimal("36.4150");
        if (chiTotal.compareTo(chiTabla) <= 0) {
           System.out.println("PASO LA PRUEBA PARA alpha=0.05 y n grados=24");
        }*/
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
                
                
                /*System.out.println("");
                System.out.println("Numx: "+numX.toString());
                System.out.println("Numy: "+numY.toString());*/
               
                
                numX = numY;
                
                coordx=(longMatrix-1)-coordx;
                celdas[coordx][coordy]++;
                //System.out.println("coordx: " + coordx);
                //System.out.println("coordy: " + coordy);
                
                

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
}
