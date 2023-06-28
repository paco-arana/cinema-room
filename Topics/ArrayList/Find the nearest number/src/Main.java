import java.util.*;

public class Main {
    public static void main(String[] args) {
        // write your code here
        Scanner scan = new Scanner(System.in);
        ArrayList<Integer> input = new ArrayList<>();
        while (scan.hasNext()) {
            input.add(scan.nextInt());
        }
        // Get the number on the second line
        int num = input.get(input.size() - 1);
        // Remove it from the arraylist
        input.remove(input.size() - 1);

        input.sort(Comparator.naturalOrder());

        input.add(10000);

        int matches = 0;
        int nextHigher = 0;

        // Check for perfect matches and for next highest
        for (int i : input) {
            if (i == num) {
                matches += 1;
            } else if (i > num && nextHigher == 0) {
                nextHigher = i;
            }
        }

        // If there were no perfect matches find next lowest as well
        int nextLower = 0;
        if (matches == 0) {
            int indexHigher = input.indexOf(nextHigher);
             nextLower = input.get(indexHigher-1);

        }

        // Find the number of times nextHigher and nextLower happens
        int lowMatches = 0;
        int highMatches = 0;
        for (int i : input) {
            if (i == nextHigher) {
                highMatches += 1;
            } else if (i == nextLower) {
                lowMatches += 1;
            }
        }


        if (matches != 0) {
            // If we have a match we print the match
            for (int i = 0; i < matches; i++) {
                System.out.print(num + " ");
            }
        } else if (nextHigher - num == num - nextLower) {
            // If nextHigher and nextLower are the same distance from num we print both
            for (int i = 0; i < lowMatches; i++) {
                System.out.print(nextLower + " ");
            }
            for (int i = 0; i < highMatches; i++) {
                System.out.print(nextHigher + " ");
            }
        } else if (nextHigher - num < num - nextLower) {
            // If nextHigher is closer to num we print only nextHigher
            for (int i = 0; i < highMatches; i++) {
                System.out.print(nextHigher + " ");
            }
        } else {
            for (int i = 0; i < lowMatches; i++) {
                System.out.print(nextLower + " ");
            }
        }
    }
}