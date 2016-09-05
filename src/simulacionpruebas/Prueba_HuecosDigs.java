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
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import static simulacionpruebas.SimulacionPruebas.generarRandom;

/**
 *
 * @author Oscar Montes
 */
public class Prueba_HuecosDigs {
    int[] huecos_zero = new int[21];
    int[] huecos_one = new int[21];
    int[] huecos_two = new int[21];
    int[] huecos_three = new int[21];
    int[] huecos_four = new int[21];
    int[] huecos_five = new int[21];
    int[] huecos_six = new int[21];
    int[] huecos_seven = new int[21];
    int[] huecos_eight = new int[21];
    int[] huecos_nine = new int[21];
    
    int totalHuecos = 0;
    BigDecimal chiTotal = BigDecimal.ZERO;
       
    
    
    
    int cantNums = 0;
    public Prueba_HuecosDigs(){};
    
    public void runPrueba(String path, int scale, String folder) {
         obtenerHuecos(path);
         writeTablaHuecos(folder);
         System.out.println("Cant total nums: " + cantNums);
         System.out.println("Cant total huecos: " + totalHuecos);
         wrtieTablaFrecuencias(folder,scale);
         System.out.println("Sumatoria chi: " + chiTotal);
         
         //Con la tabla del libro con alpha=0.005 y 100 grados --> 140.17 
         BigDecimal chiTabla = new BigDecimal("140.17");
         if (chiTotal.compareTo(chiTabla) <= 0) {
            System.out.println("PASO LA PRUEBA con alpha=0.005 y"
                    + " 100 grados --> 140.17");
        }
     }
     
     private void obtenerHuecos(String path) {
        BufferedReader input = null;
        
        try {

            FileReader fstream = new FileReader(path);
            input = new BufferedReader(fstream);
            
            
            String sCurrentLine = "";
            //input.readLine();//LA PRIMERA LINEA ES VACIA
                     
                      
            //CONTROL VARIABLES
            int cont_zero = 0;
            int cont_one = 0;
            int cont_two = 0;
            int cont_three = 0;
            int cont_four = 0;
            int cont_five = 0;
            int cont_six = 0;
            int cont_seven = 0;
            int cont_eight = 0;
            int cont_nine = 0;
            
            boolean firstZero = false;
            boolean firstOne = false;
            boolean firstTwo = false;
            boolean firstThree = false;
            boolean firstFour = false;
            boolean firstFive = false;
            boolean firstSix = false;
            boolean firstSeven = false;
            boolean firstEight = false;
            boolean firstNine = false;
           
            
            while ((sCurrentLine = input.readLine()) != null) {
                cantNums++;
                String[] numberarray = sCurrentLine.split("\\.");
                char[] digitsarray = numberarray[1].toCharArray();
                for (int i = 0; i < digitsarray.length; i++) {
                    char digit = digitsarray[i];
                    if (digit == '0') {
                        if (firstOne) {cont_one++;}
                        if (firstTwo) {cont_two++;}
                        if (firstThree) {cont_three++;}
                        if (firstFour) {cont_four++;}
                        if (firstFive) {cont_five++;}
                        if (firstSix) {cont_six++;}
                        if (firstSeven) {cont_seven++;}
                        if (firstEight) {cont_eight++;}
                        if (firstNine) {cont_nine++;}
                        if (!firstZero) {
                            firstZero = true;
                            continue;
                        } else {
                            if (cont_zero <= 20) {
                                huecos_zero[cont_zero]++;
                            } else {
                                //System.out.println("Para el dig 0 hueco de: " + cont_zero);
                                totalHuecos++;
                            }
                            cont_zero = 0;
                            continue;
                        }
                        
                    } 
                    else if (digit == '1') {
                        if (firstZero) {cont_zero++;}
                        if (firstTwo) {cont_two++;}
                        if (firstThree) {cont_three++;}
                        if (firstFour) {cont_four++;}
                        if (firstFive) {cont_five++;}
                        if (firstSix) {cont_six++;}
                        if (firstSeven) {cont_seven++;}
                        if (firstEight) {cont_eight++;}
                        if (firstNine) {cont_nine++;}
                        
                        if (!firstOne) {
                            firstOne = true;
                            continue;
                        } else {
                            if (cont_one <= 20) {
                                huecos_one[cont_one]++;
                            } else {
                                //System.out.println("Para el dig 1 hueco de: "+cont_one);
                                totalHuecos++;
                            }
                            cont_one = 0;
                            continue;
                        }
                    } else if (digit == '2') {
                        
                        if (firstZero) {cont_zero++;}
                        if (firstOne) {cont_one++;}
                        if (firstThree) {cont_three++;}
                        if (firstFour) {cont_four++;}
                        if (firstFive) {cont_five++;}
                        if (firstSix) {cont_six++;}
                        if (firstSeven) {cont_seven++;}
                        if (firstEight) {cont_eight++;}
                        if (firstNine) {cont_nine++;}
                        
                        if (!firstTwo) {
                            firstTwo = true;
                            continue;
                        } else {
                            if (cont_two <= 20) {
                                huecos_two[cont_two]++;
                            } else {
                                //System.out.println("Para el dig 2 hueco de: "+cont_two);
                                totalHuecos++;
                            }
                            cont_two = 0;
                            continue;
                        }
                        
                    } else if (digit == '3') {
                        if (firstZero) {cont_zero++;}
                        if (firstOne) {cont_one++;}
                        if (firstTwo) {cont_two++;}
                        if (firstFour) {cont_four++;}
                        if (firstFive) {cont_five++;}
                        if (firstSix) {cont_six++;}
                        if (firstSeven) {cont_seven++;}
                        if (firstEight) {cont_eight++;}
                        if (firstNine) {cont_nine++;}
                        
                        if (!firstThree) {
                            firstThree = true;
                            continue;
                        } else {
                            if (cont_three <= 20) {
                                huecos_three[cont_three]++;
                            } else {
                                //System.out.println("Para el dig 3 hueco de: "+cont_three);
                                totalHuecos++;
                            }
                            cont_three = 0;
                            continue;
                        }
                    } else if (digit == '4') {
                        if (firstZero) {cont_zero++;}
                        if (firstOne) {cont_one++;}
                        if (firstTwo) {cont_two++;}
                        if (firstThree) {cont_three++;}
                        if (firstFive) {cont_five++;}
                        if (firstSix) {cont_six++;}
                        if (firstSeven) {cont_seven++;}
                        if (firstEight) {cont_eight++;}
                        if (firstNine) {cont_nine++;}
                        
                        if (!firstFour) {
                            firstFour = true;
                            continue;
                        } else {
                            if (cont_four <= 20) {
                                huecos_four[cont_four]++;
                            } else {
                                //System.out.println("Para el dig 4 hueco de: "+cont_four);
                                totalHuecos++;
                            }
                            cont_four = 0;
                            continue;
                        }
                    } else if (digit == '5') {
                        if (firstZero) {cont_zero++;}
                        if (firstOne) {cont_one++;}
                        if (firstTwo) {cont_two++;}
                        if (firstThree) {cont_three++;}
                        if (firstFour) {cont_four++;}
                        if (firstSix) {cont_six++;}
                        if (firstSeven) {cont_seven++;}
                        if (firstEight) {cont_eight++;}
                        if (firstNine) {cont_nine++;}
                        
                        if (!firstFive) {
                            firstFive = true;
                            continue;
                        } else {
                            if (cont_five <= 20) {
                                huecos_five[cont_five]++;
                            } else {
                                //System.out.println("Para el dig 5 hueco de: "+cont_five);
                                totalHuecos++;
                            }
                            cont_five = 0;
                            continue;
                        }
                    } else if (digit == '6') {
                        if (firstZero) {cont_zero++;}
                        if (firstOne) {cont_one++;}
                        if (firstTwo) {cont_two++;}
                        if (firstThree) {cont_three++;}
                        if (firstFour) {cont_four++;}
                        if (firstFive) {cont_five++;}
                        if (firstSeven) {cont_seven++;}
                        if (firstEight) {cont_eight++;}
                        if (firstNine) {cont_nine++;}
                        
                        if (!firstSix) {
                            firstSix = true;
                            continue;
                        } else {
                            if (cont_six <= 20) {
                                huecos_six[cont_six]++;
                            } else {
                                //System.out.println("Para el dig 6 hueco de: "+cont_six);
                                totalHuecos++;
                            }
                            cont_six = 0;
                            continue;
                        }
                    } else if (digit == '7') {
                        if (firstZero) {cont_zero++;}
                        if (firstOne) {cont_one++;}
                        if (firstTwo) {cont_two++;}
                        if (firstThree) {cont_three++;}
                        if (firstFour) {cont_four++;}
                        if (firstFive) {cont_five++;}
                        if (firstSix) {cont_six++;}
                        if (firstEight) {cont_eight++;}
                        if (firstNine) {cont_nine++;}
                        
                        if (!firstSeven) {
                            firstSeven = true;
                            continue;
                        } else {
                            if (cont_seven <= 20) {
                                huecos_seven[cont_seven]++;
                            } else {
                                //System.out.println("Para el dig 7 hueco de: "+cont_seven);
                                totalHuecos++;
                            }
                            cont_seven = 0;
                            continue;
                        }
                    } else if (digit == '8') {
                        if (firstZero) {cont_zero++;}
                        if (firstOne) {cont_one++;}
                        if (firstTwo) {cont_two++;}
                        if (firstThree) {cont_three++;}
                        if (firstFour) {cont_four++;}
                        if (firstFive) {cont_five++;}
                        if (firstSix) {cont_six++;}
                        if (firstSeven) {cont_seven++;}
                        if (firstNine) {cont_nine++;}
                        
                        if (!firstEight) {
                            firstEight = true;
                            continue;
                        } else {
                            if (cont_eight <= 20) {
                                huecos_eight[cont_eight]++;
                            } else {
                                //System.out.println("Para el dig 8 hueco de: "+cont_eight);
                                totalHuecos++;
                            }
                            cont_eight = 0;
                            continue;
                        }
                    } else if (digit == '9') {
                        if (firstZero) {cont_zero++;}
                        if (firstOne) {cont_one++;}
                        if (firstTwo) {cont_two++;}
                        if (firstThree) {cont_three++;}
                        if (firstFour) {cont_four++;}
                        if (firstFive) {cont_five++;}
                        if (firstSix) {cont_six++;}
                        if (firstSeven) {cont_seven++;}
                        if (firstEight) {cont_eight++;}
                        
                        if (!firstNine) {
                            firstNine = true;
                            continue;
                        } else {
                            if (cont_nine <= 20) {
                                huecos_nine[cont_nine]++;
                            } else {
                                //System.out.println("Para el dig 9 hueco de: "+cont_nine);
                                totalHuecos++;
                            }
                            cont_nine = 0;
                            continue;
                        }
                    }
                    
                    
                    
                    
                    
                }
               
                
                
              
                
            }
                
            
            
            

            
            
            

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ex) {
                    Logger.getLogger(Prueba_HuecosDigs.class.getName()).
                            log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }

    private void writeTablaHuecos(String folder) {
        String path = "./" + folder + "/" + "tablahuecosdigs.csv";

        BufferedWriter out = null;
        try {
            FileWriter fstream = new FileWriter(path, true);
            out = new BufferedWriter(fstream);
            out.write("Tamano Hueco,0,1,2,3,4,5,6,7,8,9,Total");
            out.newLine();
            for (int i = 0; i <= 20; i++) {
                int total = huecos_zero[i] + huecos_one[i] + huecos_two[i] 
                        + huecos_three[i]
                        + huecos_four[i] + huecos_five[i] + huecos_six[i]
                        + huecos_seven[i] + huecos_eight[i] + huecos_nine[i];
                totalHuecos += total;

                out.write(i + "," + huecos_zero[i] + "," + huecos_one[i] + "," 
                        + huecos_two[i] + ","
                        + huecos_three[i] + "," + huecos_four[i] 
                        + "," + huecos_five[i] + ","
                        + huecos_six[i] + "," + huecos_seven[i] 
                        + "," + huecos_eight[i] + ","
                        + huecos_nine[i] + "," + total);
                out.newLine();
            }

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

    private void wrtieTablaFrecuencias(String folder, int scale) {
        String path = "./" + folder + "/" + "tablafrecuenciasdigs.csv";

        BufferedWriter out = null;
        try {
            FileWriter fstream = new FileWriter(path, true);
            out = new BufferedWriter(fstream);
            out.write("Huecos,f0,pe,fe,chi");
            out.newLine();
            for (int i = 0; i <= 20; i++) {
                int f0 = huecos_zero[i] + huecos_one[i] + huecos_two[i] 
                        + huecos_three[i]
                        + huecos_four[i] + huecos_five[i] + huecos_six[i]
                        + huecos_seven[i] + huecos_eight[i] + huecos_nine[i];
                BigDecimal pe = new BigDecimal("0.1").
                        multiply(new BigDecimal("0.9").pow(i)).
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
            }

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
