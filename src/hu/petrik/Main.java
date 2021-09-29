package hu.petrik;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static List<Bejegyzes> bejegyzesList = new ArrayList();

    private static void fajlbeolvasas(String eleres) {
        try {
            FileReader rd = new FileReader(eleres);
            BufferedReader br = new BufferedReader(rd);
            String ujsor = br.readLine();
            while (ujsor != null) {
                String[] bejegyzes = ujsor.split(";");
                bejegyzesList.add(new Bejegyzes(bejegyzes[0], bejegyzes[1]));
                ujsor = br.readLine();
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("Nem található további bejegyzés az adatbázisban. (" + fnfe + ")");
        } catch (IOException ioe) {
            System.out.println("Olvasási hiba lépett fel. (" + ioe + ")");
        }
    }

    private static void UI(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Hány bejegyzést kíván hozzáadni a listához? ");
        int darab = sc.nextInt();
        String szerzo = "";
        if (darab > 0) {
            System.out.println("Adja meg a szerző nevét: ");
            szerzo = sc.next();
        }
        for (int i = 0; i < darab; i++) {
            System.out.println("Adja meg az " + (i + 1) + ". bejegyzés tartalmát: ");
            String tartalom = sc.next();
            bejegyzesList.add(new Bejegyzes(szerzo, tartalom));
        }
    }

    private static void init(){
        bejegyzesList.add(new Bejegyzes("Erdődi Fülöp", "Üres"));
        bejegyzesList.add(new Bejegyzes("Nem Fülöp", "Nem Üres"));
        UI();
        fajlbeolvasas("bejegyzesek.txt");
    }

    public static void main(String[] args) {
        init();
    }
}
