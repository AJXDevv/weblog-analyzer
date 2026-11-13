/**
 * Read web server data and analyse hourly access patterns.
 *
 * @author David J. Barnes and Michael Kölling.
 * @version 7.0
 */
public class LogAnalyzer
{
    public static final int HOURS_PER_DAY = 24;
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer()
    {
        hourCounts = new int[HOURS_PER_DAY];
        reader = new LogfileReader();
    }

    // Question 5: let the user choose which log file to read.
    public LogAnalyzer(String filename)
    {
        hourCounts = new int[HOURS_PER_DAY];
        reader = new LogfileReader(filename);
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }

    /**
     * Print the hourly counts.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        // Question 4: same output, but using a while loop.
        int hour = 0;
        while(hour < hourCounts.length) {
            System.out.println(hour + ": " + hourCounts[hour]);
            hour++;
        }
    }

    // Questions 6 and 7: add up all the hourly counts.
    public int numberOfAccesses()
    {
        int total = 0;
        for(int hour = 0; hour < hourCounts.length; hour++) {
            total += hourCounts[hour];
        }
        return total;
    }

    // Question 8: return the hour with the most visits.
    public int busiestHour()
    {
        int busiest = 0;
        for(int hour = 1; hour < hourCounts.length; hour++) {
            if(hourCounts[hour] > hourCounts[busiest]) {
                busiest = hour;
            }
        }
        return busiest;
    }

    // Question 9: return the hour with the fewest visits.
    public int quietestHour()
    {
        int quietest = 0;
        for(int hour = 1; hour < hourCounts.length; hour++) {
            if(hourCounts[hour] < hourCounts[quietest]) {
                quietest = hour;
            }
        }
        return quietest;
    }

    // Question 11: find the starting hour of the busiest two-hour period.
    public int busiestTwoHours()
    {
        int busiest = 0;
        for(int hour = 1; hour < hourCounts.length - 1; hour++) {
            int current = hourCounts[hour] + hourCounts[hour + 1];
            int highest = hourCounts[busiest] + hourCounts[busiest + 1];
            if(current > highest) {
                busiest = hour;
            }
        }
        return busiest;
    }

    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }

    // Written questions (Part II)
    // Question 1: In the supplied weblog.txt, 18:00 is the busiest hour (237 accesses).
    // Question 2: hourCounts stores the number of visits for each of the 24 hours.
    // Question 3: Using <= tries to read index 24 and causes an array index error.
    // Question 10: If two hours tie, busiestHour returns the earlier one.
    // The loop only updates the result when it finds a strictly larger count.
}