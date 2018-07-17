package ztt.lenders;

/**
 * Created by invisible on 12.07.2018.
 */
public class Lender implements Comparable<Lender> {
    private String name;
    private double rate;
    private int amount;
    private int contractAmount;

    public Lender(String name, double rate, int amount) {
        this.name = name;
        this.rate = rate;
        this.amount = amount;
        contractAmount = 0;
    }

    public Lender(Lender lender, int contractAmount) {
        this.name = lender.getName();
        this.rate = lender.getRate();
        this.amount = lender.getAmount();
        this.contractAmount = contractAmount;
    }


    public int getContractAmount() {
        return contractAmount;
    }

    public void setContractAmount(int contractAmount) {
        this.contractAmount = contractAmount;
    }

    public int getAmount() {
        return amount;
    }

    public double getRate() {

        return rate;
    }

    public String getName() {

        return name;
    }

    @Override
    public int compareTo(Lender o) {
        if (rate > o.rate) return 1;
        if (rate == o.rate) return 0;
        return -1;
    }
}
