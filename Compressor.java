import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Compressor {
	static HashMap<Character, Integer> hm;

	public static void main(String[] args) {

		hm = new HashMap<>();

		try {
			OutputStream outStream = new FileOutputStream("output.txt");
			InputStream inpStream = new FileInputStream("sampleFile.txt");
			PrintWriter out = new PrintWriter(outStream);

			Scanner sc = new Scanner(inpStream);

			while (sc.hasNextLine()) {
				String s = sc.nextLine();
				out.println(s);
				checkRepetition(s);
			}

			List<Map.Entry<Character, Integer>> list = new LinkedList<>(hm.entrySet());

			Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {

				@Override
				public int compare(Entry<Character, Integer> o1, Entry<Character, Integer> o2) {
					return o1.getValue().compareTo(o2.getValue());
				}

			});

			System.out.println("\n\n\n" + list);

			outStream.close();
			out.close();
			sc.close();
			inpStream.close();

		} catch (Exception e) {
			System.out.println("Error in reading file " + e);
		}

	}

	static void checkRepetition(String s) {

		for (int i = 0; i < s.length(); i++) {
			hm.put(s.charAt(i), hm.getOrDefault(s.charAt(i), 0) + 1);
		}
	}
}