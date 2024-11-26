public class Example {
    public static void main(String[] args) {
        int[][] t = new int[5][6];
        int[] k[] = new int[3][5];
        int f[][] = new int[4][6];
        k[2][4] = 34;
        for (int[] i: k){
            for (int j: i){
                System.out.println(j);
            }
            
        }
    }
}
