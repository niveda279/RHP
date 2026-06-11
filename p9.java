import java.util.*;
public class EqualZeroOneDP {
    public static int countSubstrings(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (s.charAt(i - 1) == '1')
                dp[i] = dp[i - 1] + 1;
            else
                dp[i] = dp[i - 1] - 1;
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (dp[j] == dp[i])
                    count++;
            }
        }
        return count;
    }
    public static void main(String[] args) {
    	Scanner sc=new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(countSubstrings(s));
    }
}
