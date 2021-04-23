package biltrader.categories;

/**
 * An interface that represents an object that can be
 * filtered according to its Property objects.
 * @author Mohamed Eldeeb
 * @version 11.04.2021
 */
public interface Filterable
{
    /**
     * Checks if the given property is present.
     * @param p property to check for
     * @return  true if this object has a property with the same name as p
     */
    boolean hasProperty(Property p);
    
    /**
     * Compares a property of a specific name with the one in this object.
     * This method should call hasProperty() and pass it the argument for this
     * method, in order to check if the property exists before comparing it.
     * @param other the property to be compared to the one in this object
     * @return a positive value if the property in this object is greater
     * than "other", or 0 if both properties are equal
     */
    int comparePropertyTo(Property other);
}