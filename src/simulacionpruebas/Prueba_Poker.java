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
import java.util.ArrayList;
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
        
        writePokerResult(folder);
        System.out.println("POKER ESTADISTICAS");
        System.out.println("Diferentes"+","+diferentes);
        System.out.println("Un par"+","+un_par);
        System.out.println("Dos pares"+","+dos_par);
        System.out.println("Tercia"+","+tercia);
        System.out.println("Full"+","+full);
        System.out.println("Poker"+","+poker);
        System.out.println("Quintilla"+","+quintilla);
        
        System.out.println("");
        
        wrtieTablaFrecuencias(folder);
        
        
        System.out.println("Sumatoria chi: " + chiTotal);
         
         //Con la tabla del libro con alpha=0.005 y 6 grados --> 12.5916 
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
        
        String path = "./" + folder + "/" + "tablafrecpoker.csv";
        ArrayList<String> lines = new ArrayList<String>();
        

        lines.add("\\begin{table}");
        lines.add("\\centering");
        lines.add("\\begin{tabular}{cccc}");//4 columnas = cccc
        lines.add("\\" +"\\"+"\\" + "hline");
        lines.add("Clase&f0&pe&chi"+"\\" +"\\"+"\\" + "hline");
        
                
        BufferedWriter out = null;
        try {
            FileWriter fstream = new FileWriter(path, true);
            out = new BufferedWriter(fstream);
            out.write("Clase,f0,pe,fe,chi");
            out.newLine();
            
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
        out.write("Diferentes"+","+diferentes+","+p_diferentes+","
                +fe_diferentes+","+chi.toString());
        out.newLine();
        chiTotal=chiTotal.add(chi);
        lines.add("Diferentes&"+diferentes+"&"+p_quintilla+"&"+chi.toString()+"\\" +"\\");
        
        chi_aux = (new BigDecimal(un_par).subtract(fe_par)).pow(2).
                    setScale(scale,RoundingMode.HALF_UP);
        chi = chi_aux.divide(fe_par,scale,RoundingMode.HALF_UP);
        out.write("Un par"+","+un_par+","+p_par+","
                +fe_par+","+chi.toString());
        out.newLine();
        chiTotal=chiTotal.add(chi);
        lines.add("Un par&"+un_par+"&"+p_par+"&"+chi.toString()+"\\" +"\\");
        
        chi_aux = (new BigDecimal(dos_par).subtract(fe_doblepar)).pow(2).
                    setScale(scale,RoundingMode.HALF_UP);
        chi = chi_aux.divide(fe_doblepar,scale,RoundingMode.HALF_UP);
        out.write("Dos pares"+","+dos_par+","+p_doblepar+","
                +fe_doblepar+","+chi.toString());
        out.newLine();
        chiTotal=chiTotal.add(chi);
        lines.add("Dos pares&"+dos_par+"&"+p_doblepar+"&"+chi.toString()+"\\" +"\\");
        
        chi_aux = (new BigDecimal(tercia).subtract(fe_tercia)).pow(2).
                    setScale(scale,RoundingMode.HALF_UP);
        chi = chi_aux.divide(fe_tercia,scale,RoundingMode.HALF_UP);
        out.write("Tercia"+","+tercia+","+p_tercia+","
                +fe_tercia+","+chi.toString());
        out.newLine();
        chiTotal=chiTotal.add(chi);
        lines.add("Tercia&"+tercia+"&"+p_tercia+"&"+chi.toString()+"\\" +"\\");
        
        chi_aux = (new BigDecimal(full).subtract(fe_full)).pow(2).
                    setScale(scale,RoundingMode.HALF_UP);
        chi = chi_aux.divide(fe_full,scale,RoundingMode.HALF_UP);
        out.write("Full"+","+full+","+p_full+","
                +fe_full+","+chi.toString());
        out.newLine();
        chiTotal=chiTotal.add(chi);
        lines.add("Full&"+full+"&"+p_full+"&"+chi.toString()+"\\" +"\\");
        
        chi_aux = (new BigDecimal(poker).subtract(fe_poker)).pow(2).
                    setScale(scale,RoundingMode.HALF_UP);
        chi = chi_aux.divide(fe_poker,scale,RoundingMode.HALF_UP);
        out.write("Poker"+","+poker+","+p_poker+","
                +fe_poker+","+chi.toString());
        out.newLine();
        chiTotal=chiTotal.add(chi);
        lines.add("Poker&"+poker+"&"+p_poker+"&"+chi.toString()+"\\" +"\\");
        
        chi_aux = (new BigDecimal(quintilla).subtract(fe_quintilla)).pow(2).
                    setScale(scale,RoundingMode.HALF_UP);
        chi = chi_aux.divide(fe_quintilla,scale,RoundingMode.HALF_UP);
        out.write("Quintilla"+","+quintilla+","+p_quintilla+","
                +fe_quintilla+","+chi.toString());
        out.newLine();
        chiTotal=chiTotal.add(chi);
        lines.add("Quintilla&"+quintilla+"&"+p_quintilla+"&"+chi.toString()
                +"\\" + "\\" + "\\" + "hline");
        lines.add("Total & & &"+chiTotal.toString()
                +"\\" + "\\" + "\\" + "hline");
        lines.add("\\end{tabular}");
        lines.add("\\caption{\\label{tab:frecpoker"+folder+"}"
                + "Frecuencias poker para "+folder+"}");
        lines.add("\\end{table}");
        
            
        System.out.println("LATEX DE TABLA FREC POKER");
        for (String line : lines) {
            System.out.println(line);
        }
        System.out.println("");
            
            
           

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                    Logger.getLogger(Prueba_Poker.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        }
        
        
        
        
    }

    private void writePokerResult(String folder) {
        String path = "./" + folder + "/" + "pokerestadisticas.csv";
        ArrayList<String> lines = new ArrayList<String>();
        lines.add("\\begin{table}");
        lines.add("\\centering");
        lines.add("\\begin{tabular}{cc}");//4 columnas = cccc
        lines.add("\\" +"\\"+"\\" + "hline");
        lines.add("Clase&f0"+"\\" +"\\"+"\\" + "hline");
        BufferedWriter out = null;
        try {
            FileWriter fstream = new FileWriter(path, true);
            out = new BufferedWriter(fstream);
            out.write("Clase,f0");
            out.newLine();
            
            out.write("Diferentes"+","+diferentes);
            lines.add("Diferentes&"+diferentes+"\\" + "\\");
            out.newLine();
            out.write("Un par"+","+un_par);
            lines.add("Un par&"+un_par+"\\" + "\\" + "\\");
            out.newLine();
            out.write("Dos pares"+","+dos_par);
            lines.add("Dos pares&"+dos_par+"\\" + "\\");
            out.newLine();
            out.write("Tercia"+","+tercia);
            lines.add("Tercia&"+tercia+"\\" + "\\");
            out.newLine();
            out.write("Full"+","+full);
            lines.add("Full&"+full+"\\" + "\\");
            out.newLine();
            out.write("Poker"+","+poker);
            lines.add("Poker&"+poker+"\\" + "\\");
            out.newLine();
            out.write("Quintilla"+","+quintilla);
            lines.add("Quintilla&"+quintilla+"\\" + "\\" + "\\" + "hline");
            out.newLine();
            
            lines.add("\\end{tabular}");
            lines.add("\\caption{\\label{tab:estadistpoker"+folder+"}"
                    + "Estadisticas poker para "+folder+"}");
            lines.add("\\end{table}");
            
            System.out.println("");
            System.out.println("LATEX DE TABLA ESTADISTICAS POKER");
            for (String line : lines) {
                System.out.println(line);
            }
            System.out.println("");

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                    Logger.getLogger(Prueba_Poker.class.getName())
                            .log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
