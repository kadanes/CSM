import java.util.*;

class Runs {

    public static void main( String []args) {
        Random rand = new Random();

        ArrayList<Double> randomNumbers = new ArrayList<Double>();
        int length = 10;
        double mean = 0.495;

        for (int i = 0; i < length; i+=1 ){
            randomNumbers.add(Math.round(rand.nextDouble() * 100.0) / 100.0);
        }

        ArrayList<Integer> runsUpDown = new ArrayList<Integer>();
        ArrayList<Integer> runsMean = new ArrayList<Integer>();

        for ( int i = 0; i < length - 1; i+=1) {

            System.out.print(randomNumbers.get(i) +" "); 

            if ( randomNumbers.get(i) < randomNumbers.get(i+1)) {
                runsUpDown.add(1);
            } else {
                runsUpDown.add(-1);
            }

            if ( randomNumbers.get(i) < mean) {
                runsMean.add(-1);
            } else {
                runsMean.add(1);
            }
        }

        System.out.println(randomNumbers.get(length-1));

        if (randomNumbers.get(length-1) < mean) {
            runsMean.add(-1);
        } else {
            runsMean.add(1);
        }

        System.out.println("UP DOWN");
        int[] upDownRunsCount = countRuns(runsUpDown);
        System.out.println("Mean");
        int[] meanRunsCount = countRuns(runsMean);

        int meanUpLength = 0;
        int meanDownLength = 0; 

        for (Integer val: runsMean) {
            if (val > 0) {
                meanUpLength += 1;
            } else if (val < 0){
                meanDownLength += 1;
            }
        }

        //Runs Up Down
        Double nuUpDown = (2*length - 1)/(double) 3;
        Double sigmaUpDown = Math.sqrt((16*length - 29)/(double) 90);
        Double zUpDown = (upDownRunsCount[0]+upDownRunsCount[1] - nuUpDown)/sigmaUpDown;

        //Mean Up Down
        Double nuMean = ( 2*meanUpLength*meanDownLength/(double) length ) + 0.5;
        Double sigmaMean = Math.sqrt((2*meanUpLength*meanDownLength*(2*meanUpLength*meanDownLength - length))/(length*length*(length - 1)));
        Double zMean = ((meanRunsCount[0]+meanRunsCount[1]) - nuMean)/sigmaMean;

        System.out.println("Up Down: "+zUpDown);

        if (-1.96 > zUpDown || zUpDown > 1.96) {
            System.out.println("Up Down Hypothesis Rejected");
        } else {
            System.out.println("Up Down Hypothesis can't be Rejected");
        }

        System.out.println("Mean :"+zMean);

        if (-1.96 > zMean || zMean > 1.96) {
            System.out.println("Mean Hypothesis Rejected");
        } else {
            System.out.println("Mean Hypothesis can't be Rejected");
        }

    }

    static int[] countRuns(ArrayList<Integer> runs) {
        int[] runsCount = new int[2];

        int previous = runs.get(0);
        // System.out.println(runs.size());

        System.out.print(previous+" ");

        if ( previous < 0) {
            runsCount[0] = 1;
            runsCount[1] = 0;
        } else {
            runsCount[0] = 0;
            runsCount[1] = 1;
        }

        for (int i = 1; i < runs.size(); i+=1) {

            int current = runs.get(i);

            if ( current > previous) {
                runsCount[1] = runsCount[1] + 1;
                previous = current;
                // System.out.print(i + ") + "+previous+" ");
            } else if ( current < previous) {
                runsCount[0] =  runsCount[0] + 1;
                previous = current;
                // System.out.print(i + ") - "+previous+" ");
            }

            System.out.print(previous +" ");
            
        }
        System.out.println();
        // System.out.println(runsCount[0] +" "+runsCount[1]);

        return runsCount;
    }
}