/*
 * Programmer:Jackson Lee
 * Program:Main
 * Date:5/7/16
 */
import java.util.*;
import java.io.*;
public class Main {
    private static HashSet<String> dictionary1 = new HashSet<>();
    private static ArrayList<String> dictionary2 = new ArrayList<>();
    private static PrintWriter pout;
    public static void main(String[]args) {
        Main main = new Main();
        
        try {
            FileReader fin = new FileReader("dictionary.txt");
            BufferedReader read = new BufferedReader(fin);
            String line = read.readLine();
            
            while(line != null) {
                dictionary1.add(line);
                dictionary2.add(line);
                
                line = read.readLine();
            }
            read.close();
            
        } catch(IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
            
        
        System.out.print("Enter a file: ");
        Scanner scan = new Scanner(System.in);
        String fileName = scan.nextLine();
        try {
            String[] outFile = fileName.split(".txt");
            FileWriter fout = new FileWriter(outFile[0]+".out.txt");
            BufferedWriter buffout = new BufferedWriter(fout);
            pout = new PrintWriter(buffout);
            
            pout.println("Total words in dictionary: " + dictionary1.size());
            pout.println();
            pout.println("Spell-checking using HashSet for dictionary.");
            pout.println();
            long start = System.currentTimeMillis();
            main.spellCheckHash(fileName);
            long duration = System.currentTimeMillis() - start;
            pout.println();
            pout.println("Total time for spell-check (ms): " + duration);
        
            pout.println("Spell-checking using ArrayList for dictionary.");
            pout.println();
            start = System.currentTimeMillis();
            main.spellCheckList(fileName);
            duration = System.currentTimeMillis() - start;
            pout.println();
            pout.println("Total time for spell-check (ms):" + duration);
            
            pout.flush();
            pout.close();
        } catch(IOException e) {
            
        }
    }
    
    private void spellCheckHash(String inFile) {
        try {
            FileReader fin = new FileReader(inFile);
            BufferedReader read = new BufferedReader(fin);
            String line = read.readLine();
            String cleanLine;
            LineNumberHash words = new LineNumberHash();
            int count = 1;
            while(line != null) {
                cleanLine = line.replaceAll("'", "");
                String[] arr = cleanLine.trim().split("\\W+");
                for(String s : arr) {
                    if(s.length() > 0) {
                        String temp;
                        temp = s.replaceAll("\\p{Punct}", "");
                        if(!dictionary1.contains(temp.toUpperCase())) {
                            words.add(s, count);
                        }
                    }
                }
                line = read.readLine();
                count++;
            }
            
            for(String str : words.getAllWords()) {
                String lines = str.toUpperCase() + ": ";
                ArrayList<Integer> lineNumbers = words.getLineNumbers(str);
                for(int i = 0; i < lineNumbers.size(); i++) {
                    if((i + 1) < lineNumbers.size()) {
                        lines = lines + lineNumbers.get(i) + ", ";
                    } else {
                        lines = lines + lineNumbers.get(i);
                    }
                }
                pout.println(lines);
            }
        } catch(IOException e) {
            System.out.println("Sorry m8, we couldn't find that file.");
            System.exit(0);
        }
    }
    
    private void spellCheckList(String inFile) {
        try {
            FileReader fin = new FileReader(inFile);
            BufferedReader read = new BufferedReader(fin);
            String line = read.readLine();
            String cleanLine;
            LineNumberHash words = new LineNumberHash();
            int count = 1;
            while(line != null) {
                cleanLine = line.replaceAll("'", "");
                String[] arr = cleanLine.trim().split("\\W+");
                for(String s : arr) {
                    if(s.length() > 0) {
                        String temp;
                        temp = s.replaceAll("\\p{Punct}", "");
                        if(!dictionary2.contains(temp.toUpperCase())) {
                            words.add(s, count);
                        }
                    }
                }
                line = read.readLine();
                count++;
            }
            
            for(String str : words.getAllWords()) {
                String lines = str.toUpperCase() + ": ";
                ArrayList<Integer> lineNumbers = words.getLineNumbers(str);
                for(int i = 0; i < lineNumbers.size(); i++) {
                    if((i + 1) < lineNumbers.size()) {
                        lines = lines + lineNumbers.get(i) + ", ";
                    } else {
                        lines = lines + lineNumbers.get(i);
                    }
                }
                pout.println(lines);
            }
        } catch(IOException e) {
            System.out.println("Sorry m8, we couldn't find that file.");
            System.exit(0);
        }
    }
}