package Exercises.Exercise11Page87;

public class Holiday {
    private String name; 
    private int day; 
    private String month; 

    public Holiday(String name, int day, String month)
    {
        this.name = name;
        this.day = day; 
        this.month = month; 
    }
    public String getName()
    {
        return name;
    }

    public String getMonth()
    {
        return month;
    }
    public int getDay()
    {
        return day; 
    }
    public boolean inSameMonth(Holiday other)
    {
        if ((other.getMonth()).equals(this.month))
        {
            return true;
        }   
        else
        return false; 
    }
}