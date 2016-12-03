import edu.duke.*;
import edu.duke.StorageResource;
import edu.duke.FileResource;
public class AllGenesStored {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
    //startIndex = dna.indexOf("ATG");
    int currIndex = dna.indexOf(stopCodon,startIndex + 3);
    while (currIndex != -1) {
        int difference = currIndex - startIndex;
        if (difference % 3 == 0) {
            return currIndex;
        }
        else {
            currIndex = dna.indexOf(stopCodon, currIndex +1);
        }
    }
    return -1; // for findGene()
    //return dna.length(); // for testStopCodon()
}
    public String findGene(String dna, int karkinos) { // for printAllGenes need (String dna, int something)
    int startIndex = dna.indexOf("ATG", karkinos); // here too ("ATG", something)
    if (startIndex == -1) {
        return "";
    }
    //Find the index of the first occurrence of the start codon ??ATG??. If there is no ??ATG??, return the empty string
    int taaIndex = findStopCodon(dna,startIndex, "TAA");
    //Find the index of the first occurrence of the stop codon ??TAA?? after the first occurrence of ??ATG?? that is a multiple of three away from the ??ATG??. Hint: call findStopCodon.
    int tagIndex = findStopCodon(dna,startIndex, "TAG");
    //Find the index of the first occurrence of the stop codon ??TAG?? after the first occurrence of ??ATG?? that is a multiple of three away from the ??ATG??. 
    int tgaIndex = findStopCodon(dna,startIndex, "TGA");
    
    //Find the index of the first occurrence of the stop codon ??TGA?? after the first occurrence of ??ATG?? that is a multiple of three away from the ??ATG??
    int closestStopCodon = 0;
    //int temp = Math.min(taaIndex, tagIndex);
    //int minIndex = Math.min(temp, tgaIndex);
    //if (minIndex == dna.length()) {
       // return "";
    //}
    if (taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)) {
        closestStopCodon = tgaIndex;
    }
    else {
        closestStopCodon = taaIndex;
    }
    if (closestStopCodon == -1 || (tagIndex != -1 && tagIndex < closestStopCodon)) {
        closestStopCodon = tagIndex;
    }
    if (closestStopCodon == -1) {
        return "";
    }
    //Return the gene formed from the ??ATG?? and the closest stop codon that is a multiple of three away. If there is no valid stop codon and therefore no gene, return the empty string.
    return dna.substring(startIndex, closestStopCodon + 3);
}
public StorageResource getAllGenes(String dna) {
        
    StorageResource geneList = new StorageResource();
    int startIndex = 0;
    while ( true ) {
        String nextGene = findGene(dna,startIndex);
            
        if (nextGene.isEmpty()) {
                break;
        }
        geneList.add(nextGene);
            startIndex = dna.indexOf(nextGene, startIndex) + nextGene.length();
        }
        return geneList;
}
public void test() { 
    String dna = ("ATGCCCCTAGCCTAACATGCCCTAATCATGTAA");
    System.out.println("Testing getAllGenes on " + dna);
    StorageResource genes = getAllGenes(dna);
    for (String g : genes.data()) {
        System.out.println(g);
    }
    //           V           v   V     v    V  v
    //           012345678901234567890123456789012
    getAllGenes("ATGCCCCTAGCCTAACATGCCCTAATCATGTAA");
    //             V  v    V  v    V  v
    getAllGenes("CCATGTAACCATGTGACCATGTAA");
    
}
public float cgRatio (String dna) {
    int C = howManyLetters(dna,"C");
    int G = howManyLetters(dna,"G");
    float cFloat = C;
    float gFloat = G;
    float cgRatio = (cFloat + gFloat)/dna.length();
    return cgRatio;

}
public int howManyLetters(String dna, String letter) {
    int letterIndex = dna.indexOf(letter);
    int howManyLetters = 0;
    while (letterIndex >= 0) {
        letterIndex = letterIndex + 1;
        howManyLetters = howManyLetters + 1;
        letterIndex = dna.indexOf(letter, letterIndex);
    }
    return howManyLetters ;
}
public int countCTG (String dna) {
    int indexCTG = dna.indexOf("CTG");
    int howManyTimes = 0;
    while (indexCTG >= 0) {
        indexCTG = indexCTG + 1;
        howManyTimes = howManyTimes + 1;
        indexCTG = dna.indexOf("CTG", indexCTG);
    }
    return howManyTimes;
}
public void processGenes (StorageResource sr) {
    
    //printGenesLongerThan60(sr);
 
    //print all the Strings in sr that are longer than 9 characters
    
    int howManyStrings = 0; //<===== for strings
    for (String g : sr.data()) {
        if (g.length() > 60) {
            howManyStrings = howManyStrings + 1; // <==== for strings
        }
    }
    System.out.println(howManyStrings);
    System.out.println("How many strings are longer than 60 print the number");
    //print the number of Strings in sr that are longer than 9 characters
    
    for (String g : sr.data()) {
        float ratioCG = cgRatio(g);
        if (ratioCG > 0.35) {
            System.out.println(g);
        }
    }
    System.out.println("Print the strings whose c-g ratio is higher than 0.35");
    //print the Strings in sr whose C-G-ratio is higher than 0.35
    
    int howManyStringsRatio = 0; // <===== for Strings
    for (String g : sr.data()) {
        float ratioCG = cgRatio(g);
        if (ratioCG > 0.35) {
           
             howManyStringsRatio = howManyStringsRatio + 1; // <===== for Strings
        }
    }
    System.out.println(howManyStringsRatio);
    System.out.println("print number of strings whose c-g ratio is higher than 0.35");
    //print the number of strings in sr whose C-G-ratio is higher than 0.35
    int longestLength = 0;
    //int howManyGenesss = 0;
    for (String g : sr.data()) {
        if (g.length() > longestLength) {
            longestLength = g.length();
        longestLength = longestLength + 1;
    }
    System.out.println(longestLength);
    System.out.println("print the length of the longest gene in file");
    //print the length of the longest gene in sr
}
}

public void printGenesLongerThan60(){
    String dna = readbraFile("C:\\Users\\steve\\Desktop\\Projects BJ\\StringsThirdAssignments\\GRch38dnapart.fa.txt");
    StorageResource karkinos = getAllGenes(dna);
    int howManyGenesLongerThan60 = 0;
    for (String g : karkinos.data()) {
        if (g.length() > 60) {
            howManyGenesLongerThan60 = howManyGenesLongerThan60 + 1;
            System.out.println(g);
            
        }
    }
    System.out.println(howManyGenesLongerThan60);
}
public void printTheLongestGene() {
    String dna = readbraFile("C:\\Users\\steve\\Desktop\\Projects BJ\\StringsThirdAssignments\\GRch38dnapart.fa.txt");
    StorageResource karkinos = getAllGenes(dna);
    int maxLen = 0;
    for (String g : karkinos.data()) {
        if (g.length() > maxLen) {
            maxLen = g.length();
            System.out.println(g);
            
        }
    }
    
    System.out.println(maxLen);
}

public void printGenesGreaterCGRatio35() {
    String dna = readbraFile("C:\\Users\\steve\\Desktop\\Projects BJ\\StringsThirdAssignments\\GRch38dnapart.fa.txt");
    StorageResource karkinos = getAllGenes(dna);
    int howManyGenesGreaterThan35 = 0;
    for (String g : karkinos.data()) {
        float ratioCG = cgRatio(g);
        if (ratioCG > 0.35) {
            howManyGenesGreaterThan35 = howManyGenesGreaterThan35 + 1;
            System.out.println(ratioCG);
            System.out.println(howManyGenesGreaterThan35);
        }
    }
}
public void testProcessGenes () {
    StorageResource sr = new StorageResource();
    String dna = readbraFile("C:\\Users\\steve\\Desktop\\Projects BJ\\StringsThirdAssignments\\GRch38dnapart.fa.txt");
    sr.add(dna);
    processGenes(sr);
}


public void testPrintGenes60(){
    StorageResource sr = new StorageResource();
    String dna = readbraFile("C:\\Users\\steve\\Desktop\\Projects BJ\\StringsThirdAssignments\\GRch38dnapart.fa.txt");
    sr.add(dna);
    //printGenesLongerThan60(sr);
}

public String readbraFile(String path) {
    FileResource file = new FileResource(path);
    String dna = file.asString();
    return dna;
}
public void CountAllGenes() {
    String dna = readbraFile("C:\\Users\\steve\\Desktop\\Projects BJ\\StringsThirdAssignments\\GRch38dnapart.fa.txt");
    StorageResource karkinos = getAllGenes(dna);
    
    int howManyGenes = 0;
    for (String g : karkinos.data()) {
        System.out.println(g);
        if (g.length() >= 0) {
            howManyGenes = howManyGenes + 1;
            //g = g + 1;
            System.out.println(howManyGenes);
        }
    }
}

public void youtubeFiles() {
    URLResource rs = new URLResource("http://dukelearntoprogram.com/course2/data/manylinks.html");
    for (String line : rs.lines()) {
        System.out.println(line);
    }
}
public void findManyGenes(String dna) {
    int howManyGenes = 0;
    while ( howManyGenes >= 0 ) {
        String nextGene = findGene(dna,howManyGenes);
            
        if (nextGene.isEmpty()) {
                break;
        }
        howManyGenes = howManyGenes + 1;
            howManyGenes = dna.indexOf(nextGene, howManyGenes) + nextGene.length();
        }
        System.out.println(howManyGenes);
}
}
