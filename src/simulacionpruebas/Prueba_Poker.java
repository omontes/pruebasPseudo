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
import poker.Card;
import poker.HandEvaluator;

/**
 *
 * @author Oscar Montes
 */
public class Prueba_Poker {
    int cantNums = 0;
    BigDecimal chiTotal = BigDecimal.ZERO;
    
    int diferentes = 0;
    int un_par = 0;
    int dos_par=0;
    int tercia=0;
    int full=0;
    int poker = 0;
    int quintilla = 0;
    
    public Prueba_Poker(){}
    
    public void runPrueba(String path, String folder) {
        
        
        pruebaPoker(path);
        System.out.println("Cant total nums: " + cantNums);
        
        System.out.println("*****PRUEBA POKER****");
        System.out.println("Diferentes"+","+diferentes);
        System.out.println("Un par"+","+un_par);
        System.out.println("Dos pares"+","+dos_par);
        System.out.println("Tercia"+","+tercia);
        System.out.println("Full"+","+full);
        System.out.println("Poker"+","+poker);
        System.out.println("Quintilla"+","+quintilla);
        
        System.out.println("");
        System.out.println("*****Tabla de frecuencias****");
        wrtieTablaFrecuencias(folder);
        System.out.println("");
        
        System.out.println("Sumatoria chi: " + chiTotal);
         
         //Con la tabla del libro con alpha=0.005 y 100 grados --> 140.17 
         BigDecimal chiTabla = new BigDecimal("12.5916");
         if (chiTotal.compareTo(chiTabla) <= 0) {
            System.out.println("PASO LA PRUEBA PARA alpha=0.05 y n grados=6");
        }
     }

    private void pruebaPoker(String path) {
        

        BufferedReader input = null;
        
        try {

            FileReader fstream = new FileReader(path);
            input = new BufferedReader(fstream);
            
            
            String sCurrentLine = "";
            //input.readLine();//LA PRIMERA LINEA ES VACIA
            while ((sCurrentLine = input.readLine()) != null) {
                cantNums++;
                String[] numberarray = sCurrentLine.split("\\.");
                char[] digitsarray = numberarray[1].toCharArray();
                Card player1[] = new Card[5];

                // MANO POKER
                player1[0] = new Card(Character.getNumericValue(digitsarray[0]));
                player1[1] = new Card(Character.getNumericValue(digitsarray[1]));
                player1[2] = new Card(Character.getNumericValue(digitsarray[2]));
                player1[3] = new Card(Character.getNumericValue(digitsarray[3]));
                player1[4] = new Card(Character.getNumericValue(digitsarray[4]));
                HandEvaluator he = new HandEvaluator(player1);
                String mano =he.getPokerHandAsString();
                if(mano.equals("todos diferentes")){diferentes++;}
                else if(mano.equals("Un Par")){un_par++;}
                else if(mano.equals("Dos pares")){dos_par++;}
                else if(mano.equals("Tercia")){tercia++;}
                else if(mano.equals("Full")){full++;}
                else if(mano.equals("Poker")){poker++;}
                else if(mano.equals("Quintilla")){quintilla++;}
                
                
                
            }

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ex) {
                    Logger.getLogger(Prueba_Poker.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void wrtieTablaFrecuencias(String folder) {
        
        int scale=8;
        
        BigDecimal chi_aux = BigDecimal.ZERO;
        BigDecimal chi = BigDecimal.ZERO;
        
        BigDecimal p_diferentes = new BigDecimal("0.3024");
        BigDecimal p_par = new BigDecimal("0.5040");
        BigDecimal p_doblepar= new BigDecimal("0.1080");
        BigDecimal p_tercia = new BigDecimal("0.0720");
        BigDecimal p_full = new BigDecimal("0.0090");
        BigDecimal p_poker = new BigDecimal("0.0045");
        BigDecimal p_quintilla = new BigDecimal("0.0001");
        
        BigDecimal fe_diferentes = p_diferentes.multiply(new BigDecimal(cantNums));
        BigDecimal fe_par = p_par.multiply(new BigDecimal(cantNums));
        BigDecimal fe_doblepar = p_doblepar.multiply(new BigDecimal(cantNums));
        BigDecimal fe_tercia = p_tercia.multiply(new BigDecimal(cantNums));
        BigDecimal fe_full = p_full.multiply(new BigDecimal(cantNums));
        BigDecimal fe_poker = p_poker.multiply(new BigDecimal(cantNums));
        BigDecimal fe_quintilla = p_quintilla.multiply(new BigDecimal(cantNums));
        
        chi_aux = (new BigDecimal(diferentes).subtract(fe_diferentes)).pow(2).
                    setScale(scale,RoundingMode.HALF_UP);
        chi = chi_aux.divide(fe_diferentes,scale,RoundingMode.HALF_UP);
        System.out.println("Diferentes"+","+diferentes+","+p_diferentes+","
                +fe_diferentes+","+chi.toString());
        chiTotal=chiTotal.add(chi);
        
        chi_aux = (new BigDecimal(un_par).subtract(fe_par)).pow(2).
                    setScale(scale,RoundingMode.HALF_UP);
        chi = chi_aux.divide(fe_par,scale,RoundingMode.HALF_UP);
        System.out.println("Un par"+","+un_par+","+p_par+","
                +fe_par+","+chi.toString());
        chiTotal=chiTotal.add(chi);
        
        chi_aux = (new BigDecimal(dos_par).subtract(fe_doblepar)).pow(2).
                    setScale(scale,RoundingMode.HALF_UP);
        chi = chi_aux.divide(fe_doblepar,scale,RoundingMode.HALF_UP);
        System.out.println("Dos pares"+","+dos_par+","+p_doblepar+","
                +fe_doblepar+","+chi.toString());
        chiTotal=chiTotal.add(chi);
        
        chi_aux = (new BigDecimal(tercia).subtract(fe_tercia)).pow(2).
                    setScale(scale,RoundingMode.HALF_UP);
        chi = chi_aux.divide(fe_tercia,scale,RoundingMode.HALF_UP);
        System.out.println("Tercia"+","+tercia+","+p_tercia+","
                +fe_tercia+","+chi.toString());
        chiTotal=chiTotal.add(chi);
        
        chi_aux = (new BigDecimal(full).subtract(fe_full)).pow(2).
                    setScale(scale,RoundingMode.HALF_UP);
        chi = chi_aux.divide(fe_full,scale,RoundingMode.HALF_UP);
        System.out.println("Full"+","+full+","+p_full+","
                +fe_full+","+chi.toString());
        chiTotal=chiTotal.add(chi);
        
        chi_aux = (new BigDecimal(poker).subtract(fe_poker)).pow(2).
                    setScale(scale,RoundingMode.HALF_UP);
        chi = chi_aux.divide(fe_poker,scale,RoundingMode.HALF_UP);
        System.out.println("Poker"+","+poker+","+p_poker+","
                +fe_poker+","+chi.toString());
        chiTotal=chiTotal.add(chi);
        
        chi_aux = (new BigDecimal(quintilla).subtract(fe_quintilla)).pow(2).
                    setScale(scale,RoundingMode.HALF_UP);
        chi = chi_aux.divide(fe_quintilla,scale,RoundingMode.HALF_UP);
        System.out.println("Quintilla"+","+quintilla+","+p_quintilla+","
                +fe_quintilla+","+chi.toString());
        chiTotal=chiTotal.add(chi);
        
        
    }
}
