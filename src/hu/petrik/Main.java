package hu.petrik;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        List<Bejegyzes> bejegyzesList = new ArrayList();
        bejegyzesList.add(new Bejegyzes("Erdődi Fülöp", "Üres"));
        bejegyzesList.add(new Bejegyzes("Nem Fülöp", "Nem Üres"));
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
}
