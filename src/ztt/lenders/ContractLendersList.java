package ztt.lenders;


import java.util.ArrayList;


/**
 * Created by invisible on 16.07.2018.
 */
public class ContractLendersList {

    private ArrayList<Lender> lenders = new ArrayList<>();

    public ContractLendersList(LendersList lendersList, int amount) {
        int sumOfAvailible = 0;
        for (Lender lender : lendersList.getLendersList()) {
            sumOfAvailible += lender.getAmount();
            if (sumOfAvailible <= amount) {
                lenders.add(lender);
                lender.setContractAmount(lender.getAmount());
            } else if (sumOfAvailible - amount == lender.getAmount()) break;
            else {
                lenders.add(new Lender(lender, amount + lender.getAmount() - sumOfAvailible));
                break;
            }
        }

    }

    public ArrayList<Lender> getLenders() {
        return lenders;
    }
}
