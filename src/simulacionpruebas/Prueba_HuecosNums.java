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
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Oscar Montes
 */
public class Prueba_HuecosNums {
    
    //Solo va a contemplar huecos de tamano 0,1,2,3
    int[] huecos;
    
    int totalHuecos = 0;
    BigDecimal chiTotal = BigDecimal.ZERO;
       
    
    
    
    int cantNums = 0;
    
    public Prueba_HuecosNums(){}
    
    public void runPrueba(String path, int scale,
            String folder, BigDecimal inf, BigDecimal sup, int maxTamHueco) {
        
        huecos = new int[maxTamHueco];
        obtenerHuecos(path, inf, sup, maxTamHueco-1);
        System.out.println("Cant total nums: " + cantNums);
        System.out.println("Cant total huecos: " + totalHuecos);
        
        System.out.println("TABLA DE FRECUENCIAS OBSERVADAS");
        for (int i = 0; i < huecos.length; i++) {
            System.out.println("h" + i + " --> " + huecos[i]);
        }
        String rango = inf.toString()+"-"+sup.toString();
        writeTablaHuecos(folder,maxTamHueco,rango);
         
        System.out.println("");
        wrtieTablaFrecuencias(folder,scale,maxTamHueco,sup.subtract(inf),rango);
        System.out.println("Sumatoria chi: " + chiTotal);
         
         //Con la tabla del libro con alpha=0.005 y 3 grados --> 7.8147 
         BigDecimal chiTabla = new BigDecimal("7.8147");
         if (chiTotal.compareTo(chiTabla) <= 0) {
            System.out.println("PASO LA PRUEBA PARA alpha=0.05 y n grados=3");
        }
     }

    private void obtenerHuecos(String path, BigDecimal inf, BigDecimal sup,
            int maxHueco) {
        BufferedReader input = null;

        try {

            FileReader fstream = new FileReader(path);
            input = new BufferedReader(fstream);
            String sCurrentLine = "";
            
            //CONTROL VARIABLES
            int contadorHuecos = 0; 
            boolean firstRange = false;

            while ((sCurrentLine = input.readLine()) != null) {
                cantNums++;
                BigDecimal currentNum = new BigDecimal(sCurrentLine);
                if (currentNum.compareTo(sup) <= 0
                        && currentNum.compareTo(inf) >= 0) {
                    if (!firstRange) {
                        firstRange = true;
                        continue;
                    } else {
                        if(contadorHuecos<=maxHueco-1){
                            huecos[contadorHuecos]++;
                            totalHuecos++;
                        }
                        else{
                            huecos[maxHueco]++;
                            totalHuecos++;
                        }

                        contadorHuecos = 0;
                    }
                }

                else  {
                    if (firstRange){
                    contadorHuecos++;}
                }
                

            }

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ex) {
                    Logger.getLogger(Prueba_HuecosNums.class.getName()).
                            log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void writeTablaHuecos(String folder, int maxHuecos, String rango) {
        String path = "./" + folder + "/" + "tablahuecosnums"+rango+".csv";

        BufferedWriter out = null;
        try {
            FileWriter fstream = new FileWriter(path, true);
            out = new BufferedWriter(fstream);
            out.write("Tamano Hueco,f0");
            out.newLine();
            for (int i = 0; i < maxHuecos; i++) {
                out.write(i + "," + huecos[i]);
                out.newLine();
            }
            out.write("Total" + "," + totalHuecos);
            out.newLine();

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                    Logger.getLogger(Prueba_HuecosNums.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        }
    }


    private void wrtieTablaFrecuencias(String folder, int scale, int maxHueco,
            BigDecimal t, String rango) {
         String path = "./" + folder + "/" + "tablafrecuenciasnums"+rango+".csv";

        BufferedWriter out = null;
        try {
            FileWriter fstream = new FileWriter(path, true);
            out = new BufferedWriter(fstream);
            out.write("Huecos,f0,pe,fe,chi");
            out.newLine();
            for (int i = 0; i < maxHueco-1; i++) {
                int f0 = huecos[i];
                this.escribirResult(f0,i,scale,out,t);
                
            }
            //PARA CALCULAR EL ULTIMO HUECO 
            this.escribirUltimo(huecos[maxHueco-1],maxHueco-1,scale,out,t);

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                    Logger.getLogger(Prueba_HuecosNums.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /*contempla la formula de p(x)= (1-t)^x */
    private void escribirUltimo(int f0, int i, int scale, BufferedWriter out,
            BigDecimal t){
        try {
            BigDecimal pe = (BigDecimal.ONE.subtract(t)).pow(i).
                    setScale(scale,RoundingMode.HALF_UP);
            //System.out.println("pe es: "+pe.toString());
            
            BigDecimal fe = pe.multiply(new BigDecimal(totalHuecos)).
                    setScale(scale,RoundingMode.HALF_UP);
            //System.out.println("fe es: "+fe.toString());
            
            BigDecimal chi_aux = (new BigDecimal(f0).subtract(fe)).pow(2).
                    setScale(scale,RoundingMode.HALF_UP);
            BigDecimal chi = chi_aux.divide(fe,scale,RoundingMode.HALF_UP);
            //System.out.println("chi es: "+chi.toString());
            
            //System.out.println(i);
            out.write(i + "," + f0 + "," + pe.toString()+"," +fe.toString()
                    +","+ chi.toString());
            out.newLine();
            chiTotal=chiTotal.add(chi);
        } catch (IOException ex) {
            Logger.getLogger(Prueba_HuecosNums.
                    class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    private void escribirResult(int f0, int i, int scale, BufferedWriter out, 
            BigDecimal t) {
        try {
            BigDecimal pe = t.
                    multiply((BigDecimal.ONE.subtract(t)).pow(i)).
                    setScale(scale,RoundingMode.HALF_UP);
            //System.out.println("pe es: "+pe.toString());
            
            BigDecimal fe = pe.multiply(new BigDecimal(totalHuecos)).
                    setScale(scale,RoundingMode.HALF_UP);
            //System.out.println("fe es: "+fe.toString());
            
            BigDecimal chi_aux = (new BigDecimal(f0).subtract(fe)).pow(2).
                    setScale(scale,RoundingMode.HALF_UP);
            BigDecimal chi = chi_aux.divide(fe,scale,RoundingMode.HALF_UP);
            //System.out.println("chi es: "+chi.toString());
            
            //System.out.println(i);
            out.write(i + "," + f0 + "," + pe.toString()+"," +fe.toString()
                    +","+ chi.toString());
            out.newLine();
            chiTotal=chiTotal.add(chi);
        } catch (IOException ex) {
            Logger.getLogger(Prueba_HuecosNums.
                    class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    
}
