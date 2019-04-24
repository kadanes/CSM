import java.util.*;
import java.util.Map.Entry;

public class Gap {
	public static void main(String[] args) {
		
		Random rand = new Random();
		
		Integer[] randomNos = new Integer[110];
		
		int length = 110;
		
		for (int i = 0; i < length; i+=1) {
			randomNos[i] = rand.nextInt(10);
		}
		
		HashMap<Integer, ArrayList<Integer>> positions = new HashMap<>();
		
		for (int i = 0; i < 10; i += 1) {
			ArrayList<Integer> positionList = new ArrayList<>();
			positions.put(i, positionList);
		}
		
		for ( int i = 0; i < length; i += 1 ) {
			int no = randomNos[i];
			positions.get(no).add(i);
			
		}
		
		HashMap<Integer, Integer> gaps = new HashMap<>();
		
		
		for (int i = 0 ; i < 10; i+=1) {
		
			
			ArrayList<Integer> positionList = positions.get(i);
			for ( int j = 0; j < positionList.size()-1; j+=1) {
				

				int oldCount = 0;
				int gapLen =  positionList.get(j+1) - positionList.get(j) - 1;
				
				if (gaps.containsKey(gapLen)) {
					oldCount = gaps.get(gapLen);	
				}
				
				oldCount += 1;
				
				gaps.put(gapLen, oldCount);
				
			}
 		}
		
		int max = 0;
		
		for (Entry<Integer, Integer> randomGaps : gaps.entrySet()) {
			
			System.out.println(randomGaps.getKey() + " : " + randomGaps.getValue());
			if (randomGaps.getKey() > max) {
				max = randomGaps.getKey();
			}
		}
		
		ArrayList<Integer> gapCount = new ArrayList<>();
				
		int mul = 1;
		while ( 4 * mul < max+1 ) {
			mul += 1;
		}
		

		max = mul * 4 -1;

		for (int i = 0; i <= max; i+=1) {
			if (gaps.containsKey(i)) {
				gapCount.add(gaps.get(i));
			} else {
				gapCount.add(0);
			}
		}
		
		System.out.println("Gaps: " + gapCount.size());
		
		ArrayList<Integer> groupGapCount = new ArrayList<>();
		
		for (int i = 0; i < gapCount.size()-3; i+=4) {
			 int count = gapCount.get(i) + gapCount.get(i+1) + gapCount.get(i+2) + gapCount.get(i+3);
			 groupGapCount.add(count);
		}
		
		System.out.println(groupGapCount.size());
		
		ArrayList<Double> cumulativeCount = new ArrayList<>();
		
		Double sum = 0.0;
		
		for ( int i = 0; i < groupGapCount.size(); i += 1) {
		
			sum += (double) ((double)groupGapCount.get(i)/(length-10));			
			cumulativeCount.add(sum);
		}
		
		ArrayList<Double> cumulativeProbability = new ArrayList<>();

		for ( int i = 0; i < groupGapCount.size(); i += 1) {
			cumulativeProbability.add(1 - Math.pow(0.9, 4 * (i+1)));
		}
		
		ArrayList<Double> diff = new ArrayList<>();
		
		for (int i = 0; i < groupGapCount.size(); i += 1) {
			
			diff.add(Math.abs(cumulativeCount.get(i)-cumulativeProbability.get(i)));
		}
		
		Double maxDiff = 0.0;
		
		
		for (int i = 0; i < groupGapCount.size(); i += 1) {
			System.out.println(4*i + "-" + (4*(i+1)-1) + "\t" + cumulativeCount.get(i) + "\t" + cumulativeProbability.get(i) + "\t" + diff.get(i));
			if (diff.get(i) > maxDiff) {
				maxDiff = diff.get(i);
			}
		}
		
		Double D = 1.36/Math.sqrt(length-10);
		
		System.out.println("Max Diff: " + maxDiff + " D: " + D);
		
		if (D > maxDiff) {
			System.out.println("Hypothisis of independence not rejected");
		} else {
			System.out.println("Hypothisis of independence rejected");

		}
		
	}
}
