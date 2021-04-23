package biltrader.localization;

/**
 * A class representing a Language, which can be used to translate text
 * between different langauges
 * @author Mohamed Eldeeb
 * @version 11.04.2021
 */
public class Language
{
    /** A representation of this language's name
     * (e.g. EN for English, TR for Turkish)
     * */
    private String lang;
    
    /**
     * Default constructor. Creates a Language with the given name.
     * @param lang name of the language
     */
    public Language(String lang)
    {
        this.lang = lang;
    }
    
    /** @return name of the language */
    public String getLanguageName()
    {
        return "";
    }
    
    private void translate()
    {
        // This is a stub
        // The functionality of this method is not clear yet
    }
    
    /**
     * Translates the given text from this language to the other one
     * @param other language to translate to
     * @param text  text to translate
     * @return      translated text
     */
    public String translateTo(Language other, String text)
    {
        return "";
    }
    
    /**
     * Translates the given text from the other language to this one
     * @param other language to translate to
     * @param text  text to translate
     * @return      translated text
     */
    public String translateFrom(Language other, String text)
    {
        return "";
    }
}