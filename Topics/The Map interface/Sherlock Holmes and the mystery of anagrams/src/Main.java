import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scan = new Scanner(System.in);

        String word1 = scan.nextLine().toLowerCase();
        String word2 = scan.nextLine().toLowerCase();

        char[] word1Array = word1.toCharArray();
        char[] word2Array = word2.toCharArray();

        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();

        for (char ch : word1Array) {
            if (!map1.containsKey(String.valueOf(ch))) {
                map1.put(String.valueOf(ch), 0);
            } else {
                int value =  map1.get(String.valueOf(ch));
                map1.put(String.valueOf(ch), value + 1);
            }
        }

        for (char ch : word2Array) {
            if (!map2.containsKey(String.valueOf(ch))) {
                map2.put(String.valueOf(ch), 0);
            } else {
                int value =  map2.get(String.valueOf(ch));
                map2.put(String.valueOf(ch), value + 1);
            }
        }

        if (map1.equals(map2)) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }


    }
}