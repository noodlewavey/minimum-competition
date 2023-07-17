import java.lang.Math;
import java.util.Arrays;

class Solution {
    public int minNumberOfHours(int initialEnergy, int initialExperience, int[] energy, int[] experience) {
        int trainingEnergy = 0;
        for (int i = 0; i < energy.length; i++) {
            trainingEnergy = trainingEnergy + energy[i];
        }

        int needTrainEnergy = Math.max(0,trainingEnergy + 1 - initialEnergy);

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


//
//this code passes now
//experience = [19, 69, 86]
//energy = [98, 2, 47]
//initialEnergy = 49
//initialExpeirence=25;
//returns 26 for training experience
//125 in total


///now this fails case 108

//energy = [69,22,93,68,57,76,54,72,8,78,88,15,58,61,25,70,58,91,79,22,91,74,90,75,31,53,31,7,51,96,76,17,64,89,83,54,28,33,32,45,20]
//exp = [51,81,46,80,56,7,46,74,64,20,84,66,13,91,49,30,75,43,74,88,82,51,72,4,80,30,10,19,40,27,21,71,24,94,79,13,40,28,63,85,70]
//initialenergy = 11;
//initialexperience= 23;

//last maxdiff should be 2323-2294 (2294 is the training for energy)...//
//output is 2302
//but should be 2323
//last maxdiff should be 29
//last debug
