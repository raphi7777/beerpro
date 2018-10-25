package ch.beerpro.domain.models;

import lombok.Data;

import java.util.Date;

@Data
public class MyBeerFromFridge implements MyBeer {
    private FridgeItem fridgeItem;
    private Beer beer;

    public MyBeerFromFridge(FridgeItem fridgeItem, Beer beer) {
        this.fridgeItem = fridgeItem;
        this.beer = beer;
    }

    @Override
    public String getBeerId() {
        return fridgeItem.getBeerId();
    }

    @Override
    public Date getDate() {
        return fridgeItem.getAddedAt();
    }
}
