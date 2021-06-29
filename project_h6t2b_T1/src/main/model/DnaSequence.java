package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// Consists of raw DNA Sequence later converted to RNA and Protein
public class DnaSequence implements Writable {
    private static String[] START_CODON = {"AUG"};
    private static String[] STOP_CODON = {"UAA", "UAG", "UGA"};

    private static String[] ALANINE = {"GCA", "GCU", "GCC", "GCG"};
    private static String[] ARGININE = {"AGA", "AGG", "CGA", "CGU", "CGC", "CGG"};
    private static String[] ASPARAGINE = {"AAU", "AAC"};
    private static String[] ASPARTIC_ACID = {"GAU", "GAC"};
    private static String[] CYSTEINE = {"UGU", "UGC"};
    private static String[] GLUTAMIC_ACID = {"GAA", "GAG"};
    private static String[] GLUTAMINE = {"CAA", "CAG"};
    private static String[] GLYCINE = {"GGA", "GGU", "GGC","GGG"};
    private static String[] HISTIDINE = {"CAU", "CAC"};
    private static String[] ISOLEUCINE = {"AUA", "AUU", "AUC"};
    private static String[] LEUCINE = {"UUA", "UUG", "CUA", "CUU", "CUC", "CUG"};
    private static String[] LYSINE = {"AAA", "AAG"};
    private static String[] PHENYLALANINE = {"UUU", "UUC"};
    private static String[] PROLINE = {"CCA", "CCU", "CCC", "CCG"};
    private static String[] SERINE = {"AGU", "AGC", "UCA", "UCU", "UCC", "UCG"};
    private static String[] THREONINE = {"ACA", "ACU", "ACC", "ACG"};
    private static String[] TRYPTOPHAN = {"UGG"};
    private static String[] TYROSINE = {"UAU", "UAC"};
    private static String[] VALINE = {"GUA", "GUU", "GUC", "GUG"};
    private ArrayList<String[]> aminoAcidDictionary;
    private ArrayList<String> aminoAcidNames;

    private String dna;
    private String rna;
    private ArrayList<String> rnaList;
    private ArrayList<Protein> proteinList;

    private Map<String, String> aminoAcidMap;




    //REQUIRES: DNA sequence to be upper case
    //EFFECTS: constructs Dna sequence given DNA sequence in string
    public DnaSequence(String sequence) {
        dna = sequence.toUpperCase();
        rna = "";
        rnaList = new ArrayList<String>();
        proteinList = new ArrayList<Protein>();

        makeAminoAcidNames();
        makeAminoAcidDictionary();
        createAminoAcidMap();

    }

    public String getDna() {
        return dna;
    }

    public String getRna() {
        return rna;
    }

    public ArrayList<String> getRnaList() {
        return rnaList;
    }

    public void setRna(String rna1) {
        rna = rna1;
    }

    public void setRnaList(ArrayList<String> rnaList1) {
        rnaList = (ArrayList<String>) rnaList1.clone();
    }

    //MODIFIES: this
    //EFFECTS: adds protein to proteinList
    public void addProtein(Protein protein) {
        proteinList.add(protein);
    }

    public ArrayList<Protein> getProteinList() {
        return proteinList;
    }

    //EFFECTS: creates an amino acid map with key as codon, and amino acid name as value
    public void createAminoAcidMap() {
        aminoAcidMap = new HashMap<String, String>();

        for (int i = 0;i < aminoAcidDictionary.size();i++) {
            String[] codons = aminoAcidDictionary.get(i);
            String aminoAcidName = aminoAcidNames.get(i);
            for (int j = 0; j < codons.length; j++) {
                String codon = codons[j];
                aminoAcidMap.put(codon, aminoAcidName);
            }
        }
    }

    //EFFECTS: make amino acid names
    private void makeAminoAcidNames() {
        aminoAcidNames = new ArrayList<>();
        aminoAcidNames.add("M");
        aminoAcidNames.add("A");
        aminoAcidNames.add("R");
        aminoAcidNames.add("N");
        aminoAcidNames.add("D");
        aminoAcidNames.add("C");
        aminoAcidNames.add("E");
        aminoAcidNames.add("Q");
        aminoAcidNames.add("G");
        aminoAcidNames.add("H");
        aminoAcidNames.add("I");
        aminoAcidNames.add("L");
        aminoAcidNames.add("K");
        aminoAcidNames.add("F");
        aminoAcidNames.add("P");
        aminoAcidNames.add("S");
        aminoAcidNames.add("T");
        aminoAcidNames.add("W");
        aminoAcidNames.add("Y");
        aminoAcidNames.add("V");

    }

    //EFFECTS: make list of RNA codons corresponding to amino acids
    private void makeAminoAcidDictionary() {
        aminoAcidDictionary = new ArrayList<>();
        aminoAcidDictionary.add(START_CODON);
        aminoAcidDictionary.add(ALANINE);
        aminoAcidDictionary.add(ARGININE);
        aminoAcidDictionary.add(ASPARAGINE);
        aminoAcidDictionary.add(ASPARTIC_ACID);
        aminoAcidDictionary.add(CYSTEINE);
        aminoAcidDictionary.add(GLUTAMIC_ACID);
        aminoAcidDictionary.add(GLUTAMINE);
        aminoAcidDictionary.add(GLYCINE);
        aminoAcidDictionary.add(HISTIDINE);
        aminoAcidDictionary.add(ISOLEUCINE);
        aminoAcidDictionary.add(LEUCINE);
        aminoAcidDictionary.add(LYSINE);
        aminoAcidDictionary.add(PHENYLALANINE);
        aminoAcidDictionary.add(PROLINE);
        aminoAcidDictionary.add(SERINE);
        aminoAcidDictionary.add(THREONINE);
        aminoAcidDictionary.add(TRYPTOPHAN);
        aminoAcidDictionary.add(TYROSINE);
        aminoAcidDictionary.add(VALINE);

    }

    //MODIFIES: this
    //EFFECTS: adds DNA sequence into existing DNA sequence
    public void addDNA(String newDNA) {
        dna = dna + newDNA;
    }

    //MODIFIES: this
    //EFFECTS: for every alphabet on the DNA strand:
    //         - if the alphabet of DNA is equal to "T", then it is converted to "U" to become RNA sequence.
    public void dnaToRNA() {
        rna = "";
        String dna = getDna();
        for (int i = 0;i < dna.length();i++) {

            if (dna.substring(i,i + 1).equals("T")) {
                rna = rna + "U";
            } else {
                rna = rna + dna.substring(i,i + 1);
            }
        }
    }

    //REQUIRES: converted RNA sequence
    //MODIFIES: this
    //EFFECTS: cuts given rna sequence starting from the start codon until before the stop codon
    public void splitRNA() {
        String rna = getRna();
        int startIndex = -1;
        int stopIndex = -1;
        ArrayList<String> cutRnaList = new ArrayList<String>();

        for (int i = 0;i < rna.length();i += 3) {
            String codon = rna.substring(i,i + 3);
            if (Arrays.asList(START_CODON).contains(codon)) {
                startIndex = i;
            } else if (Arrays.asList(STOP_CODON).contains(codon)) {
                stopIndex = i;
            }
            if (startIndex >= 0 && stopIndex > startIndex) {
                String cutRNA = rna.substring(startIndex, stopIndex);
                cutRnaList.add(cutRNA);
                startIndex = -1;
                stopIndex = -1;
            }
        }
        rnaList = cutRnaList;


    }


    //REQUIRES: 3 letter codon
    //EFFECTS: given 3 letter codon, returns corresponding amino acid name in string
    public String codonToAminoAcid(String codon) {

//        String name = "";
//        for (int i = 0; i < aminoAcidDictionary.size(); i++) {
//            String[] codons = aminoAcidDictionary.get(i);
//            if (Arrays.asList(codons).contains(codon)) {
//                name = aminoAcidNames.get(i);
//                break;
//            }
//        }
//        return name;

        String aminoAcidName = aminoAcidMap.get(codon);
        return aminoAcidName;

    }


    //REQUIRES: one RNA sequence
    //EFFECTS: converts RNA sequence into a list of amino acids, which is a single protein
    public Protein rnaToProtein(String oneRNA) {
        Protein protein = new Protein();
        for (int i = 0;i < oneRNA.length();i += 3) {
            String subRNA = oneRNA.substring(i, i + 3);
            String name = codonToAminoAcid(subRNA);
            protein.addAminoAcid(name);
        }
        return protein;
    }

    //REQUIRES: a list of RNA sequences
    //MODIFIES: this
    //EFFECTS: converts list of RNA sequences into protein list
    public void rnasToProteins() {
        ArrayList<Protein> newProteinList = new ArrayList<Protein>();
        for (int i = 0; i < rnaList.size(); i++) {
            String rna = rnaList.get(i);
            Protein protein = rnaToProtein(rna);
            newProteinList.add(protein);
        }
        proteinList = newProteinList;
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("dna", dna);
        json.put("rna", rna);
        json.put("rnaList", rnaListToJson());
        json.put("proteinList", proteinListToJson());

        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray rnaListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (String t : rnaList) {
            jsonArray.put(t);
        }

        return jsonArray;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray proteinListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Protein protein : proteinList) {
            jsonArray.put(protein.toJson());
        }

        return jsonArray;
    }

}

