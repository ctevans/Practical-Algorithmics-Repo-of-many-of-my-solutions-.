import sun.security.util.BigInt;

import javax.naming.BinaryRefAddr;
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * UVa 10193, All You Need Is Love.
 * Involves intake of Strings of binary numbers.
 * Binary numbers translated into their decimal counterpart using standard translation.
 * GCD of the numbers is calculated using Euclid's algorithm.
 * If GCD != 1, print "All you need is love" (with formatting of course).
 * If GCD == 1, print "Love is not all you need!" (with formatting of course).
 * Done :) <3.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        //Like freopen!
        //System.setIn(new FileInputStream(new File("allyouneedisinputlove.txt")));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        //Obtain the first number, which indicates the number of test cases that will be provided.
        int numberOfTestCases = Integer.parseInt(bufferedReader.readLine());

        //For each of the test cases, loop.
        for (int i = 0; i < numberOfTestCases; i++){

            //Initialize variables.
            String binaryString1, binaryString2;
            long number1, number2;

            //Read input.
            binaryString1 = bufferedReader.readLine();
            binaryString2 = bufferedReader.readLine();

            //Translate Binary String input into decimal form to be used.
            number1 = translateBinary(binaryString1);
            number2 = translateBinary(binaryString2);

            //Obtain the GCD of the two decimal numbers.
            long gcdOfNumbers = euclidAlgo(number1,number2);

            //Print out specific statements depending on if GCD == 1 or if GCD != 1.
            if (!(gcdOfNumbers==1)){

                System.out.println("Pair #" + (i+1) + ": All you need is love!" );
            }
            else{
                System.out.println("Pair #" + (i+1) + ": Love is not all you need!" );
            }
        }

        //Print out a finishing line to match expeted output for UVa.
        //System.out.println();
    }

    /**
     * Standard Euclid's algorithm, given two numbers (long format) it will calculate and return the GCD.
     * @param a Long integer
     * @param b Long integer
     * @return The GCD of a and b.
     */
    public static long euclidAlgo(long a, long b){

        if (b == 0){
            return a;
        }
        else{
            return euclidAlgo(b, a%b);
        }

    }

    /**
     * Translates a Binary String into a Long number.
     * @param binaryString Takes in a String of binary input.
     * @return A translated long value of the decimal form of the binary input provided.
     */
    public static long translateBinary(String binaryString){

        //Initialize Variables.
        long convertedNumber = 0;
        long powerOfTwoContribution = 1;

        //This loop will iterate until the length of the string == 0.
        while (binaryString.length() > 0){

            //Only execute this statement if the last character in the string is a 1.
            if (binaryString.length() > 0 && binaryString.charAt(binaryString.length()-1)=='1'){
                //Remove the last character of the String.
                binaryString = binaryString.substring(0,binaryString.length()-1);
                //Update the sum of the converted number.
                convertedNumber  = convertedNumber + powerOfTwoContribution;
                //Update the power of two contribution to the formulae.
                powerOfTwoContribution = powerOfTwoContribution *2;

            }

            //Only execute this statement if the last character in the string is a 0.
            else if (binaryString.length() > 0 && binaryString.charAt(binaryString.length()-1)=='0'){
                //Remove the last character of the String.
                binaryString = binaryString.substring(0,binaryString.length()-1);
                //Do NOT add to binary string if the string value == 0!
                powerOfTwoContribution = powerOfTwoContribution *2;
            }

        }
        //Return the Long number of the translated Binary String that was passed into the function.
        return convertedNumber;
    }


}

