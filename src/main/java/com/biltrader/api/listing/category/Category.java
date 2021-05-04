package com.biltrader.api.listing.category;

/**
 * Category enum with an id
 * In the database, categories are stored by index, so they can be parsed using Category.values()[index]
*/
public enum Category {
    // Ids that are a multiple of 100 are higher categories
    // other ids are sub-categories of the category they belong to
    Electronics(100),
    Phones(101),
    Computers(102),
    
    Academics(200),
    Books(201),
    Notebooks(202);
    
    private final int id;

    Category(final int id) {
        this.id = id;
    }

    public int getId() { return id; }
    
    public static Category fromValue(int value) {
        for (Category cat : values()) {
            if (cat.getId() == value) {
                return cat;
            }
        }
        
        return null;
    }
}
