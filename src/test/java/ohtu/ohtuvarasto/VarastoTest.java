package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiLisataLiikaa() {
        varasto.lisaaVarastoon(10.1);
        assertEquals(10.0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void eiVoiOttaaLiikaa() {
        varasto.lisaaVarastoon(3);
        varasto.otaVarastosta(4);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void validoiSyote() {
        assertEquals(0, varasto.otaVarastosta(-0.1), vertailuTarkkuus);
        
        varasto.lisaaVarastoon(-0.1);
        assertEquals(varasto.getSaldo(), 0, vertailuTarkkuus);
        
        Varasto toinenVarasto = new Varasto(-0.1);
        assertEquals(0.0, toinenVarasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void TestToString() {
        assertEquals(varasto.toString(), "saldo = 0.0, vielä tilaa 10.0");
    }
    
    @Test
    public void alkusaldoKonstruktorissa() {
        Varasto uusiVarasto = new Varasto(0.1, 0.2);
        assertEquals(0.1, uusiVarasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0.1, uusiVarasto.getSaldo(), vertailuTarkkuus);
        
        Varasto samaVarasto = new Varasto(0.1, 0.1);
        assertEquals(0.1, samaVarasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0.1, samaVarasto.getSaldo(), vertailuTarkkuus);
        
        Varasto miinusVarasto = new Varasto(-0.1, -0.1);
        assertEquals(0.0, miinusVarasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0.0, miinusVarasto.getSaldo(), vertailuTarkkuus);

    }

}