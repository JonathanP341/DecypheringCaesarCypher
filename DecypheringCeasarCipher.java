/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decypheringceasarcipher;


import java.util.*;
public class DecypheringCeasarCipher {

    /**
     * @param args the command line arguments
     */
    
    /*
    findingDiffs
    
    Will take all of the possible cipher shifts and if it appears multiple times it will
    be added to another list that is outputted to the user
    
    Parameters ArrayList<int>
    
    Returns ArrayList<int>
    */
    public static ArrayList<Integer> findingDiffs(ArrayList<Integer> potentialDiffs) {
        ArrayList<Integer> diffs = new ArrayList<>();
        for (int i = 0; i < potentialDiffs.size()-1; i++) {
            if (potentialDiffs.get(i) == potentialDiffs.get(i+1)) {
                diffs.add(potentialDiffs.get(i));
            }
        }
        return(diffs);
    }
    
    /*
    removeDuplicates
    
    Takes a list and will remove the duplicate integers that were inputted in
    the last method. It adds all of the contents into a LinkedHashset that can only
    have one of each value then clears the arrayList before setting it to the values
    in the set
    
    Parameters ArrayList<Integer>
    
    Returns ArrayList<Integer>
    */
    public static ArrayList<Integer> removeDuplicates(ArrayList<Integer> diffs) {
        Set<Integer> set = new LinkedHashSet<>();
        set.addAll(diffs);
        diffs.clear();
        diffs.addAll(set);
        return diffs;
    }
    
    public static void main(String[] args) {
        //Sharing work on monday
        
        Scanner input = new Scanner(System.in);
        String encrypted;
        String decrypted = "";
        char temp;
        int asciiValue = 96;
        int caesarCipher;
        double largest = 0;
        int indexHolder;
        String space = " ";
        
        //Holding the percentages of each of the most popular letters in english
        final int E = 12;
        final int T = 9;
        final int A = 8;
        final int O = 8;
        final int I = 7;
        final int N = 7;
        final int S = 6;
        
        int [ ] englishPercent = {E, T, A, O, I, N, S};
        int [ ] englishPercentascii = {101, 116, 97, 111, 105, 110, 115};
        
        Double [ ] letterFreq = new Double[26];
        
        //Making method here eventually to find make sure the input is atleast 100 characters
        System.out.println("Enter a string with atleast 100 character: ");
        encrypted = input.nextLine();
        encrypted = encrypted.toLowerCase();
        
        //Setting each value of the array to 0 so i dont get an error
        for (int i = 0; i < letterFreq.length; i++) {
            letterFreq[i] = 0.0;
        }
        
        //Finding the amount of each number
        for (int i = 0; i < encrypted.length(); i++) {
            temp = encrypted.charAt(i);
            asciiValue = (int)temp;
            switch (asciiValue) { 
                case 97:
                    letterFreq[0] += 1;
                    break;
                case 98:
                    letterFreq[1] += 1;
                    break;
                case 99:
                    letterFreq[2] += 1;
                    break;
                case 100:
                    letterFreq[3] += 1;
                    break;    
                case 101:
                    letterFreq[4] += 1;
                    break;
                case 102:
                    letterFreq[5] += 1;
                    break;
                case 103:
                    letterFreq[6] += 1;
                    break; 
                case 104:
                    letterFreq[7] += 1;
                    break;
                case 105:
                    letterFreq[8] += 1;
                    break; 
                case 106:
                    letterFreq[9] += 1;
                    break; 
                case 107:
                    letterFreq[10] += 1;
                    break; 
                case 108:
                    letterFreq[11] += 1;
                    break;   
                case 109:
                    letterFreq[12] += 1;
                    break; 
                case 110:
                    letterFreq[13] += 1;
                    break;
                case 111:
                    letterFreq[14] += 1;
                    break;  
                case 112:
                    letterFreq[15] += 1;
                    break;  
                case 113:
                    letterFreq[16] += 1;
                    break; 
                case 114:
                    letterFreq[17] += 1;
                    break; 
                case 115:
                    letterFreq[18] += 1;
                    break;  
                case 116:
                    letterFreq[19] += 1;
                    break; 
                case 117:
                    letterFreq[20] += 1;
                    break; 
                case 118:
                    letterFreq[21] += 1;
                    break; 
                case 119:
                    letterFreq[22] += 1;
                    break;   
                case 120:
                    letterFreq[23] += 1;
                    break; 
                case 121:
                    letterFreq[24] += 1;
                    break;    
                case 122:
                    letterFreq[25] += 1;
                    break;       
            }
        }
        
        
        //Converting to percentage
        for (int i = 0; i < letterFreq.length; i++) {
            letterFreq[i] = letterFreq[i] / encrypted.length();
        }
        
        
        int [ ] biggestFreq = new int[7]; //Holding the percantages of the most common letters
        int [ ] asciiOfFreq = new int[7]; //Holding the index of the most common letters
        
        //Finding the most common letters and their index
        for (int i = 0; i < 7; i++) {
            indexHolder = 0;
            for (int j = 0; j < letterFreq.length; j++) {
                if (letterFreq[j] > largest) {
                    largest = letterFreq[j];
                    indexHolder = j;
                }
            }
            largest = Math.round(largest*100); //Rounding to percent
            biggestFreq[i] = (int)largest;
            letterFreq[indexHolder] = 0.0; //Setting the index of the former biggest to 0
            asciiOfFreq[i] = indexHolder + 97; //Converting the letter to ascii
            largest = 0;
        }
        
        ArrayList<Integer> potentialDiffs = new ArrayList<>();
        //Need nested for loop, we every differential there is all 35 of them
        for (int i = 0; i < biggestFreq.length; i++) {
             for (int j = 0; j < englishPercent.length; j++) {
                 potentialDiffs.add(asciiOfFreq[i] - englishPercentascii[j]);
             }      
        }
        
        //Sorting list
        Collections.sort(potentialDiffs);
        
        //Finding most common number
        ArrayList<Integer> diffs = new ArrayList<>();
        diffs = findingDiffs(potentialDiffs);
        
        //Finding most common numbers again
        diffs = findingDiffs(diffs);
        
        //Removing duplicates
        diffs = removeDuplicates(diffs);
        
        //Runs for the values in diffs
        for (int i = 0; i < diffs.size(); i++) {
            caesarCipher = diffs.get(i); //Caesarcipher is the value of diffs at i
            
            for (int j = 0; j < encrypted.length(); j++) { //Runs through every value of the encrypted message
                temp = encrypted.charAt(j); //Temp is the individual character
                asciiValue = (int)temp; //Converted to int
                asciiValue = asciiValue - caesarCipher; //Subtracting the value of the cipher from the asciivalue
                if (asciiValue <= (46 + caesarCipher)){ //If the int is less than 46( period in ascii) + cipher as non letters arnt effected
                    if ((asciiValue + caesarCipher == 32)) { //If the asciiValue + cipher is 32(ascii value of space) make temp = " "
                        temp = space.charAt(0);
                    }
                    else { //Else make temp the asciiValue + cipher
                        temp = (char)(asciiValue + caesarCipher);
                    }
                }
                else { //If the value is a number, the way it is circular is dependant on the cipher being positive or negative
                    if (asciiValue < 97) { //If less than 97
                        asciiValue += 26; //Add 26 because it is circualar
                    }
                    else if (asciiValue > 122) { //If greater than 122
                        asciiValue -= 26; //Suibtract 26 because its circular
                    }
                    temp = (char)asciiValue; 
                }
                decrypted += String.valueOf(temp); //Adding character to decrypted
            }
            System.out.println(decrypted);
            decrypted = "";
        }
        
    }
    
}
