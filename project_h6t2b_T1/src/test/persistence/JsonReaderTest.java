package persistence;

import model.DnaSequence;
import model.Protein;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            DnaSequence dnaSequence = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyDnaSequence() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyDnaSequence.json");
        try {
            DnaSequence dnaSequence = reader.read();
            assertEquals("TATATGGCCTGGACTTGATGTGAAATGCATAAATTTTAA", dnaSequence.getDna());
            assertEquals("", dnaSequence.getRna());
            assertEquals(0, dnaSequence.getRnaList().size());
            assertEquals(0, dnaSequence.getProteinList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralDnaSequence() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralDnaSequence.json");
        try {
            DnaSequence dnaSequence = reader.read();
            assertEquals("TATATGGCCTGGACTTGATGTGAAATGCATAAATTTTAA", dnaSequence.getDna());
            assertEquals("UAUAUGGCCUGGACUUGAUGUGAAAUGCAUAAAUUUUAA", dnaSequence.getRna());

            List<String> rnaList = dnaSequence.getRnaList();
            assertEquals(2, rnaList.size());
            assertEquals("AUGGCCUGGACU", rnaList.get(0));
            assertEquals("AUGCAUAAAUUU", rnaList.get(1));

            List<Protein> proteinList = dnaSequence.getProteinList();
            assertEquals(2, proteinList.size());
            Protein firstProtein = proteinList.get(0);
            Protein secondProtein = proteinList.get(1);
            ArrayList<String> expectedOne = new ArrayList<>(Arrays.asList("M", "A", "W", "T"));
            ArrayList<String> expectedTwo = new ArrayList<>(Arrays.asList("M", "H", "K", "F"));
            assertEquals(firstProtein.getAminoAcidList(), expectedOne);
            assertEquals(secondProtein.getAminoAcidList(), expectedTwo);

        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
