package persistence;

import model.DnaSequence;
import model.Protein;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.json.*;


// Citation: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReader {

    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads dnaSequence from file and returns it;
    // throws IOException if an error occurs reading data from file
    public DnaSequence read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseDnaSequence(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses dnaSequence from JSON object and returns it
    private DnaSequence parseDnaSequence(JSONObject jsonObject) {
        String dna = jsonObject.getString("dna");
        DnaSequence dnaSequence = new DnaSequence(dna);
        String rna = jsonObject.getString("rna");
        addRnaList(dnaSequence, jsonObject);
        addProteinList(dnaSequence, jsonObject);
        dnaSequence.setRna(rna);
        return dnaSequence;
    }

    // MODIFIES: dnaSequence
    // EFFECTS: parses rnaList from JSON object and adds them to dnaSequence
    private void addRnaList(DnaSequence dnaSequence, JSONObject jsonObject) {
        ArrayList<String> rnaList = new ArrayList<>();
        JSONArray dnaSequenceJsonArray = jsonObject.getJSONArray("rnaList");
        int l = dnaSequenceJsonArray.length();
        for (int i = 0; i < l; i++) {
            rnaList.add(dnaSequenceJsonArray.getString(i));
        }
        dnaSequence.setRnaList(rnaList);
    }

    // MODIFIES: dnaSequence
    // EFFECTS: parses proteinList from JSON object and adds them to dnaSequence
    private void addProteinList(DnaSequence dnaSequence, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("proteinList");
        for (Object json : jsonArray) {
            JSONObject nextProtein = (JSONObject) json;
            addProtein(dnaSequence, nextProtein);
        }
    }

    // MODIFIES: dnaSequence
    // EFFECTS: parses aminoAcidList from JSON object and create a protein, then add the protein to dnaSequence
    private void addProtein(DnaSequence dnaSequence, JSONObject jsonObject) {
        ArrayList<String> aminoAcidList = new ArrayList<>();
        JSONArray dnaSequenceJsonArray = jsonObject.getJSONArray("aminoAcidList");
        int l = dnaSequenceJsonArray.length();
        for (int i = 0; i < l; i++) {
            aminoAcidList.add(dnaSequenceJsonArray.getString(i));
        }

        Protein protein = new Protein();
        protein.setAminoAcidList(aminoAcidList);
        dnaSequence.addProtein(protein);
    }

}
