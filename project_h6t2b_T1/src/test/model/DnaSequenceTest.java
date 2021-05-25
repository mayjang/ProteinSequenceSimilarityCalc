package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Unit tests for DnaSequences class
class DnaSequenceTest {
    private DnaSequence testDna;

    @BeforeEach
    void runBefore() {
        testDna = new DnaSequence("TATATGGCCTGGACTTGATGTGAAATGCATAAATTTTAA");
    }

    @Test
    void testInitialDnaSequence() {
        assertEquals("TATATGGCCTGGACTTGATGTGAAATGCATAAATTTTAA", testDna.getDna());
        assertEquals("", testDna.getRna());

    }


    @Test
    void testAddDna(){
        String existingDna = testDna.getDna();
        String newDna = "ATGGGACACATCTAG";
        testDna.addDNA(newDna);
        assertEquals(existingDna + newDna, testDna.getDna());
    }

    @Test
    void testDnaToRna(){
        testDna.dnaToRNA();
        assertEquals("UAUAUGGCCUGGACUUGAUGUGAAAUGCAUAAAUUUUAA", testDna.getRna());

    }

    @Test
    void testSplitRna(){
        testDna.dnaToRNA();
        testDna.splitRNA();
        ArrayList<String> rnaList = testDna.getRnaList();
        assertEquals("AUGGCCUGGACU", rnaList.get(0));
        assertEquals("AUGCAUAAAUUU", rnaList.get(1));

    }

    @Test
    void testCodonToAminoAcid() {
        String[] allCodons = {"AUG", "GCA", "GCU", "GCC", "GCG", "AGA", "AGG", "CGA", "CGU", "CGC", "CGG", "AAU",
                "AAC", "GAU", "GAC", "UGU", "UGC", "GAA", "GAG", "CAA", "CAG", "GGA", "GGU", "GGC", "GGG", "CAU",
                "CAC", "AUA", "AUU", "AUC", "UUA", "UUG", "CUA", "CUU", "CUC", "CUG", "AAA", "AAG", "UUU", "UUC",
                "CCA", "CCU", "CCC", "CCG", "AGU", "AGC", "UCA", "UCU", "UCC", "UCG", "ACA", "ACU", "ACC", "ACG",
                "UGG", "UAU", "UAC", "GUA", "GUU", "GUC", "GUG"};

        String[] allAminoAcids = {"M", "A", "A", "A", "A", "R", "R", "R", "R", "R", "R", "N", "N", "D", "D", "C", "C",
                "E", "E", "Q", "Q", "G", "G", "G", "G", "H", "H", "I", "I", "I", "L", "L", "L", "L", "L", "L",
                "K", "K", "F", "F", "P", "P", "P", "P", "S", "S", "S", "S", "S", "S", "T", "T", "T", "T",
                "W", "Y", "Y", "V", "V", "V", "V"};

        for (int i = 0; i < allCodons.length; i++) {
            String codon = allCodons[i];
            String aminoAcid = testDna.codonToAminoAcid(codon);
            assertEquals(allAminoAcids[i], aminoAcid);
        }
    }




    @Test
    void testRnaToProtein(){
        testDna.dnaToRNA();
        testDna.splitRNA();
        ArrayList<String> rnaList = testDna.getRnaList();
        Protein firstProtein = testDna.rnaToProtein(rnaList.get(0));
        Protein secondProtein = testDna.rnaToProtein(rnaList.get(1));
        ArrayList<String> expectedOne = new ArrayList<>(Arrays.asList("M", "A", "W", "T"));
        ArrayList<String> expectedTwo = new ArrayList<>(Arrays.asList("M", "H", "K", "F"));

        assertEquals(firstProtein.getAminoAcidList(), expectedOne);
        assertEquals(secondProtein.getAminoAcidList(), expectedTwo);

    }

    @Test
    void testRnasToProteins(){
        testDna.dnaToRNA();
        testDna.splitRNA();
        testDna.rnasToProteins();
        ArrayList<Protein> proteinList = testDna.getProteinList();
        Protein firstProtein = proteinList.get(0);
        Protein secondProtein = proteinList.get(1);
        ArrayList<String> expectedOne = new ArrayList<>(Arrays.asList("M", "A", "W", "T"));
        ArrayList<String> expectedTwo = new ArrayList<>(Arrays.asList("M", "H", "K", "F"));

        assertEquals(firstProtein.getAminoAcidList(), expectedOne);
        assertEquals(secondProtein.getAminoAcidList(), expectedTwo);


    }

}


