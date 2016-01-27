import java.io.*;
import java.math.BigInteger;

/**
 * UVa 374 Big Mod.
 * Modular exponentiation done really fast.
 * R = B^P mod M
 *
 * I already had a hunch from a prior problem (Where I suffered greatly learning BigInteger type) that I could use
 * that specific type in this program and then use the modexp function associated with it. So this was pretty simple.
 */
public class Main {

    public static void main(String[] args) throws IOException {
        //Like freopen in c++. Opens the selected file and treats it as if it were stdin.
        //Mostly used for testing.
        //System.setIn(new FileInputStream(new File("inputFile.txt")));

        //Take input from stdin using a Bufferedreader.
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while((line = bufferedReader.readLine()) != null && !("".equals(line))) {

            while(!("".equals(line)) && (line != null)){
                //Read in B from the input given to us from stdin.
                BigInteger b = new BigInteger(line);
                //System.out.println("B = " + line);
                line = bufferedReader.readLine();

                //Read in P from the input given to us from stdin.
                BigInteger p = new BigInteger(line);
                //System.out.println("P = " + line);
                line = bufferedReader.readLine();

                //Read in M from the input given to us from stdin.
                BigInteger m = new BigInteger(line);
                //System.out.println("M = " + line);
                line = bufferedReader.readLine();

                function(b,p,m);
            }

        }

    }

    /**
     * Takes in B, P and M in the modular exponentiation formula and calculates the value.
     * Fortunately I already had (painful, really PAINFUL) experiences with BigInteger in a diferent problem... so I
     * knew this would be a viable strategy! HUZZAH! THAT PAIN WAS NOT FOR NOTHING!!!!! :D
     *
     * @param b BigInteger value of "B" variable in the modular exponentiation formulae.
     * @param p BigInteger value of "P" variable in the modular exponentiation formulae.
     * @param m BigInteger value of "M" variable in the modular exponentiation formulae.
     */
    public static void function(BigInteger b, BigInteger p, BigInteger m){

        System.out.println(b.modPow(p,m));
        //return(b.modPow(p,m));

    }

}
