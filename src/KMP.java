public class KMP {
    public static void main(String[] args) {
    String str = "ABABCABAA";
    String target = "ABABABABCABAAB";
    int[] prefix = getNext(str);
     for(int i :prefix) {
         System.out.print(i + ",");
     }
        System.out.println(kmp(str,target));
    }
    public static int[] getNext(String str) {
        char[] ch = str.toCharArray();
        int n = str.length();
        int[] prefix = new int[n];
        if(n > 0) {
            prefix[0] = 0;
        }
        // 匹配的长度， 一开始匹配的长度为0 所以len = 0；
        int len = 0;
        // 规定第一个数的没真前缀和真后缀所以第一个prefix 为0， 我们这里直接从第二个数来填。
        int i = 1;
        while(i < n) {
            if(ch[i] == ch[len]) {
                len++;
                prefix[i] = len;
                i++;
            }
            else {
                if(len > 0) {
                    len = prefix[len - 1];
                } else {
                    prefix[i] = len;
                    i++;
                }
            }
        }
        prefix[0] = -1;
        for(int j = n-1; j > 1; j--) {
            prefix[j] = prefix[j-1];
        }
        return prefix;
    }

    public static int kmp(String str, String target) {
        int n = str.length();
        int m = target.length();
        int[] next = getNext(str);
        int i = 0, j = 0;
        while(i < m) {
            if(str.charAt(j) == target.charAt(i)) {
                i++;
                j++;
            } else {
                j = next[j];
                if(j == -1) {
                    i++;
                    j++;
                }
            }
            if(j == n) {
                System.out.println(i - j);
                return i - j;
            }
        }
        return -1;
    }
}
