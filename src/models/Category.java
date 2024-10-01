package models;

import java.util.Objects;

public class Category {
    private String categoryTitle;

    public Category(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public String getCategoryTitle() {
        return this.categoryTitle;
    }

    @Override
    public boolean equals(Object obj) {
        // if the object is literally this one
        if (this == obj)
            return true;

        // if the object we comparing this instance with is either null,
        // or isn't from the same class this one is from
        if (obj == null || getClass() != obj.getClass())
            return false;

        Category category = (Category) obj;
        return Objects.equals(categoryTitle, category.categoryTitle);
    }

    @Override
    // this hashes the object, specifically by its name
    // so that we ensure equal objects have the same hash code
    public int hashCode() {
        return Objects.hash(categoryTitle);
    }
}

/*
 * a = category("math")
 * b = category("math")
 * 
 */