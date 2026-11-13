import java.util.Calendar;

/** Stores one web log entry. */
public class LogEntry implements Comparable<LogEntry>
{
    private int[] dataValues;
    private Calendar when;
    private static final int YEAR = 0, MONTH = 1, DAY = 2, HOUR = 3, MINUTE = 4;
    private static final int NUMBER_OF_FIELDS = 5;

    public LogEntry(int[] dataValues)
    {
        this.dataValues = dataValues;
        setWhen();
    }

    public LogEntry(int year, int month, int day, int hour, int minute)
    {
        dataValues = new int[NUMBER_OF_FIELDS];
        dataValues[YEAR] = year;
        dataValues[MONTH] = month;
        dataValues[DAY] = day;
        dataValues[HOUR] = hour;
        dataValues[MINUTE] = minute;
        setWhen();
    }

    public int getHour()
    {
        return dataValues[HOUR];
    }

    public int getMinute()
    {
        return dataValues[MINUTE];
    }

    public String toString()
    {
        StringBuilder theData = new StringBuilder();
        for(int value : dataValues) {
            if(value < 10) {
                theData.append('0');
            }
            theData.append(value);
            theData.append(' ');
        }
        return theData.toString().trim();
    }

    public int compareTo(LogEntry otherEntry)
    {
        return when.compareTo(otherEntry.getWhen());
    }

    private Calendar getWhen()
    {
        return when;
    }

    private void setWhen()
    {
        when = Calendar.getInstance();
        when.set(dataValues[YEAR], dataValues[MONTH] - 1, dataValues[DAY] - 1,
                 dataValues[HOUR], dataValues[MINUTE]);
    }
}
