import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MatchingArtists {
	
	public static void main(String[] args) throws IOException {
		
		File inputFileName = new File("Artist_lists_small.txt");
		File outputFileName = new File("ArtistPairs.txt");
		
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(inputFileName), "UTF-8"));
			
			// Create a HashMap to set up all the data to be accessed.
			// Each artist = key and HashSet = line number(s) where the artist occurs
			Map<String, HashSet<Integer>> sections = generateHashMap(in);
			
			// findPairs uses the HashMap data structure of generated artist location mappings
			// and writes Hashmap implementations into a text file, which returns matching pairs. 
			findPairs(sections, outputFileName);
			
			/* 
			 * Total time complexity for program: O(N^2)
			 */
			
			in.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 *  Method to findPairs with a given HashMap<String, HashSet<Integer>> input.
	 *  Write matching pairs to the output file.
	 */
	private static void findPairs(Map<String, HashSet<Integer>> sections, File outputFileName) throws IOException {
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFileName), "UTF-8"));
		Object[] keys = sections.keySet().toArray();
		
		/*
		 *  Filter out any artists that does not appear at least 50 times 
		 *  This filter also optimizes the search time by reducing the number of elements to search.
		 */
		for(int i = 0; i < keys.length; i++) {
			Set<Integer> set1 = sections.get(keys[i].toString());
			
			if(set1.size() < 50){ 
				sections.remove(keys[i].toString());
			}
		} 
		
		/*
		 *  Find matching pairs, iterate through each line and get the intersection with current line 
		 *  and the next line (continue until all lines have been compared).
		 */
		keys = sections.keySet().toArray();
		for(int i=0; i < keys.length; i++) {
			for(int j = i+1; j < keys.length; j++) {
				HashSet<Integer> intersection = new HashSet<Integer>(sections.get(keys[i].toString()));
				intersection.retainAll(sections.get(keys[j].toString()));
				
				if(intersection.size() >= 50) {
					System.out.println(keys[i].toString() + ", " + keys[j].toString());
					out.write(keys[i].toString() + ", " + keys[j].toString() + "\n");
				}
			}
		}
		out.close();
	}

	private static Map<String, HashSet<Integer>> generateHashMap(
			BufferedReader in) throws IOException {
		
		Map<String, HashSet<Integer>> sections = new HashMap<String, HashSet<Integer>>();		
		HashSet<Integer> set = new HashSet<Integer>();

		String line;
		int i = 1;
			
		while((line = in.readLine()) != null) {
			// Parse line and store each artist into an array
			String[] tokens = line.split(",");
			
			for(int j = 0; j < tokens.length; j++) {
				if(tokens[j].trim().isEmpty()) {
					continue;
				}
				
				// Get each artist and store as a key value, without duplicates
				if(!sections.containsKey(tokens[j])) {
					sections.put(tokens[j], new HashSet<Integer>());
				}
				
				// Add line number to HashSet of current artist key
				set = sections.get(tokens[j]);
				set.add(i);
			}
			i++;
		}
		
		return sections;
	}
}
