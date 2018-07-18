package ztt.contracts;

import ztt.exceptions.CanNotCreateContractException;
import ztt.lenders.ContractLendersList;
import ztt.lenders.Lender;
import ztt.lenders.LendersList;
import ztt.rates.Rate;
import ztt.rates.SVSRate;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.MathContext;


/**
 * Created by invisible on 13.07.2018.
 */
public class SVSContract implements Contract {

    private Rate rate;
    private int amount = 0;
    private int months = 0;
    private BigDecimal monthRepayment = BigDecimal.valueOf(0);
    private BigDecimal totalRepayment = BigDecimal.valueOf(0);
    private ContractLendersList contractLenders;

    public SVSContract(File lendersFile, int amount, int months) throws IOException, CanNotCreateContractException {

        LendersList fullLenderList = new LendersList(lendersFile.getName());

        if (amount <= fullLenderList.getSumOfPool()) {
            contractLenders = new ContractLendersList(fullLenderList, amount);
            this.rate = new SVSRate(contractLenders);
            this.amount = amount;
            this.months = months;
            BigDecimal mRepayment = BigDecimal.valueOf(0); // может быть объявление сделать внутри for? 

            for (Lender lender : contractLenders.getLenders()) {

                mRepayment = calculateMonthRepayment(lender.getContractAmount(), months, rate.getRate());
                monthRepayment = monthRepayment.add(mRepayment);
            }
            totalRepayment = monthRepayment.multiply(BigDecimal.valueOf(months)).setScale(2, BigDecimal.ROUND_HALF_DOWN);

        } else throw new CanNotCreateContractException();

    }

    @Override
    public BigDecimal calculateMonthRepayment(int amount, int months, BigDecimal rate) {
        MathContext mc = new MathContext(5);
        BigDecimal result = BigDecimal.valueOf(0);
        BigDecimal amountAfterPayment = BigDecimal.valueOf(amount);
        BigDecimal valueOfMonthPayment = BigDecimal.valueOf(0);
        BigDecimal payPerMonth = amountAfterPayment.divide(BigDecimal.valueOf(months), mc);
        BigDecimal monthRate = rate.divide(BigDecimal.valueOf(12), mc);

        for (int i = 0; i < months - 1; i++) {
            valueOfMonthPayment = amountAfterPayment.multiply(monthRate, mc);
            result = result.add(payPerMonth.add(valueOfMonthPayment));
            amountAfterPayment = amountAfterPayment.subtract(payPerMonth, mc);
        }
        valueOfMonthPayment = amountAfterPayment.multiply(monthRate, mc);
        result = result.add(amountAfterPayment.add(valueOfMonthPayment));
        return result.divide(BigDecimal.valueOf(months), mc).setScale(2, BigDecimal.ROUND_HALF_DOWN);

    }


    @Override
    public String toString() {
        return "Requested Amount: " + "\u20A4" + amount +
                "\nRate: " + (rate.getRate().multiply(BigDecimal.valueOf(100)).setScale(1)) + "%" +
                "\nMonthly repayment: " + "\u20A4" + monthRepayment +
                "\nTotal repayment: " + "\u20A4" + totalRepayment;
    }
}
