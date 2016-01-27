import java.io.*;
import java.util.*;

/**
 * I can guess the data structure UVa Problem #11995
 */
public class Main {
    public static void main(String[] args) throws IOException {
        //System.setIn(new FileInputStream(new File("inputFile.txt")));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));



        int numberOfCases;
        String inputLine;
        while ((inputLine = bufferedReader.readLine()) != null){
            numberOfCases = Integer.parseInt(inputLine);
            //System.out.println(numberOfCases);
            /**The three main data structures, in the most ... simple way. Note: PriorityQueue is largest first.**/
            Stack<Integer> stack = new Stack<Integer>();
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>(numberOfCases,Collections.reverseOrder());
            Queue<Integer> queue = new LinkedList<>();
            stack.clear();
            priorityQueue.clear();
            queue.clear();

            String line;

            //Set of 3 boolean flags that will determine the final output. Reset to True every case.
            boolean isItAQueue = true;
            boolean isItAStack = true;
            boolean isItAPriorityQueue = true;

            for (int i = 0; i < numberOfCases; i++){
                line = bufferedReader.readLine();

                String[] splitLine = line.split(" ");
                int commandType = Integer.parseInt(splitLine[0]); //First number will be the command.
                int objectID = Integer.parseInt(splitLine[1]); //Second number will identify the object.

                //System.out.println(commandType + " " + objectID);
                if (commandType == 1){
                    //Place the Object (objectID) into each data structure
                    queue.add(objectID);
                    priorityQueue.add(objectID);
                    stack.push(objectID);

                }

                else if (commandType == 2){
                    //Remove Object (objectID) from the data structure and compare if it matches expected output.


                    if (queue.size() == 0 ){
                        isItAQueue = false;
                    }

                    if (stack.size() == 0){
                        isItAStack = false;
                    }

                    if (priorityQueue.size() == 0){
                        isItAPriorityQueue = false;
                    }

                    if (queue.size() != 0){
                        int expectedObjectID1 = queue.remove();
                        int expectedObjectID2 = stack.pop();
                        int expectedObjectID3 = priorityQueue.remove();

                        //Set to false if it doesn't match the actual output.
                        if (expectedObjectID1 != objectID){
                            isItAQueue = false;
                        }
                        if (expectedObjectID2 != objectID){
                            isItAStack = false;
                        }
                        if (expectedObjectID3 != objectID){
                            isItAPriorityQueue = false;
                        }
                    }


                }

            }

            /**Output is determined here by looking at the boolean flags **/
            if (isItAQueue && !isItAPriorityQueue && !isItAStack){
                System.out.println("queue");
            }

            else if (!isItAQueue && !isItAPriorityQueue && isItAStack){
                System.out.println("stack");
            }
            else if (!isItAQueue && isItAPriorityQueue && !isItAStack){
                System.out.println("priority queue");
            }

            else if (isItAQueue || isItAPriorityQueue || isItAStack){
                System.out.println("not sure");
            }

            else{
                System.out.println("impossible");
            }

            /** //Testing stuff.
             System.out.println("Q");
             for (int i = 0; i < 3; i++){
             System.out.println(queue.remove());

             }
             System.out.println("ST");
             for (int i = 0; i < 3; i++){
             System.out.println(stack.pop());

             }
             System.out.println("PQ");
             for (int i = 0; i < 3; i++){
             System.out.println(priorityQueue.remove());

             }**/

        }
        }



}
