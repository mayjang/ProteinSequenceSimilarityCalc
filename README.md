# ProteinSequenceSimilarityCalc

# Protein Sequence Similarity Calculator

## Calculates the percentage similarity between a user-inputted protein and the registered protein in the system.

***What* will this application do?**
- This application is a simplified version of the *Protein Blast* section of the 
website: https://blast.ncbi.nlm.nih.gov/Blast.cgi?PAGE=Proteins

- What is unique of this app from the website is that instead of having to convert a DNA sequence first 
 before inputting its amino acid sequence, the user can directly input the orginal DNA sequence and the app will convert 
 it to the corresponding amino acid sequence during the process. 
 
 - This DNA App is a *sequence dependent* application, and will consist of these following methods in the order of 
 DNA transcription and translation in biology:
     - Add DNA (*optional*)
     - Convert DNA to RNA
     - Split RNA
     - Convert RNA to Protein
     - Match a given list of proteins to a registered protein for similarity
     
- In the last step, this application will provide the percentage similarity between the analyzed protein of the input DNA, 
and the registered protein in the system. 


***Who* will use this application?**

- Microbiologists will use this application to help figure out what an unknown protein's function might be, by finding
a protein with the most similar RNA sequence. 


***Why* this project is *interesting* to me:**
- As a student in a combined major of Microbiology & Immunology and Computer Science, I wanted to incorporate what I'm
learning from each area of study. 

- Aside from interconnecting learning outcomes from two different courses, this project relates to my interest in the 
field of bioinformatics, which is the career path I want to pursue. 



**User Stories:**

- As a user, I want to be able to add a new DNA sequence to an existing DNA sequence.

- As a user, I want to be able to convert a DNA sequence to its corresponding messenger RNA sequence.

- As a user, I want to be able to split the RNA sequence (starting from the start codon up until before the stop codon).

- As a user, I want to be able to convert the splitted RNA sequence into proteins.

- As a user, I want to be able to calculate the similarity (in percentage) between the converted protein and a 
registered protein in the app.

- As a user, I want to be able to save my DNA sequence from file.

- As a user, I want to be able to load my DNA sequence from file.

**Phase 4: Task 2**

- I made use of the Map interface in the Class: DnaSequence, under the method: createAminoAcidMap.

**Phase 4: Task 3**

- There aren't any more refactoring that I would do in my design. 
