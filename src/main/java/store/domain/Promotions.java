package store.domain;

import java.time.LocalDate;

public class Promotions {

    private final String name;
    private final int buy;
    private final int get;
    private final LocalDate startDate;
    private final LocalDate endDate;

    public Promotions(String name, String buy, String get, String startDate, String endDate) {
        this.name = name;
        this.buy = Integer.parseInt(buy);
        this.get =  Integer.parseInt(get);
        this.startDate = LocalDate.parse(startDate);
        this.endDate = LocalDate.parse(endDate);
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean hasName(String name) {
        return this.name.equals(name);
    }

    public boolean isPromotionApplicable(int quantity) {
        return calculateRemainingItems(quantity) >= get;
    }

    public int calculateRemainingItems(int quantity) {
        return quantity % (buy + get);
    }

    public int calculatePromotionUnits(int quantity) {
        return quantity / (buy + get);
    }

    public boolean isWithinPromotionPeriod(LocalDate date) {
        return (date.isEqual(startDate) || date.isAfter(startDate)) &&
                (date.isEqual(endDate) || date.isBefore(endDate));
    }
}
