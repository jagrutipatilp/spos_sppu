import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class OR {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int frames, pointer = 0, hit = 0, fault = 0, str_len;
        boolean isFull = false;
        int buffer[];
        int ref[];
        int mem_layout[][];
        
        // Entering the number of frames
        System.out.println("Enter the total number of Frames: ");
        frames = Integer.parseInt(br.readLine());
        
        // Entering the length of the reference string
        System.out.println("Enter the reference string size:");
        str_len = Integer.parseInt(br.readLine());
        
        ref = new int[str_len];
        mem_layout = new int[str_len][frames];
        buffer = new int[frames];

        for (int j = 0; j < frames; j++) {
            buffer[j] = -1;
        }
        
        // Entering the reference string
        System.out.println("Enter the reference string: ");
        for (int i = 0; i < str_len; i++) {
            ref[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < str_len; i++) {
            int search = -1;

            for (int j = 0; j < frames; j++) {
                if (buffer[j] == ref[i]) {
                    search = j;
                    hit++;
                    break;
                }
            }

            if (search == -1) {
                if (isFull) {
                    int index[] = new int[frames];
                    boolean index_flag[] = new boolean[frames];

                    for (int j = i + 1; j < str_len; j++) {
                        for (int k = 0; k < frames; k++) {
                            if ((ref[j] == buffer[k]) && (index_flag[k] == false)) {
                                index[k] = j;
                                index_flag[k] = true;
                                break;
                            }
                        }
                    }
                    buffer[pointer] = ref[i];
                    fault++;

                    if (!isFull) {
                        pointer++;

                        if (pointer == frames) {
                            pointer = 0;
                            isFull = true;
                        }
                    }
                }
            }

            for (int j = 0; j < frames; j++) {
                mem_layout[i][j] = buffer[j];
            }
        }

        // Display the memory layout
        for (int i = 0; i < frames; i++) {
            for (int j = 0; j < str_len; j++) {
                System.out.printf("%3d ", mem_layout[j][i]);
            }
            System.out.println();
        }

        System.out.println("Hits: " + hit);
        System.out.println("Hit Ratio: " + ((float) hit / str_len));
        System.out.println("Faults: " + fault);
    }
}
