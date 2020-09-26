package no.oslomet.cs.algdat;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.util.*;

import java.util.function.Predicate;



public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    public DobbeltLenketListe() {
        //throw new UnsupportedOperationException();
    }

    public DobbeltLenketListe(T[] a) {
        Objects.requireNonNull(a, "Tabellen a er null!");

        if(a.length == 0) return;

        antall = a.length;

        for(int i = 0; i < a.length; i++){
            if(a[i] != null){
                hode = hale = new Node<T>(a[i],null,null);
                Node<T> p =  hode;
                i++;
                for(; i < a.length; i++){
                    if (a[i] != null){
                        hale = new Node<T>(a[i],p,null);
                        p.neste = hale;
                        p = hale;
                    }
                    else antall--;
                }
                break;
            }
            antall--;
        }
    }

    public Liste<T> subliste(int fra, int til){
        fratilKontroll(fra,til);

        Liste<T> ut = new DobbeltLenketListe<>();

        Node<T> node = hode;

        int i = 0;

        for(; i < fra; i++){
            node = node.neste;
        }

        for(; i<til; i++){
            ut.leggInn(node.verdi);
            node = node.neste;
        }
        return ut;

    }

    private void fratilKontroll(int fra, int til)
    {
        if (fra < 0)                                  // fra er negativ
            throw new IndexOutOfBoundsException
                    ("fra(" + fra + ") er negativ!");

        if (til > antall)                          // til er utenfor tabellen
            throw new IndexOutOfBoundsException
                    ("til(" + til + ") > antall(" + antall + ")");

        if (fra > til)                                // fra er større enn til
            throw new IllegalArgumentException
                    ("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
    }

    private Node<T> finnNode(int indeks){

        Node<T> node;
        if(antall / 2 >= indeks){
            node = hode;
            for(int i = 0; i < indeks; i++) node = node.neste;
        }
        else {
            node = hale;
            for(int i = antall-indeks-1; i > 0; i--) node = node.forrige;
        }
        return node;
    }

    @Override
    public int antall() {
        return antall;
    }

    @Override
    public boolean tom() {
        return antall == 0;
    }

    @Override
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi, "Verdien er null!");
        if(antall == 0){
            hode = hale = new Node<T>(verdi,null,null);
        }
        else {
            Node<T> ny = new Node<T>(verdi,hale,null);
            hale.neste = ny;
            hale = ny;
        }
        antall++;
        endringer++;
        return true;
    }

    @Override
    public void leggInn(int indeks, T verdi) {
        Objects.requireNonNull(verdi, "verdi er null!");
        indeksKontroll(indeks,true);
        Node<T> node;

        if(antall == 0) hode = hale = new Node<>(verdi,null,null);
        else if(indeks == 0){
            node = hode;
            hode = new Node<>(verdi,null,node);
            node.forrige = hode;
        }
        else if(indeks == antall){
            node = hale;
            hale = new Node<>(verdi,node,null);
            node.neste = hale;
        }
        else {
            node = finnNode(indeks);
            Node<T> ny = new Node<T>(verdi,node.forrige,node);
            node.forrige.neste = ny;
            node.forrige = ny;
        }
        antall++;
        endringer++;
    }

    @Override
    public boolean inneholder(T verdi) {
        return indeksTil(verdi) != -1;
    }

    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks,false);
        return finnNode(indeks).verdi;
    }

    @Override
    public int indeksTil(T verdi) {
        if(verdi == null) return -1;

        int indeks = 0;
        Node<T> node = hode;

        while (node != null){
            if(node.verdi.equals(verdi)) return indeks;
            node = node.neste;
            indeks++;
        }
        return -1;
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {

        Objects.requireNonNull(nyverdi, "nyverdi er null!");

        indeksKontroll(indeks,false);
        Node<T> node = finnNode(indeks);
        T ut = node.verdi;
        node.verdi = nyverdi;
        return ut;

    }

    @Override
    public boolean fjern(T verdi) {
        int indeks = indeksTil(verdi);
        if(indeks == -1) return false;
        fjern(indeks);
        return true;
    }

    @Override
    public T fjern(int indeks) {
        indeksKontroll(indeks,false);
        T ut;

        if(antall == 1){
            ut = hode.verdi;
            hode = hale = null;
        }
        else if(indeks == 0){
            ut = hode.verdi;
            hode = hode.neste;
            hode.forrige = null;
        }
        else if(indeks == antall-1){
            ut = hale.verdi;
            hale = hale.forrige;
            hale.neste = null;
        }
        else {
            Node<T> p = finnNode(indeks - 1);  // p er noden foran den som skal fjernes
            Node<T> q = p.neste;
            ut = q.verdi;
            p.neste = q.neste;
            q.neste.forrige = p;
        }
        antall--;
        endringer++;
        return ut;
    }

    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        if (antall == 0) return "[]";

        StringBuilder ut = new StringBuilder("[" + hode.verdi);
        Node<T> p = hode.neste;

        while (p != null){
            ut.append(", ").append(p.verdi);
            p = p.neste;
        }

        ut.append("]");
        return ut.toString();
    }

    public String omvendtString() {
        if (antall == 0) return "[]";

        StringBuilder ut = new StringBuilder("[" + hale.verdi);
        Node<T> p = hale.forrige;

        while (p != null){
            ut.append(", ").append(p.verdi);
            p = p.forrige;
        }

        ut.append("]");
        return ut.toString();
    }

    @Override
    public Iterator<T> iterator() {
        return new DobbeltLenketListeIterator();
    }

    public Iterator<T> iterator(int indeks) {
        indeksKontroll(indeks,false);
        return new DobbeltLenketListeIterator(indeks);
    }

    private class DobbeltLenketListeIterator implements Iterator<T>
    {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator(){
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks){
            denne = finnNode(indeks);
            fjernOK = false;
            iteratorendringer = endringer;
        }

        @Override
        public boolean hasNext(){
            return denne != null;
        }

        @Override
        public T next(){
            if(endringer != iteratorendringer) throw new ConcurrentModificationException();
            if(!hasNext()) throw new NoSuchElementException();

            fjernOK = true;
            T ut = denne.verdi;
            denne = denne.neste;

            return ut;
        }

        @Override
        public void remove(){
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }
} // class DobbeltLenketListe


