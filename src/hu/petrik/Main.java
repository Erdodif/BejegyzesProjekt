package hu.petrik;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static List<Bejegyzes> bejegyzesList = new ArrayList();
    private static Scanner sc = new Scanner(System.in);

    private static void fajlBeolvasas(String eleres) {
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

    private static void felhasznaloiBekeres() {
        System.out.println("Hány bejegyzést kíván hozzáadni a listához? ");
        int darab = Integer.parseInt(sc.nextLine());
        String szerzo = "";
        if (darab > 0) {
            System.out.println("Adja meg a szerző nevét: ");
            szerzo = sc.nextLine();
            for (int i = 0; i < darab; i++) {
                System.out.println("Adja meg az " + (i + 1) + ". bejegyzés tartalmát: ");
                String tartalom = sc.nextLine();
                bejegyzesList.add(new Bejegyzes(szerzo, tartalom));
            }
        }
    }

    private static void felhasznaloiModositas(int index) {
        Bejegyzes temp = bejegyzesList.get(index);
        String tartalom = temp.getTartalom();
        String szerzo = temp.getSzerzo();
        System.out.println("Kérem Módosítsa a " + (index + 1) + ". bejegyzés tartalmát.\nTartalom:\n\t" +
                tartalom + "\n");
        temp.setTartalom(sc.nextLine());
        bejegyzesList.set(index, temp);
    }

    private static void lajkol() {
        int kor = bejegyzesList.size() * 20;
        for (int i = 0; i < kor; i++) {
            int randomszam = (int) (Math.random() * bejegyzesList.size());
            Bejegyzes temp = bejegyzesList.get(randomszam);
            temp.like();
            bejegyzesList.set(randomszam, temp);
        }
    }

    private static void megjelenit() {
        for (Bejegyzes bejegyzes : bejegyzesList) {
            System.out.println(bejegyzes);
        }
    }

    private static void init() {
        bejegyzesList.add(new Bejegyzes("Erdődi Fülöp", "Üres"));
        bejegyzesList.add(new Bejegyzes("Nem Fülöp", "Nem Üres"));
        felhasznaloiBekeres();
        fajlBeolvasas("bejegyzesek.txt");
        lajkol();
        felhasznaloiModositas(1);
        megjelenit();
    }

    private static void rendezes() {
        for (int i = bejegyzesList.size() - 1; i > -1; i--) {
            for (int j = 0; j < i; j++) {
                Bejegyzes temp1 = bejegyzesList.get(i);
                Bejegyzes temp2 = bejegyzesList.get(j);
                if (temp1.getLikeok() > temp2.getLikeok()) {
                    bejegyzesList.set(i, temp2);
                    bejegyzesList.set(j, temp1);
                }
            }
        }
    }

    private static int lajkXAlatt(int x) {
        int i = 0;
        while (i < bejegyzesList.size() && bejegyzesList.get(i).getLikeok() < x) {
            i++;
        }
        return i;
    }

    private static void kiertekeles() {
        int legtobblajk = bejegyzesList.get(0).getLikeok();
        System.out.println("\nLegtöbb lájk egy bejegyzésen: " + legtobblajk);
        if (bejegyzesList.get(0).getLikeok() > 35) {
            System.out.println("Volt olyan bejegyzés, amire több mint 35 lájk érkezett.");
        } else {
            System.out.println("Egyik bejegyzés sem kapott 35-nél több lájkot.");
        }
        System.out.println(lajkXAlatt(15) + " darab 15 lájk alatti bejegyzés volt.\n");
        megjelenit();
    }

    private static void kiiratas(String eleres) {
        try {
            FileWriter fw = new FileWriter(eleres);
            BufferedWriter bw = new BufferedWriter(fw);
            for (Bejegyzes bejegyzes : bejegyzesList) {
                bw.write(bejegyzes.toString());
            }
            bw.close();
        } catch (IOException ioe) {
            System.out.println("Hiba lépett fel");
        }
    }

    public static void main(String[] args) {
        init();
        rendezes();
        kiertekeles();
        kiiratas("bejegyzesek_rendezett.txt");
    }
}
