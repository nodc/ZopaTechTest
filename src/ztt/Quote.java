package ztt;

import ztt.contracts.SVSContract;
import ztt.exceptions.CanNotCreateContractException;

import java.io.File;
import java.io.IOException;

public class Quote {
    public static final int months = 36;
    private static int amount;

    public static void main(String[] args) throws IOException {

        if (args.length!=2) System.out.println("Input error. Try again.");
        else
            {
            File lendersFile = new File(args[0]);

            if (!lendersFile.exists() || !isAmountCorrect(args[1])) System.out.println("Input error. Try again!");
            else {

                try {
                    SVSContract contract = new SVSContract(lendersFile, amount, months);
                    System.out.println(contract);
                }catch (CanNotCreateContractException e){
                    System.out.println("It is not possible to provide a quote at that time.");
                }

            }
        }
    }

    public static boolean isAmountCorrect(String amountValue){
        try {
            amount = Integer.parseInt(amountValue);
            if (amount % 100!=0 || amount<=0) return false;
             else return true;
        } catch (NumberFormatException e) {
        }
        return false;
    }



}
