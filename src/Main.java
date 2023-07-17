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
        System.out.println(needTrainExperience + "training for experience");

        int totalTraining = needTrainEnergy + needTrainExperience;
        return totalTraining;
    }

    public int recursiveMax(int initialExperience, int[] experience, int maxnumber, int maxIndex) {
        int sumAll = 0;
        for (int i = 0; i < maxIndex; i++) {
            sumAll = sumAll + experience[i];
        }

        if (experience.length == 1) {
            int calc = experience[0]+1-(initialExperience);
            System.out.println(maxnumber + "biggest number");
            System.out.println(calc);
            return Math.max(calc, 0);
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
            int[] energy = {58,47,100,71,47,6,92,82,35,16,50,15,42,5,2,45,22};
            int[] experience = {77,83,99,76,75,66,58,84,44,98,70,41,48,7,10,61,28};
            int initialEnergy = 94;
            int initialExperience = 70;
            System.out.println(s.minNumberOfHours(initialEnergy, initialExperience, energy, experience));
        }
    }


