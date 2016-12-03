public class Part3 {
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
public String findGene(String dna, int i) { // for printAllGenes need (String dna, int something)
    int startIndex = dna.indexOf("ATG", i); // here too ("ATG", something)
    if (startIndex == -1) {
        return "";
    }
    //Find the index of the first occurrence of the start codon “ATG”. If there is no “ATG”, return the empty string
    int taaIndex = findStopCodon(dna,startIndex, "TAA");
    //Find the index of the first occurrence of the stop codon “TAA” after the first occurrence of “ATG” that is a multiple of three away from the “ATG”. Hint: call findStopCodon.
    int tagIndex = findStopCodon(dna,startIndex, "TAG");
    //Find the index of the first occurrence of the stop codon “TAG” after the first occurrence of “ATG” that is a multiple of three away from the “ATG”. 
    int tgaIndex = findStopCodon(dna,startIndex, "TGA");
    
    //Find the index of the first occurrence of the stop codon “TGA” after the first occurrence of “ATG” that is a multiple of three away from the “ATG”
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
    //Return the gene formed from the “ATG” and the closest stop codon that is a multiple of three away. If there is no valid stop codon and therefore no gene, return the empty string.
    return dna.substring(startIndex, closestStopCodon + 3);
}
 public void printAllGenes(String dna) {
        int startIndex = 0;
        while ( true ) {
            String nextGene = findGene(dna,startIndex);
            
            if (nextGene.isEmpty()) {
                break;
            }
            System.out.println(nextGene);
            startIndex = dna.indexOf(nextGene, startIndex) + nextGene.length();
        }
}
public int countGenes(String dna) {
    int howManyGenes = 0;
    int startIndex = 0;
    while ( true ) {
           String nextGene = findGene(dna,startIndex);
           howManyGenes = howManyGenes + 1;
           if (nextGene.isEmpty()) {
               break;
           }
           System.out.println(nextGene);
           startIndex = dna.indexOf(nextGene, startIndex) + nextGene.length();
    }
    return howManyGenes;
}
public void testCountGenes() {
       System.out.println("DNA is ATGCCCCTAGCCTAACATGCCCTAATCATGTAA");
       int countTimes = countGenes("ATGCCCCTAGCCTAACATGCCCTAATCATGTAA");
       System.out.println("It appears " + countTimes);
    
       System.out.println("DNA is CCATGTAACCATGTGACCATGTAA");
       countTimes = countGenes("CCATGTAACCATGTGACCATGTAA");
       System.out.println("It appears " + countTimes);
       //                                   V     v   V  v
       //                         0123456789012345678901234
       System.out.println("DNA is CCCCAACTACATGCCCTAACATGTAG");
       countTimes = countGenes("CCCCAACTACATGCCCTAACATGTAG");
       System.out.println("It appears " + countTimes);
}
}
