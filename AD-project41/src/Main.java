import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int[] weights = new int[n];
        for (int i = 0;i < n;i++){
            weights[i] = scanner.nextInt();
        }//Inputs are read

        bubbleSort(weights,n);

        double sum = 0;
        for (int i = 0;i < n;i++){
            sum += weights[i];
        }
        double average = sum/k;

        int[] kamion = new int[k];

        for (int i = 0;i < k;i++){
            kamion[i] = 0;
        }

        greedy(weights,kamion,n,k,average);

        System.out.println("Maximum weight: " + kamion[max(kamion,k)] + " Average weight: " + average);
    }
    /*
    10
4
13 24 45 2 34 63 16 37 47 44
     */

    public static void bubbleSort(int[] weights, int n){
        for (int i = 0;i < n-1;i++){
            for (int j = 0;j < n-1;j++){
                if (weights[j] < weights[j+1]){
                    int p = weights[j];
                    weights[j] = weights[j+1];
                    weights[j+1] = p;
                }
            }
        }
    }

    public static void greedy(int[] weights, int [] kamion, int n, int k, double average){
        int m = 0;
        int p = 1;
        kamion[0] = weights[0];
        while (m < k && p < n){
            if (kamion[m] + weights[p] <= average){
                kamion[m] += weights[p];
            }else {
                m++;
                if (m < k) {
                    kamion[m] += weights[p];
                }else {
                    p--;
                }
            }
            p++;
        }
        if (p < n){
            while (p < n){
                kamion[min(kamion,k)] += weights[p];
                p++;
            }
        }
    }

    public static int min(int[] kamion, int k){
        int ans = 0;
        for (int i = 1;i < k;i++){
            if (kamion[ans] > kamion[i]){
                ans = i;
            }
        }
        return ans;
    }

    public static int max(int[] kamion, int k){
        int ans = 0;
        for (int i = 1;i < k;i++){
            if (kamion[ans] < kamion[i]){
                ans = i;
            }
        }
        return ans;
    }
}
