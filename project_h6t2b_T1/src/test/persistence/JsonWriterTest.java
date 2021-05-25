package persistence;

import model.DnaSequence;
import model.Protein;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest {

    @Test
    void testWriterInvalidFile() {
        try {
            DnaSequence dnaSequence = new DnaSequence("TATATGGCCTGGACTTGATGTGAAATGCATAAATTTTAA");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyDnaSequence() {
        try {
            DnaSequence dnaSequence = new DnaSequence("TATATGGCCTGGACTTGATGTGAAATGCATAAATTTTAA");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyDnaSequence.json");
            writer.open();
            writer.write(dnaSequence);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyDnaSequence.json");
            dnaSequence = reader.read();
            assertEquals("TATATGGCCTGGACTTGATGTGAAATGCATAAATTTTAA", dnaSequence.getDna());
            assertEquals("", dnaSequence.getRna());

            assertEquals(0, dnaSequence.getRnaList().size());
            assertEquals(0, dnaSequence.getProteinList().size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralDnaSequence() {
        try {
            DnaSequence dnaSequence = new DnaSequence("TATATGGCCTGGACTTGATGTGAAATGCATAAATTTTAA");
            dnaSequence.dnaToRNA();
            dnaSequence.splitRNA();
            dnaSequence.rnasToProteins();

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralDnaSequence.json");
            writer.open();
            writer.write(dnaSequence);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralDnaSequence.json");
            dnaSequence = reader.read();
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
            fail("Exception should not have been thrown");
        }
    }
}
