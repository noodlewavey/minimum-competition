import java.lang.Math;
import java.util.Arrays;

class Solution {
    public int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        int trainingEnergy = 0;
        for (int i = 0; i < energy.length; i++) {
            trainingEnergy = trainingEnergy + energy[i];
        }

        int needTrainEnergy = trainingEnergy + 1 - initialEnergy;

        System.out.println(needTrainEnergy + "needs this much training for energy");

        int biggestIndex = findMaxIndex(experience);
        int biggestNumber = experience[biggestIndex];

        int needTrainExperience = recursiveMax(initialExperience, experience, biggestNumber, biggestIndex);
        System.out.println(needTrainExperience + " training for experience");


        int totalTraining = needTrainEnergy + needTrainExperience;
        return totalTraining;
    }

    public int recursiveMax(int initialExperience, int[] experience, int maxnumber, int maxIndex) {
        int sumAll = 0;
        for (int i = 0; i < maxIndex; i++) {
            sumAll = sumAll + experience[i];
        }

        if (experience.length == 1) {
            int firstTwo = experience[0]+initialExperience;
            System.out.println("firsttwo" + firstTwo);
            System.out.println("is greeater than maxnumber" + (firstTwo > maxnumber));
            if (experience[0] < initialExperience && (firstTwo > maxnumber)){
                return 0;
            }
            else if (experience[0]>=initialExperience){
                int calc = experience[0]+1-(initialExperience);
                //calc could only be positive?
                return Math.max(calc,0);
            }
            else{
                int compareToSecond = maxnumber+1 - experience[0]-initialExperience;
                return Math.max((compareToSecond),0);

            }
        }

        if ((sumAll + initialExperience) <= maxnumber) {
            System.out.println(sumAll+initialExperience + "this is the sum of all experience");
            int maxdiff = maxnumber - initialExperience + 1;
            System.out.println(maxdiff + "initial maxdiff");
            for (int i = 0; i < maxIndex; i++) {

                maxdiff = maxdiff - experience[i];
            }
            System.out.println(maxdiff + "this is the last maxdiff");
            return Math.max(maxdiff, 0);
            //this is what returns 77

        } else {
            int[] newExperience = Arrays.copyOfRange(experience, 0, experience.length - 1);
            return recursiveMax(initialExperience, newExperience, experience[findMaxIndex(experience)],findMaxIndex(experience));
            //maxnumber is not an index
        }
    }

    public int findMaxIndex ( int[] experience){
        int maxIndex = 0;
        int max = 0;
        for (int i = 0; i < experience.length; i++) {
            if (experience[i] > max) {
                max = experience[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public static void main(String[] args){
        Solution s = new Solution();
        int[] energy = {98, 2, 47};
        int[] experience = {19, 69, 86};
        int initialEnergy = 49;
        int initialExperience = 25;
        System.out.println(s.minNumberOfHours(initialEnergy, initialExperience, energy, experience));
        //supposed to return 26 for experience training
    }
}


//this code fails
//experience = [19, 69, 86]
//energy = [98, 2, 47]
//initialEnergy = 49
//initialExpeirence=25;