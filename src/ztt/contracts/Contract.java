package ztt.contracts;

import ztt.rates.Rate;

import java.math.BigDecimal;

/**
 * Created by invisible on 13.07.2018.
 */
public interface Contract  {

    public BigDecimal calculateMonthRepayment(int amount,int months, BigDecimal rate);

}

