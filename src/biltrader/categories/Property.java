package biltrader.categories;

/**
 * A class representing a Property with a generic type
 * @author Mohamed Eldeeb
 * @version 11.04.2021
 */
public class Property<E>
{
    /** Name of the property */
    private String name;
    
    /** Value of the property */
    private E value;
    
    /**
     * Default constructor. Creates a new property with the given name and value.
     * @param name  name of the property
     * @param value value of the property
     */
    public Property(String name, E value)
    {
        this.name = name;
        this.value = value;
    }
    
    /** @return name of the property */
    public String getName()
    {
        return name;
    }
    
    /** @return value of the property */
    public E getValue()
    {
        return value;
    }
    
    /** @return value of the property as a String */
    public String getValueFormatted()
    {
        return value.toString();
    }
    
    /** @param newName name to set the property's name to */
    public void setName(String newName)
    {
        name = newName;
    }
    
    /** @param newValue value to set the property's value to */
    public void setValue(E newValue)
    {
        value = newValue;
    }
}