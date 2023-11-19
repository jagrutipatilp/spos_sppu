import java.util.Scanner;
public class FIFO {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        int numFrames, numInputs;
        System.out.println("Enter the number of FRAMES:");
        numFrames = scanner.nextInt();
        int fifo[] = new int[numFrames];
        System.out.println("Enter the number of INPUTS:");
        numInputs = scanner.nextInt();
        int inputs[] = new int[numInputs];
        System.out.println("Enter INPUT:");
        for (int i = 0; i < numInputs; i++) {
            inputs[i] = scanner.nextInt();
        }
        int result[] = runFIFO(inputs, fifo, numFrames);
        System.out.println("HIT:" + result[0] + " FAULT:" + result[1] + " HIT RATIO:" + calculateHitRatio(result[0], numInputs));
        scanner.close();
    }
    private static int[] runFIFO(int[] inputs, int[] fifo, int numFrames) {
        int hit = 0;
        int fault = 0;
        int j = 0;
        boolean check;
        for (int i = 0; i < inputs.length; i++) {
            check = false;
            for (int k = 0; k < numFrames; k++) {
                if (fifo[k] == inputs[i]) {
                    check = true;
                    hit++;
                }
            }
            if (!check) {
                fifo[j] = inputs[i];
                j++;
                if (j >= numFrames) {
                    j = 0;
                }
                fault++;
            }
        }
        return new int[]{hit, fault};
    }
    private static float calculateHitRatio(int hits, int totalInputs) {
        return (float) hits / totalInputs;
    }
}