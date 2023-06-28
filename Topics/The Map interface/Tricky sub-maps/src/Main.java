import java.util.*;

class MapUtils {
    public static Map<Integer, String> getSubMap(TreeMap<Integer, String> map) {
        // Write your code here

        if (map.firstKey() % 2 != 0){
            SortedMap<Integer, String> submap = new TreeMap<>(Collections.reverseOrder());
            for (int i = 0; i < 5; i++) {
                submap.put(map.firstKey() + i, map.get(map.firstKey() + i));
            }
            return submap;
        } else {
            SortedMap<Integer, String> submap = new TreeMap<>(Collections.reverseOrder());
            int count = 5;
            for (int i = 0; i < count; i++) {
                if (map.containsKey(map.lastKey() - i)) {
                    submap.put(map.lastKey() - i, map.get(map.lastKey() - i));
                }
            }
            return submap;
        }
    }
}

/* Do not modify code below */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        TreeMap<Integer, String> map = new TreeMap<>();
        Arrays.stream(scanner.nextLine().split("\\s")).forEach(s -> {
            String[] pair = s.split(":");
            map.put(Integer.parseInt(pair[0]), pair[1]);
        });

        Map<Integer, String> res = MapUtils.getSubMap(map);
        res.forEach((k, v) -> System.out.println(k + " : " + v));
    }
}