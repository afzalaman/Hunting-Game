package huntingGame;

/**
 *
 * @author aliafzal
 */

public class Slot 
{
    String value;
    
    public Slot()
    {
        value = "";
    }
    
    public Slot(String val)
    {
        value = val;
    }
    
    public void SetValue(String val)
    {
        value = val;
    }
    
    public String GetValue()
    {
        return value;
    }
    
    public Boolean isEmpty()
    {
        return value.equals("");
    }
    
}
