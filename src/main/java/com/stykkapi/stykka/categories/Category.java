package com.stykkapi.stykka.categories;

public enum Category {
//    private SubCategories SUBCATEGORY,
    MEN,
    WOMEN,
    BOY,
    GIRLS;

    @Override
    public String toString() {
        return switch (this){
            case MEN ->  "Men";
            case WOMEN -> "WOMEN";
            case BOY ->  "BOY";
            case GIRLS -> "GIRLS";
        };

    }


}
