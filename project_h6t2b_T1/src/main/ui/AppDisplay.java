package ui;

import model.DnaSequence;
import model.Protein;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFileChooser;


class AppDisplay extends JFrame implements ActionListener {
    private DnaSequence dnaSequence;
    private JLabel labelForDnaSequence;
    private JLabel labelForRegisteredProtein;
    private JLabel labelForProteinSimilarity;
    private JTextField fieldForDnaSequence;
    private JTextField fieldForAddDna;
    private JTextField fieldForDnaToRna;
    private JTextField fieldForRegisteredProtein;
    private DefaultListModel<String> listOfCutRna;
    private DefaultListModel<String> listOfProtein;
    private JList<String> rnaList;
    private JList<String> proteinJList;
    private int progressBarY;
    private int proteinNumber;


    private static synchronized void playSound(final String name) {
        new Runnable() {
            public void run() {
                try {
                    File soundFile = new File(name);

                    Clip clip = AudioSystem.getClip();

                    //URL url = this.class.getResource("resources/" + name);
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundFile);
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.run();
    }

    //EFFECTS: play Button Sound 1
    public void playButtonSound1() {
        playSound("Resources/button-09.wav");
    }

    //MODIFIES: this
    //EFFECTS: includes functions related to the display of the app window
    //         such as size, colour, menu bar, labels, fields, etc.
    public AppDisplay(String title) {

        super(title);
        setLayout(null);

        setPreferredSize(new Dimension(800, 800));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(Color.magenta);

        createMenu();
        initConstructor();

        createLabels();
        createFields();
        createButtons12();

        add(rnaList);
        add(proteinJList);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    //MODIFIES: this
    //EFFECTS: initializes constructors
    public void initConstructor() {
        dnaSequence = new DnaSequence("TATATGGCCTGGACTTGATGTGAAATGCATAAATTTTAA");
        listOfCutRna = new DefaultListModel<>();
        rnaList = new JList<String>(listOfCutRna);
        rnaList.setBounds(250, 300, 400, 50);

        listOfProtein = new DefaultListModel<>();
        proteinJList = new JList<String>(listOfProtein);
        proteinJList.setBounds(250, 400, 400, 50);

        progressBarY = 580;
        proteinNumber = 0;
    }

    //MODIFIES this
    //EFFECTS: creates menu each with menu items
    public void createMenu() {
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu file = new JMenu("File");
        JMenuItem quit = new JMenuItem("Quit");
        quit.addActionListener(this);
        file.add(quit);
        menuBar.add(file);

        JMenu processing = new JMenu("Processing");
        JMenuItem dnaToRna = new JMenuItem("DNA to RNA");
        dnaToRna.addActionListener(this);

        JMenuItem splitRna = new JMenuItem("Split RNA");
        splitRna.addActionListener(this);
        JMenuItem rnaToProtein = new JMenuItem("RNA to Protein");
        rnaToProtein.addActionListener(this);

        processing.add(dnaToRna);
        processing.add(splitRna);
        processing.add(rnaToProtein);
        menuBar.add(processing);

        createItem(file);
    }

    //MODIFIES: this
    //EFFECTS: creates labels with specific String, location (x, y), and size (width, height)
    public void createLabels() {
        labelForDnaSequence = new JLabel("DNA Sequence: ");
        labelForDnaSequence.setBounds(150, 30, 100, 30);
        labelForRegisteredProtein = new JLabel("Registered Protein:");
        labelForRegisteredProtein.setBounds(100, 520, 150, 30);
        add(labelForDnaSequence);
        add(labelForRegisteredProtein);
    }

    //MODIFIES: this
    //EFFECTS: creates fields with specific String, location (x, y), and size (width, height)
    public void createFields() {
        fieldForDnaSequence = new JTextField(10);
        fieldForDnaSequence.setBounds(250, 30, 400, 30);
        fieldForDnaSequence.setText(dnaSequence.getDna());

        fieldForAddDna = new JTextField(10);
        fieldForAddDna.setBounds(250, 100, 200, 30);

        fieldForDnaToRna = new JTextField(10);
        fieldForDnaToRna.setBounds(250, 200, 400, 30);

        fieldForRegisteredProtein = new JTextField(10);
        fieldForRegisteredProtein.setBounds(250, 520, 400, 30);
        fieldForRegisteredProtein.setText("MHKT");

        add(fieldForDnaSequence);
        add(fieldForAddDna);
        add(fieldForDnaToRna);
        add(fieldForRegisteredProtein);
    }

    //MODIFIES: this
    //EFFECTS: creates buttons 1 and 2
    public void createButtons12() {
        JButton addDnaBtn = new JButton("Add DNA");
        addDnaBtn.setBounds(70, 100, 150, 30);
        addDnaBtn.setActionCommand("addDna");
        addDnaBtn.addActionListener(this);
        add(addDnaBtn);

        JButton dnaToRnaBtn = new JButton("DNA to RNA");
        dnaToRnaBtn.setBounds(70, 200, 150, 30);
        dnaToRnaBtn.setActionCommand("dnaToRna");
        dnaToRnaBtn.addActionListener(this);
        add(dnaToRnaBtn);
    }

    //MODIFIES: this
    //EFFECTS: creates buttons 3, 4, and 5
    public void createButtons345() {
        JButton splitRnaBtn = new JButton("Split RNA");
        splitRnaBtn.setBounds(70, 300, 150, 30);
        splitRnaBtn.setActionCommand("splitDna");
        splitRnaBtn.addActionListener(this);
        add(splitRnaBtn);

        JButton rnaToProtein = new JButton("RNA to Protein");
        rnaToProtein.setBounds(70, 400, 150, 30);
        rnaToProtein.setActionCommand("rnaToProtein");
        rnaToProtein.addActionListener(this);
        add(rnaToProtein);

        JButton matchProtein = new JButton("Match Protein");
        matchProtein.setBounds(70, 550, 150, 30);
        matchProtein.setActionCommand("matchProtein");
        matchProtein.addActionListener(this);
        add(matchProtein);
    }

    //REQUIRES: a file
    //MODIFIES: this
    //EFFECTS: adds menu items in the file
    public void createItem(JMenu file) {
        JMenuItem open = new JMenuItem("Open");
        open.addActionListener(this);
        file.add(open);
        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(this);
        file.add(save);
    }

    //EFFECTS: saves current DNA sequence
    private void saveDna() throws FileNotFoundException {
        JFileChooser chooser = new JFileChooser();
        int userSelection = chooser.showSaveDialog(null);
        if (userSelection != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File fileToSave = chooser.getSelectedFile();

        JsonWriter writer = new JsonWriter(fileToSave.getAbsolutePath());
        writer.open();
        writer.write(dnaSequence);
        writer.close();
    }

    //EFFECTS: opens DNA sequence file
    private void openDna() throws IOException {

        JFileChooser chooser = new JFileChooser();
        int userSelection = chooser.showOpenDialog(null);
        if (userSelection != JFileChooser.APPROVE_OPTION) {
            return;
        }

        File fileToOpen = chooser.getSelectedFile();

        JsonReader reader = new JsonReader(fileToOpen.getAbsolutePath());
        dnaSequence = reader.read();

        fieldForDnaSequence.setText(dnaSequence.getDna());
        fieldForDnaToRna.setText(dnaSequence.getRna());

        ArrayList<String> cutRnaList = dnaSequence.getRnaList();
        listOfCutRna.removeAllElements();
        for (int i = 0; i < cutRnaList.size(); i++) {
            listOfCutRna.addElement(cutRnaList.get(i));
        }

        ArrayList<Protein> proteinList1 = dnaSequence.getProteinList();
        updateProtein(proteinList1);
    }

    //EFFECTS: displays app title and ables visibility
    public static void main(String[] args) {
        AppDisplay myApp = new AppDisplay("DNA application");
        myApp.setVisible(true);
    }

    //EFFECTS: calls functions according to action performed
    public void actionPerformed(ActionEvent ae) {
        String choice = ae.getActionCommand();
        if (choice.equals("Quit")) {
            actionPerformedQuit();
        } else if (choice.equals("Open")) {
            actionPerformedOpen();
        } else if (choice.equals("Save")) {
            actionPerformedSave();
        } else if (ae.getActionCommand().equals("addDna")) {
            playButtonSound1();
            actionPerformedAddDna();
        } else if (ae.getActionCommand().equals("dnaToRna") || choice.equals("DNA to RNA")) {
            playButtonSound1();
            actionPerformedDnaToRna();
        } else if (ae.getActionCommand().equals("splitDna") || choice.equals("Split RNA")) {
            playButtonSound1();
            actionPerformedSplitDna();
        } else if (ae.getActionCommand().equals("rnaToProtein") || choice.equals("RNA to Protein")) {
            playButtonSound1();
            actionPerformedRnaToProtein();
        } else if (ae.getActionCommand().equals("matchProtein")) {
            playButtonSound1();
            actionPerformedMatchProtein();
        }
    }

    //EFFECTS: exits window when action performed is quit
    public void actionPerformedQuit() {
        System.exit(0);
    }

    //EFFECTS: opens DNA sequence when action performed is open
    public void actionPerformedOpen() {
        try {
            openDna();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //EFFECTS: saves DNA sequence when action performed is save
    public void actionPerformedSave() {
        try {
            saveDna();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //MODIFIES: this
    //EFFECTS: adds DNA String from field2 to field1
    public void actionPerformedAddDna() {
        dnaSequence.addDNA(fieldForAddDna.getText());
        fieldForDnaSequence.setText(dnaSequence.getDna());
    }

    //MODIFIES: this
    //EFFECTS: converts DNA to RNA and adds String to field3
    public void actionPerformedDnaToRna() {
        dnaSequence.dnaToRNA();
        fieldForDnaToRna.setText(dnaSequence.getRna());
    }

    //MODIFIES: this
    //EFFECTS: splits sequence into a list and displays it in list1
    public void actionPerformedSplitDna() {
        dnaSequence.splitRNA();
        ArrayList<String> cutRnaList = dnaSequence.getRnaList();
        listOfCutRna.removeAllElements();
        for (int i = 0; i < cutRnaList.size(); i++) {
            listOfCutRna.addElement(cutRnaList.get(i));
        }
    }

    //MODIFIES: this
    //EFFECTS: converts RNA into a list of proteins, displays it in list 2
    public void actionPerformedRnaToProtein() {
        dnaSequence.rnasToProteins();
        ArrayList<Protein> proteinList1 = dnaSequence.getProteinList();
        updateProtein(proteinList1);
    }

    //REQUIRES: a protein list
    //EFFECTS: updates a protein list by adding appropriate proteins
    public void updateProtein(ArrayList<Protein> proteinList) {
        listOfProtein.removeAllElements();
        for (int i = 0; i < proteinList.size(); i++) {
            Protein protein = proteinList.get(i);
            String proteinString = "";
            for (int j = 0; j < protein.getAminoAcidList().size(); j++) {
                String aminoAcid = protein.getAminoAcid(j);
                proteinString = proteinString + aminoAcid;
            }
            listOfProtein.addElement(proteinString);
        }
    }

    //EFFECTS: Matches registered protein to new protein and shows progress bar with percentage
    public void actionPerformedMatchProtein() {
        Protein registeredProtein = new Protein();
        for (int i = 0; i < fieldForRegisteredProtein.getText().length(); i++) {
            registeredProtein.addAminoAcid(fieldForRegisteredProtein.getText().substring(i, i + 1));
        }
        ArrayList<Protein> proteinList = dnaSequence.getProteinList();
        for (int i = 0; i < proteinList.size(); i++) {
            Protein protein = proteinList.get(i);
            proteinNumber++;
            float percentage = protein.matchProtein(registeredProtein);
            createProgressBarWithTimer(percentage);
        }
    }

    //REQUIRES: percentage in float
    //MODIFIES: this
    //EFFECTS: creates progress bar according to percentage from match proteins
    public JProgressBar createProgressBar(float percentage) {
        JProgressBar progressBar = new JProgressBar(SwingConstants.HORIZONTAL, 0, 100);
        progressBar.setSize(100, 200);
        progressBar.setBounds(250, progressBarY, 200, 80);
        labelForProteinSimilarity = new JLabel("Protein " + Integer.toString(proteinNumber) + " Similarity:");
        labelForProteinSimilarity.setBounds(100, progressBarY + 25, 200, 30);
        progressBarY = progressBarY + 50;

        progressBar.setStringPainted(true);
        add(progressBar);
        add(labelForProteinSimilarity);

        invalidate();
        validate();
        repaint();
        progressBar.repaint();
        progressBar.setValue(0);

        return progressBar;
    }

    //REQUIRES: percentage in float
    //EFFECTS: create progress bar with timer, given percentage for the bar
    public void createProgressBarWithTimer(float percentage) {
        JProgressBar progressBar = createProgressBar(percentage);
        float lastPosition = percentage;
        int i = 0;
        try {

            Timer timer = new Timer(50, new ActionListener() {
                private int position;

                @Override
                public void actionPerformed(ActionEvent e) {
                    position += 1;
                    if (position < lastPosition) {
                        progressBar.setValue(position + 1);
                    } else {
                        ((Timer) e.getSource()).stop();
                    }
                }
            });
            timer.start();

        } catch (Exception e) {
            System.out.println("An error occurred");
        }
    }
}
