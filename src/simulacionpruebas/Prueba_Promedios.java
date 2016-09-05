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
public class Prueba_Promedios {
    
    public Prueba_Promedios(){};
    
    public void runPrueba(String path, int scale){
        /*metodo para la lectura del archivo con numeros*/
        BufferedReader input = null;
        try {

            
            FileReader fstream = new FileReader(path);
            input = new BufferedReader(fstream);
            String sCurrentLine = "";
            //input.readLine();//LA PRIMERA LINEA ES VACIA
            int cantNums = 0;
            BigDecimal sumNum = BigDecimal.ZERO;
            BigDecimal promedioUniforme = new BigDecimal("0.50");
            BigDecimal desvestandarUniforme = 
                    sqrt(new BigDecimal("0.08333333333"));
            
            //itera todos los numeros del txt
            while ((sCurrentLine = input.readLine()) != null) {
                //System.out.println(sCurrentLine);
                sumNum=sumNum.add(new BigDecimal(sCurrentLine));
                cantNums++;
            };
            System.out.println("Cant total nums: "+cantNums);
            System.out.println("Total Sumado: "+sumNum.toString());
            
            BigDecimal promedio =sumNum.divide(
                    new BigDecimal(cantNums),scale,RoundingMode.HALF_UP);
            System.out.println("Promedio es: "+promedio.toString());
            
            BigDecimal restProms = promedio.subtract(promedioUniforme);
            BigDecimal sqrtN = sqrt(new BigDecimal(cantNums));
            BigDecimal divSqrts = desvestandarUniforme.divide(sqrtN,scale,
                    RoundingMode.HALF_UP);
            BigDecimal z0 = restProms.divide(divSqrts,scale,
                    RoundingMode.HALF_UP);
            System.out.println("Z0 es: "+ z0.toString());
            //Con alpha = 0.025 y n--> infinito la tabla t-student da 1.645
            BigDecimal tstudent = new BigDecimal("1.960");
            
            //Prueba de promedios seria
            if (z0.compareTo(tstudent) <= 0
                    && z0.compareTo(tstudent.negate()) >= 0) {
                System.out.println("PASO LA PRUEBA CON alpha=0.025 y"
                        + " t-student 1.645 para n infinito");
            }
            
            //Prueba con limites superior e inferior [Intervalo]
            
            BigDecimal limInferior = promedioUniforme.
                    subtract(tstudent.multiply(divSqrts)).
                    setScale(scale,RoundingMode.HALF_UP);
            BigDecimal limSuperior = promedioUniforme.
                    add(tstudent.multiply(divSqrts)).
                    setScale(scale,RoundingMode.HALF_UP);
            System.out.println("lim inferior: "+limInferior.toString());
            System.out.println("lim superior: "+limSuperior.toString());
            
            if (promedio.compareTo(limSuperior) <= 0
                    && promedio.compareTo(limInferior) >= 0) {
                System.out.println("PASO LA PRUEBA UTILIZANDO LOS LIMITES");
            }
            
            
            
            
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ex) {
                    Logger.getLogger(SimulacionPruebas.class.getName()).
                            log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    /*Solo permite hasta 32 digitos tomado de 
    http://stackoverflow.com/questions/13649703/square-root-of-bigdecimal-in-java*/
    private BigDecimal sqrt(BigDecimal value) {
        BigDecimal x = new BigDecimal(Math.sqrt(value.doubleValue()));
        return x.add(new BigDecimal(value.subtract(x.multiply(x)).doubleValue() 
                / (x.doubleValue() * 2.0)));
    }
}
