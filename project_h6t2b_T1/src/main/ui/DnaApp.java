package ui;

import model.DnaSequence;
import model.Protein;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Protein similarity comparison app, every step (other than addDna) is "sequence dependent" when using the
// process commands
public class DnaApp {
    private DnaSequence dnaSequence;
    private Scanner input;

    // EFFECTS: runs the DNA application
    public DnaApp() throws IOException {
        runDna();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runDna() throws IOException {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) throws IOException {
        if (command.equals("a")) {
            addDna();
        } else if (command.equals("d")) {
            convertDnaToRna();
        } else if (command.equals("p")) {
            splitRna();
        } else if (command.equals("r")) {
            convertRnaToProtein();
        } else if (command.equals("m")) {
            matchProtein();
        } else if (command.equals("s")) {
            saveDna();
        } else if (command.equals("o")) {
            openDna();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes a Dna sequence
    private void init() {
        dnaSequence = new DnaSequence("TATATGGCCTGGACTTGATGTGAAATGCATAAATTTTAA");
        input = new Scanner(System.in);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> addDna");
        System.out.println("\td -> dnaToRna");
        System.out.println("\tp -> splitRna");
        System.out.println("\tr -> rnaToProtein");
        System.out.println("\tm -> matchProtein");
        System.out.println("\ts -> save");
        System.out.println("\to -> open");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: saves current DNA sequence
    private void saveDna() throws FileNotFoundException {
        JsonWriter writer = new JsonWriter("./data/testWriterEmptyDnaSequence.json");
        writer.open();
        writer.write(dnaSequence);
        writer.close();
    }

    // EFFECTS: opens DNA sequence file
    private void openDna() throws IOException {
        JsonReader reader = new JsonReader("./data/testReaderGeneralDnaSequence.json");
        dnaSequence = reader.read();
    }

    // REQUIRES: an existing DNA sequence
    // MODIFIES: this
    // EFFECTS: adds new DNA sequence to existing DNA sequence
    private void addDna() {
        dnaSequence.addDNA("ATGGGACACATCTAG");
        System.out.println("Result of added DNA:");
        System.out.println(dnaSequence.getDna());
    }


    // REQUIRES: a DNA sequence
    // MODIFIES: this
    // EFFECTS: conducts a DNA to RNA conversion
    private void convertDnaToRna() {
        dnaSequence.dnaToRNA();
        System.out.println("DNA converted to RNA sequence:");
        System.out.println(dnaSequence.getRna());
    }

    // REQUIRES: an RNA sequence
    // MODIFIES: this
    // EFFECTS: conducts the appropriate splitting of an RNA sequence
    private void splitRna() {
        dnaSequence.splitRNA();
        System.out.println("Cut RNA sequence starting from the start codon to before the stop codon:");
        System.out.println(dnaSequence.getRnaList());
    }

    // REQUIRES: a splitted RNA list
    // MODIFIES: this
    // EFFECTS: conducts an RNA to protein conversion
    private void convertRnaToProtein() {
        System.out.println("Converted protein from RNA sequences:");
        dnaSequence.rnasToProteins();

        ArrayList<Protein> list = dnaSequence.getProteinList();
        for (int i = 0; i < list.size(); i++) {
            Protein protein = list.get(i);
            System.out.println(protein.getAminoAcidList());
        }

    }

    // REQUIRES: a protein
    // EFFECTS: matches given protein to a registered protein in the system and calculates similarity between their
    // amino acid sequences in percentage
    private void matchProtein() {
        System.out.println("Similarity between input proteins and registered protein:\n");
        Protein registeredProtein = new Protein();
        registeredProtein.addAminoAcid("M");
        registeredProtein.addAminoAcid("H");
        registeredProtein.addAminoAcid("K");
        registeredProtein.addAminoAcid("T");
        ArrayList<Protein> list = dnaSequence.getProteinList();
        for (int i = 0; i < list.size(); i++) {
            Protein protein = list.get(i);
            float similarity = protein.matchProtein(registeredProtein);
            String formatted = String.format("      - Protein %d Similarity : %.1f %%", i + 1, similarity);
            System.out.println(formatted);
        }

    }

}
