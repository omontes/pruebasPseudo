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
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    
    int cantNums = 0;
    public Prueba_HuecosDigs(){};
    
    public void runPrueba(String path, int scale) {
         obtenerHuecos(path);
         for (int i = 0; i < huecos_four.length; i++) {
             System.out.println(huecos_four[i]);
            
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
                                System.out.println("Para el dig 0 hueco de: " + cont_zero);
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
                                System.out.println("Para el dig 1 hueco de: "+cont_one);
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
                                System.out.println("Para el dig 2 hueco de: "+cont_two);
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
                                System.out.println("Para el dig 3 hueco de: "+cont_three);
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
                                System.out.println("Para el dig 4 hueco de: "+cont_four);
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
                                System.out.println("Para el dig 5 hueco de: "+cont_five);
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
                                System.out.println("Para el dig 6 hueco de: "+cont_six);
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
                                System.out.println("Para el dig 7 hueco de: "+cont_seven);
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
                                System.out.println("Para el dig 8 hueco de: "+cont_eight);
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
                                System.out.println("Para el dig 9 hueco de: "+cont_nine);
                            }
                            cont_nine = 0;
                            continue;
                        }
                    }
                    
                    
                    
                    
                    
                }
               
                
                
              
                
            }
                
            
            System.out.println("Cant total nums: " + cantNums);
            

            
            
            

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
}
