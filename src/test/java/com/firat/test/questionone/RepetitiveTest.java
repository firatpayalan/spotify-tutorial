package com.firat.test.questionone;
import static org.junit.Assert.*;
import com.firat.questionone.Repetitive;
import org.junit.Test;

public class RepetitiveTest {

    @Test
    public void printTopMost()
    {
        Repetitive repetitive = new Repetitive("dizgi ve baskı endüstrisinde kullanılan mıgır mıgır metinlerdir");
        repetitive.topMostWords();

    }
    @Test
    public void printTopMost2()
    {
        Repetitive repetitive = new Repetitive("Ben giderim adım kalır Dostlar beni hatırlasın Düğün olur bayram gelir Dostlar beni hatırlasın");
        repetitive.topMostWords();
    }
    @Test
    public void printTopMost3()
    {
        Repetitive repetitive = new Repetitive("Tomorrow all will be known");
        repetitive.topMostWords();
    }
}
