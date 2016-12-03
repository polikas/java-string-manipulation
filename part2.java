public class Part2 {
public int howMany(String stringa, String stringb) {
    int indexA = stringb.indexOf(stringa);
    int howManyTimes = 0;
    while (indexA >= 0) {
        System.out.println(indexA);
        indexA = indexA + 1;
        howManyTimes = howManyTimes + 1;
        indexA = stringb.indexOf(stringa,indexA);
    }
    return howManyTimes;
}
public void testHowMany() {
   
   System.out.println("DNA is banana ");
   int times = howMany("a", "banana");
   System.out.println("It appears " + times);
   
   System.out.println("DNA is aboudalalulu");
   times = howMany("lu", "aboudalalulu");
   System.out.println("It appears " + times);
}
}
