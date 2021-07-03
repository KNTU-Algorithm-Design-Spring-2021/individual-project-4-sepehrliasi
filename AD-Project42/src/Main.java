import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[][] matrix = new int[n][n];
        for (int i = 0;i < n;i++){
            for (int j = 0;j < n;j++){
                matrix[i][j] = scanner.nextInt();
            }
        }
        int from = scanner.nextInt();
        int to = scanner.nextInt();

        boolean ans = true;
        ArrayList<Integer> answer1 = new ArrayList<>();
        ArrayList<Integer> vertex = new ArrayList<>();
        vertex.add(from);
        if (!findPath(from,to,vertex,answer1,matrix,n)){
            System.out.println("There is no such path");
            ans = false;
        }

        for (int i = 0;i < answer1.size()-1;i++){
            matrix[answer1.get(i)][answer1.get(i+1)] = 0;
            matrix[answer1.get(i+1)][answer1.get(i)] = 0;
        }

        ArrayList<Integer> answer2 = new ArrayList<>();
        vertex.clear();
        vertex.add(from);
        if (!findPath(from,to,vertex,answer2,matrix,n)){
            System.out.println("There is no such path");
            ans = false;
        }

        if (ans){
            System.out.println("Answer 1:");
            for (int i = answer1.size()-1;i >= 0;i--){
                System.out.print(answer1.get(i) + " ");
            }
            System.out.println();
            System.out.println("Answer 2:");
            for (int i = answer2.size()-1;i >= 0;i--){
                System.out.print(answer2.get(i) + " ");
            }
        }
    }
    /*
    4
0 1 1 0
1 0 0 1
1 0 0 1
0 1 1 0
0
3
     */

    public static boolean findPath(int from, int to, ArrayList<Integer> vertex, ArrayList<Integer> answer, int[][] matrix, int n){
        if (from == to){
            answer.add(from);
            return true;
        }
        for (int i = 0;i < n;i++){
            if (matrix[from][i] == 1 && !vertex.contains(i)){
                vertex.add(i);
                if (findPath(i,to,vertex,answer,matrix,n)){
                    answer.add(from);
                    return true;
                }
                vertex.remove(vertex.size()-1);
            }
        }
        return false;
    }
}
