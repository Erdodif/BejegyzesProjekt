package hu.petrik;

import java.time.LocalDateTime;

public class Bejegyzes {
    private String szerzo;
    private String tartalom;
    private int likeok;
    private LocalDateTime letrejott;
    private LocalDateTime szerkesztve;

    public Bejegyzes(String szerzo, String tartalom) {
        letrejott = LocalDateTime.now();
        this.szerzo = szerzo;
        this.tartalom = tartalom;
        likeok = 0;
        szerkesztve = letrejott;
    }

    public String getSzerzo() {
        return szerzo;
    }

    public String getTartalom() {
        return tartalom;
    }

    public void setTartalom(String tartalom) {
        this.tartalom = tartalom;
        szerkesztve = LocalDateTime.now();
    }

    public int getLikeok() {
        return likeok;
    }

    public LocalDateTime getLetrejott() {
        return letrejott;
    }

    public LocalDateTime getSzerkesztve() {
        return szerkesztve;
    }

    public void like() {
        this.likeok++;
    }

    @Override
    public String toString() {
        String szerkesztveVolt = szerkesztve.equals(letrejott) ? ("Szerkesztve: " + szerkesztve) : "";
        return szerzo + " - " + likeok + " - " + letrejott + '\n' +
                szerkesztveVolt + '\n' +
                tartalom;
    }
}
