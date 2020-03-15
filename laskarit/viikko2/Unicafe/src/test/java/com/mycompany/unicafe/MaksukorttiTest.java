package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti != null);
    }

    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals("saldo: 0.10", kortti.toString());
    }

    @Test
    public void rahanLataaminenKasvattaaSaldoaOikein() {

        kortti.lataaRahaa(50);
        kortti.lataaRahaa(10);
        assertEquals("saldo: 0.70", kortti.toString());
    }

    @Test
    public void saldoVaheneeOikeinKunSaldoaTarpeeksi() {

        kortti.otaRahaa(7);
        assertEquals("saldo: 0.3", kortti.toString());
    }

    @Test
    public void saldoEiMuutuJosRahaaEiTarpeeksi() {

        kortti.otaRahaa(11);
        assertEquals("saldo: 0.10", kortti.toString());
    }

    @Test
    public void palauttaaTrueJosOnnistuu() {

        assertEquals(true, kortti.otaRahaa(5));
    }

    @Test
    public void palauttaaFalseJosEpaonnistuu() {

        assertEquals(false, kortti.otaRahaa(15));
    }

    @Test
    public void saldoPalauttaaOikean() {

        kortti.lataaRahaa(5);
        int saldo = kortti.saldo();
        assertEquals(15.0, saldo,15.0);
    }
}
