import java.util.*;
public class nextfit {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of processes: ");
        int n = scanner.nextInt();
        int processSize[] = new int[n];        
        System.out.println("Enter the process sizes:");
        for (int i = 0; i < n; i++) {
            processSize[i] = scanner.nextInt();
        }        
        System.out.print("Enter the number of blocks: ");
        int m = scanner.nextInt();
        int blockSize[] = new int[m];        
        System.out.println("Enter the block sizes:");
        for (int i = 0; i < m; i++) {
            blockSize[i] = scanner.nextInt();
        }        
        NextFit(blockSize, m, processSize, n);
        scanner.close();
    }
    static void NextFit(int blockSize[], int m, int processSize[], int n) {
        int allocation[] = new int[n], j = 0, t = m - 1;
        Arrays.fill(allocation, -1);
                for (int i = 0; i < n; i++) {
            while (j < m) {
                if (blockSize[j] >= processSize[i]) {
                    allocation[i] = j;
                    blockSize[j] -= processSize[i];
                    t = (j - 1) % m;
                    break;
                }
                if (t == j) {
                    t = (j - 1) % m;
                    break;
                }
                j = (j + 1) % m;
            }
        }        
        System.out.print("\nProcess No.\tProcess Size\tBlock no.\n");
        for (int i = 0; i < n; i++) {
            System.out.print(i + 1 + "\t\t" + processSize[i] + "\t\t");
            if (allocation[i] != -1) {
                System.out.print(allocation[i] + 1);
            } else {
                System.out.print("Not Allocated");
            }
            System.out.println("");
        }
    }

}

