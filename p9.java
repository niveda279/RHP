import java.util.HashMap;
import java.util.*;
public class EqualZeroOneSubstrings {
    public static int countSubstrings(String s) {
        HashMap<Integer, Integer> map = new HashMap<>();

        int balance = 0;
        int count = 0;

        map.put(0, 1);

        for (char ch : s.toCharArray()) {

            if (ch == '1')
                balance += 1;
            else
                balance -= 1;

            if (map.containsKey(balance))
                count += map.get(balance);

            map.put(balance, map.getOrDefault(balance, 0) + 1);
        }

        return count;
    }

    public static void main(String[] args) {
    	Scanner sc=new Scanner(System.in);
        String s = sc.nextLine() ;
        System.out.println(countSubstrings(s));
    }
}