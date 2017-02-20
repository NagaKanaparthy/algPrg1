import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class projOne{
    public static void main(String[] args){
        TestPair temp = new TestPair();
        ArrayList<TestPair> testPairs = temp.createTestPairs();
        System.out.printf("Test Value One,Test Value Two,Euclid GCD solution, Euclid Time,"
        + " Exhaustive GCD solution, Exhaustive GCD Time\n");
        for(TestPair pair : testPairs){
            runBothAndPrint(pair.valueOne, pair.valueTwo);
        }

    }
    private static void runBothAndPrint(BigInteger randOne, BigInteger randTwo){
        long startTime = System.nanoTime();
        BigInteger solution = euclidGCD(randOne,randTwo);
        long endtime = System.nanoTime();
        long euclidTime = endtime - startTime;
        startTime = System.nanoTime();
        BigInteger solutionEx = exhaustiveGCD(randOne,randTwo);
        endtime = System.nanoTime();
        long exhaustiveTime = endtime - startTime;
        System.out.printf("%d,%d,%d,%d,%d,%d\n", randOne,randTwo,solution,euclidTime,solutionEx,exhaustiveTime);
    }
    private static BigInteger euclidGCD(BigInteger valueOne, BigInteger valueTwo){
        if(valueOne.compareTo(valueTwo) < 0){ //valueOne < valueTwo
            BigInteger temp =  valueOne;
            valueOne = valueTwo;
            valueTwo = temp;
        }
        if(valueOne.equals(BigInteger.ONE) && valueTwo.equals(BigInteger.ZERO)){
            return null;
        }
        if(valueTwo.equals(BigInteger.ZERO)){
            return valueOne;
        }
        return euclidGCD(valueTwo, valueOne.mod(valueTwo));
    }
    private static BigInteger exhaustiveGCD(BigInteger valueOne, BigInteger valueTwo){
        ArrayList<BigInteger> primesOne = new ArrayList<BigInteger>();
        ArrayList<BigInteger> primesTwo = new ArrayList<BigInteger>();
        BigInteger startValue = valueOne;
        //find value one factors
        while(startValue.compareTo(BigInteger.ONE) == 1){
            if(valueOne.mod(startValue).compareTo(BigInteger.ZERO) == 0){
                primesOne.add(startValue);
            }
            startValue = startValue.subtract(BigInteger.ONE);
            //System.out.printf("%d\n",startValue);
        }
        startValue = valueTwo;
        //find value two factors
        while(startValue.compareTo(BigInteger.ONE) == 1){
            if(valueTwo.mod(startValue).compareTo(BigInteger.ZERO) == 0){
                primesTwo.add(startValue);
            }
            startValue = startValue.subtract(BigInteger.ONE);
        }
        //Find GCD
        for(BigInteger i:primesOne){
            if(primesTwo.contains(i))
                return i;
        }
        return null;
    }
}
class TestPair{
        public BigInteger valueOne;
        public BigInteger valueTwo;
        public TestPair(){
        }
        public TestPair(BigInteger valOne, BigInteger valTwo){
            valueOne = valOne;
            valueTwo = valTwo;
        }
        public ArrayList<TestPair> createTestPairs(){
            ArrayList<TestPair> testPairs = new ArrayList<TestPair>();
            long tempO, tempT;
            for(int i = 3; i < 32;i++){              
                tempO = ThreadLocalRandom.current().nextLong( 
                    BigInteger.valueOf(2).pow(i-1).longValue(),
                    BigInteger.valueOf(2).pow(i).longValue());
                tempT = ThreadLocalRandom.current().nextLong( 
                    BigInteger.valueOf(2).pow(i-1).longValue(),
                    BigInteger.valueOf(2).pow(i).longValue());
                testPairs.add(new TestPair(BigInteger.valueOf(tempO), BigInteger.valueOf(tempT)));
            }
            return testPairs;
        }
    }