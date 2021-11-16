
package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class KassapaateTest {
    
    Kassapaate paate;
    Maksukortti kortti;
    
    public KassapaateTest() {
        paate = new Kassapaate();
        kortti = new Maksukortti(500);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void luotuPaateOlemassa() {
        assertTrue(paate!=null);      
    }
    
    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }

    @Test
    public void rahaaAluksiOikein() {
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void aluksiEdullisiaLounaitaMyytyNolla() {
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void aluksiMaukkaitaLounaitaMyytyNolla() {
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void kassaKasvaaOikeinEdullinen() {
        paate.syoEdullisesti(500);
        
        assertEquals(100240, paate.kassassaRahaa());
    }
    
    @Test
    public void kassaAntaaOikeinTakaisinEdullinen() {
        assertEquals(260, paate.syoEdullisesti(500));
    }
    
    @Test
    public void myydytKasvaaOikeinEdullinen() {
        paate.syoEdullisesti(500);
        
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void josRahamaaraEiRiittavaSaldoEiMuutuEdullinen() {
        paate.syoEdullisesti(100);
        
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void josRahamaaraEiRiittavaRahatPalautetaanEdullinen() {
        assertEquals(100, paate.syoEdullisesti(100));
    }
    
    @Test
    public void josRahamaaraEiRiittavaMyydytEiKasvaEdullinen() {
        paate.syoEdullisesti(100);
        
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kassaKasvaaOikeinMaukas() {
        paate.syoMaukkaasti(500);
        
        assertEquals(100400, paate.kassassaRahaa());
    }
    
    @Test
    public void kassaAntaaOikeinTakaisinMaukas() {
        assertEquals(100, paate.syoMaukkaasti(500));
    }
    
    @Test
    public void myydytKasvaaOikeinMaukas() {
        paate.syoMaukkaasti(500);
        
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void josRahamaaraEiRiittavaSaldoEiMuutuMaukas() {
        paate.syoMaukkaasti(100);
        
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void josRahamaaraEiRiittavaRahatPalautetaanMaukas() {
        assertEquals(100, paate.syoMaukkaasti(100));
    }
    
    @Test
    public void josRahamaaraEiRiittavaMyydytEiKasvaMaukas() {
        paate.syoMaukkaasti(100);
        
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void josKortillaRahaaVeloitetaanKorttiaEdullinen() {
        paate.syoEdullisesti(kortti);
        
        assertEquals(260, kortti.saldo());
    }
    
    @Test
    public void josKortillaRahaaPalutetaanTrueEdullinen() {
        assertTrue(paate.syoEdullisesti(kortti));
    }
    
    @Test
    public void josKortilllaRahaaMyydytKasvaaEdullinen() {
        paate.syoEdullisesti(kortti);
        
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void josKortillaEiRahaaKorttiaEiVeloitetaEdullinen() {
        kortti.otaRahaa(480);
        paate.syoEdullisesti(kortti);
        
        assertEquals(20, kortti.saldo());
    }
    
    @Test
    public void josKortillaEiRahaaMyydytEiKasvaEdullinen() {
        kortti.otaRahaa(480);
        paate.syoEdullisesti(kortti);
        
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void josKortillaEiRahaaPalautetaanFalseEdullinen() {
        kortti.otaRahaa(480);
        
        assertFalse(paate.syoEdullisesti(kortti));
    }
    
    @Test
    public void kortillaOstettaessaKassanSaldoEiMuutuEdullinen() {
        paate.syoEdullisesti(kortti);
        
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void josKortillaRahaaVeloitetaanKorttiaMaukas() {
        paate.syoMaukkaasti(kortti);
        
        assertEquals(100, kortti.saldo());
    }
    
    @Test
    public void josKortillaRahaaPalutetaanTrueMaukas() {
        assertTrue(paate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void josKortilllaRahaaMyydytKasvaaMaukas() {
        paate.syoMaukkaasti(kortti);
        
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void josKortillaEiRahaaKorttiaEiVeloitetaMaukas() {
        kortti.otaRahaa(480);
        paate.syoMaukkaasti(kortti);
        
        assertEquals(20, kortti.saldo());
    }
    
    @Test
    public void josKortillaEiRahaaMyydytEiKasvaMaukas() {
        kortti.otaRahaa(480);
        paate.syoMaukkaasti(kortti);
        
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void josKortillaEiRahaaPalautetaanFalseMaukas() {
        kortti.otaRahaa(480);
        
        assertFalse(paate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void kortillaOstettaessaKassanSaldoEiMuutuMaukas() {
        paate.syoMaukkaasti(kortti);
        
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void rahaaLadatessaKortinSaldoMuuttuu() {
        paate.lataaRahaaKortille(kortti, 500);
        
        assertEquals(1000, kortti.saldo());
    }
    
    @Test
    public void josSummaNegatiivinenKortinSaldoEiMuutu() {
        paate.lataaRahaaKortille(kortti, -500);
        
        assertEquals(500, kortti.saldo());
    }
    
    @Test
    public void rahaaLadatessaKassanSaldoMuuttuu() {
        paate.lataaRahaaKortille(kortti, 500);
        
        assertEquals(100500, paate.kassassaRahaa());
    }
    
    @Test
    public void josSummaNegatiivinenKassanSaldoEiMuutu() {
        paate.lataaRahaaKortille(kortti, -500);
        
        assertEquals(100000, paate.kassassaRahaa());
    }
    
}
