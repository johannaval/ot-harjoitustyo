package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {

    Kassapaate kassa;

    public KassapaateTest() {
    }

    @Before
    public void setUp() {
        kassa = new Kassapaate();
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void rahaAlussaOikein() {

        kassa.kassassaRahaa();
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void edullistenLounaidenMaaraAlussaOikein() {

        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void maukkaidenLounaidenMaaraAlussaOikein() {

        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void josMaukasOnnistuuKassanRahamaaraKasvaa() {
        kassa.syoMaukkaasti(500);
        assertEquals(100400, kassa.kassassaRahaa());
    }

    @Test
    public void josEdullinenOnnistuuKassanRahamaaraKasvaa() {
        kassa.syoEdullisesti(300);
        assertEquals(100240, kassa.kassassaRahaa());
    }

    @Test
    public void josMaukasOnnistuuLounaidenMaaraKasvaa() {
        kassa.syoMaukkaasti(400);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void josEdullinenOnnistuuLounaidenMaaraKasvaa() {
        kassa.syoEdullisesti(240);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void josMaukasEpaonnistuuKassanRahamaaraKasvaa() {
        kassa.syoMaukkaasti(300);
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void josEdullinenEpaonnistuuKassanRahamaaraKasvaa() {
        kassa.syoEdullisesti(150);
        assertEquals(100000, kassa.kassassaRahaa());
    }

    @Test
    public void josMaukasEpaonnistuuLounaidenMaaraKasvaa() {
        kassa.syoMaukkaasti(300);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void josEdullinenEpannistuuLounaidenMaaraKasvaa() {
        kassa.syoEdullisesti(200);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void josKortillaEdullisenVerranPalauttaaTrue() {

        Maksukortti kortti = new Maksukortti(4000);

        kassa.syoEdullisesti(kortti);
        assertEquals(true, kassa.syoEdullisesti(kortti));
    }

    @Test
    public void josKortillaMaukkaanVerranPalauttaaTrue() {

        Maksukortti kortti = new Maksukortti(4000);

        kassa.syoMaukkaasti(kortti);
        assertEquals(true, kassa.syoMaukkaasti(kortti));
    }

    @Test
    public void josKortillaEiOleEdullisenVerranPalauttaaFalse() {

        Maksukortti kortti = new Maksukortti(200);

        kassa.syoEdullisesti(kortti);
        assertEquals(false, kassa.syoEdullisesti(kortti));
    }

    @Test
    public void josKortillaEiOleMaukkaanVerranPalauttaaFalse() {

        Maksukortti kortti = new Maksukortti(300);

        kassa.syoMaukkaasti(kortti);
        assertEquals(false, kassa.syoMaukkaasti(kortti));
    }

    @Test
    public void josKortillaEdullisenVerranVaheneeSummaOikein() {

        Maksukortti kortti = new Maksukortti(400);

        kassa.syoEdullisesti(kortti);

        assertEquals(160, kortti.saldo());

    }

    @Test
    public void josKortillaMaukkaanVerranVaheneeSummaOikein() {

        Maksukortti kortti = new Maksukortti(500);

        kassa.syoMaukkaasti(kortti);
        assertEquals(100, kortti.saldo());

    }

    @Test
    public void josMaukasOnnistuuKortillaLounaidenMaaraKasvaa() {
        Maksukortti kortti = new Maksukortti(4000);

        kassa.syoMaukkaasti(kortti);
        assertEquals(1, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void josEdullinenOnnistuuKortillaLounaidenMaaraKasvaa() {
        Maksukortti kortti = new Maksukortti(4000);

        kassa.syoEdullisesti(kortti);
        assertEquals(1, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void josMaukasEpaonnistuuKortillaLounaidenMaaraEiKasva() {
        Maksukortti kortti = new Maksukortti(300);

        kassa.syoMaukkaasti(kortti);
        assertEquals(0, kassa.maukkaitaLounaitaMyyty());
    }

    @Test
    public void josEdullinenEpaonnistuuKortillaLounaidenMaaraEiKasva() {
        Maksukortti kortti = new Maksukortti(200);

        kassa.syoEdullisesti(kortti);
        assertEquals(0, kassa.edullisiaLounaitaMyyty());
    }

    @Test
    public void kortinSaldoPysyyJosKortillaEiEdullisenVerran() {

        Maksukortti kortti = new Maksukortti(200);

        kassa.syoEdullisesti(kortti);

        assertEquals(100000, kassa.kassassaRahaa());

    }

    @Test
    public void kortinSaldoPysyyJosKortillaEiMaukkaanVerran() {

        Maksukortti kortti = new Maksukortti(300);

        kassa.syoMaukkaasti(kortti);
        assertEquals(100000, kassa.kassassaRahaa());

    }

    @Test
    public void kassanSaldoKasvaaOikeinRahaaLadattaessaKortille() {

        Maksukortti kortti = new Maksukortti(300);
        kassa.lataaRahaaKortille(kortti, 100);

        assertEquals(100100, kassa.kassassaRahaa());

    }

    @Test
    public void kortinSaldoMuuttuuKunLadataanRahaa() {

        Maksukortti kortti = new Maksukortti(300);
        kortti.lataaRahaa(100);

        assertEquals(400, kortti.saldo());

    }

    @Test
    public void kassanSaldoEiKasvaJosladataanNegatiivinenLuku() {

        Maksukortti kortti = new Maksukortti(300);
        kassa.lataaRahaaKortille(kortti, -100);

        assertEquals(100000, kassa.kassassaRahaa());
    }
}
