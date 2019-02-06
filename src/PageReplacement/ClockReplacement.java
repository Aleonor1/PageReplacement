package PageReplacement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClockReplacement {

	public static void main(String[] args) throws IOException {
		String file = "C:\\Users\\Aleonor\\Desktop\\date.txt";
		BufferedReader reader = new BufferedReader(new FileReader(file));
		// String currentLine = reader.readLine();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int frames, pointer = 0, hit = 0, fault = 0, ref_len;
		int buffer[][];
		int reference[];
		int mem_layout[][];
		int used_layout[][];

		System.out.println("Please enter the number of Frames: ");
		// frames = Integer.parseInt(br.readLine());
		String currentLine = reader.readLine();
		frames = Integer.parseInt(currentLine);
		System.out.println(frames);
		System.out.println("Please enter the length of the Reference string: ");
		// ref_len = Integer.parseInt(br.readLine());
		ref_len = Integer.parseInt(reader.readLine());
		System.out.println(ref_len);
		reference = new int[ref_len];
		mem_layout = new int[ref_len][frames];
		used_layout = new int[ref_len][frames];
		buffer = new int[frames][2];
		for (int j = 0; j < frames; j++) {
			buffer[j][0] = -1;
			buffer[j][1] = 0;
		}
		System.out.println("Please enter the reference string: ");
		for (int i = 0; i < ref_len; i++) {
			// reference[i] = Integer.parseInt(br.readLine());
			reference[i] = Integer.parseInt(reader.readLine());
		}
		System.out.println();
		for (int i = 0; i < ref_len; i++) {
			int search = -1;
			for (int j = 0; j < frames; j++) {
				if (buffer[j][0] == reference[i]) {
					search = j;
					hit++;
					buffer[j][1] = 1;
					break;
				}
			}
			if (search == -1) {

				while (buffer[pointer][1] == 1) {
					buffer[pointer][1] = 0;
					pointer++;
					if (pointer == frames)
						pointer = 0;
				}
				buffer[pointer][0] = reference[i];
				buffer[pointer][1] = 1;
				fault++;
				pointer++;
				if (pointer == frames)
					pointer = 0;
			}
			for (int j = 0; j < frames; j++) {
				mem_layout[i][j] = buffer[j][0];
				used_layout[i][j] = buffer[j][1];
			}
		}
		System.out.println("ClockReplacement");
		for (int i = 0; i < frames; i++) {
			for (int j = 0; j < ref_len; j++)
				System.out.printf("%3d %d ", mem_layout[j][i], used_layout[j][i]);
			System.out.println();
		}

		System.out.println("The number of Hits: " + hit);
		System.out.println("Hit Ratio: " + (float) ((float) hit / ref_len));
		System.out.println("The number of Faults: " + fault);
	}

}