package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Unit tests for Protein class
class ProteinTest {
    private Protein testProtein;

    @BeforeEach
    void runBefore() {
        testProtein = new Protein();
        testProtein.addAminoAcid("M");
        testProtein.addAminoAcid("H");
        testProtein.addAminoAcid("W");
        testProtein.addAminoAcid("L");
    }

    @Test
    void testInitialProtein() {
        ArrayList<String> otherProtein = new ArrayList<>(Arrays.asList("M", "H", "W", "L"));
        assertEquals(testProtein.getAminoAcidList(), otherProtein);

    }

    @Test
    void testGetAminoAcid(){
        ArrayList<String> aminoAcidList = testProtein.getAminoAcidList();
        int index = aminoAcidList.size() + 1;
        String aminoAcid = testProtein.getAminoAcid(index);
        assertEquals("", aminoAcid);

    }

    @Test
    void testMatchProteinFor100Percent(){
        Protein otherProtein = new Protein();
        otherProtein.addAminoAcid("M");
        otherProtein.addAminoAcid("H");
        otherProtein.addAminoAcid("W");
        otherProtein.addAminoAcid("L");
        float similarity = testProtein.matchProtein(otherProtein);
        assertEquals(100.0, similarity);

    }

    @Test
    void testMatchProteinFor25Percent(){
        Protein otherProtein = new Protein();
        otherProtein.addAminoAcid("M");
        otherProtein.addAminoAcid("N");
        otherProtein.addAminoAcid("S");
        otherProtein.addAminoAcid("C");
        float similarity = testProtein.matchProtein(otherProtein);
        assertEquals(25.0, similarity);

    }

    @Test
    void testMatchProteinFor50Percent(){
        Protein otherProtein = new Protein();
        otherProtein.addAminoAcid("M");
        otherProtein.addAminoAcid("H");
        otherProtein.addAminoAcid("N");
        otherProtein.addAminoAcid("S");
        float similarity = testProtein.matchProtein(otherProtein);
        assertEquals(50.0, similarity);

    }

    @Test
    void testMatchProteinForZeroPercent(){
        Protein otherProtein = new Protein();
        otherProtein.addAminoAcid("A");
        otherProtein.addAminoAcid("L");
        otherProtein.addAminoAcid("N");
        otherProtein.addAminoAcid("S");
        float similarity = testProtein.matchProtein(otherProtein);
        assertEquals(0.0, similarity);

    }

}
