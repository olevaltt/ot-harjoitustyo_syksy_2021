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
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void alussaOikeaMaaraRahaa() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void lataaminenKasvattaaSaldoaOikein() {
        kortti.lataaRahaa(200);
        
        assertEquals("saldo: 2.10", kortti.toString());
    }
    
    @Test
    public void saldoVaheneeOikeinJosTarpeeksiRahaa() {
        kortti.lataaRahaa(990);
        kortti.otaRahaa(240);
        
        assertEquals("saldo: 7.60", kortti.toString());
    }
    
    @Test
    public void saldoEiMuutuJosEiTarpeeksiRahaa() {
        kortti.otaRahaa(240);
        
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void metodinTotuusarvoPalauttaaTrueJosRahaRiittaa() {
        kortti.lataaRahaa(990);
        
        assertTrue(kortti.otaRahaa(240));
    }
    
    @Test
    public void metodinTotuusarvoPalauttaaFalseJosRahaEiRiita() {
        assertFalse(kortti.otaRahaa(240));
    }
    
    @Test
    public void saldoMetodiToimiiOikein() {
        assertEquals(10, kortti.saldo());
    }
}

