package ztt.rates;

import ztt.lenders.ContractLendersList;
import ztt.lenders.Lender;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 13.07.2018.
 */
public class SVSRate implements Rate {

    private BigDecimal rate;

    public SVSRate(ContractLendersList lenders) {
        this.rate = calculate(lenders);
    }

    @Override
    public BigDecimal getRate() {
        return rate;
    }

    private BigDecimal calculate(ContractLendersList list){
        List<Lender> lendersList = list.getLenders();
        double sumPayment = 0;
        int sumAmount = 0;
        for (Lender lender : lendersList){
           sumPayment += lender.getRate()*lender.getContractAmount();
           sumAmount += lender.getContractAmount();
        }
        return BigDecimal.valueOf(sumPayment/sumAmount).setScale(3,BigDecimal.ROUND_HALF_DOWN);
    }


}
