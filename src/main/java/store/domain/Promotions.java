package store.domain;

import java.time.LocalDate;

public class Promotions {

    private static final String NAME_BLANK = "";

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
}
