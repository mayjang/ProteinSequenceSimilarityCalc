package model;


import java.util.ArrayList;
import java.util.Arrays;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;


// Represents protein used to compare similarities with other proteins
// Consists of a list of amino acids
public class Protein implements Writable {

    private ArrayList<String> aminoAcidList;

    //EFFECTS: intialize amino acid list
    public Protein() {
        aminoAcidList = new ArrayList<>();
    }


    //MODIFIES: this
    //EFFECTS: add amino acid to amino acid list
    public void addAminoAcid(String aminoAcidName) {
        aminoAcidList.add(aminoAcidName);

    }


    public String getAminoAcid(int index) {
        if (index < aminoAcidList.size()) {
            return aminoAcidList.get(index);
        } else {
            return "";
        }

    }

    public ArrayList<String> getAminoAcidList() {
        return aminoAcidList;
    }


    public void setAminoAcidList(ArrayList<String> aminoAcidList1) {
        aminoAcidList = (ArrayList<String>) aminoAcidList1.clone();
    }

    //REQUIRES: same length of protein
    //EFFECTS: calculate protein similarity in float with a registered protein
    public float matchProtein(Protein registeredProtein) {

        int count = 0;
        for (int i = 0; i < aminoAcidList.size(); i++) {
            String aminoAcid = getAminoAcid(i);
            String registeredAminoAcid = registeredProtein.getAminoAcid(i);
            if (aminoAcid.equals(registeredAminoAcid)) {
                count++;
            }
        }
        float similarity = 100 * (float)count / (float)aminoAcidList.size();

        return similarity;

    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("aminoAcidList", aminoAcidListToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray aminoAcidListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (String a : aminoAcidList) {
            jsonArray.put(a);
        }

        return jsonArray;
    }

}
