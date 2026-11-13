import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/** Reads web log entries from a text file. */
public class LogfileReader implements Iterator<LogEntry>
{
    private String format;
    private ArrayList<LogEntry> entries;
    private Iterator<LogEntry> dataIterator;

    public LogfileReader(String filename)
    {
        format = "Year Month(1-12) Day Hour Minute";
        entries = new ArrayList<>();
        boolean dataRead;
        try {
            Path filePath = Paths.get(filename);
            List<String> lines = Files.readAllLines(filePath);
            for(String logline : lines) {
                String[] parts = logline.split(" ");
                int[] values = Arrays.stream(parts)
                                     .mapToInt(Integer::parseInt)
                                     .toArray();
                entries.add(new LogEntry(values));
            }
            dataRead = true;
        }
        catch(IOException e) {
            System.out.println("Problem encountered: " + e);
            dataRead = false;
        }
        if(!dataRead) {
            System.out.println("Failed to read the data file: " + filename);
            System.out.println("Using simulated data instead.");
            createSimulatedData(entries);
        }
        Collections.sort(entries);
        reset();
    }

    public LogfileReader()
    {
        this("weblog.txt");
    }

    public boolean hasNext()
    {
        return dataIterator.hasNext();
    }

    public LogEntry next()
    {
        return dataIterator.next();
    }

    public void remove()
    {
        System.err.println("It is not permitted to remove entries.");
    }

    public String getFormat()
    {
        return format;
    }

    public void reset()
    {
        dataIterator = entries.iterator();
    }

    public void printData()
    {
        for(LogEntry entry : entries) {
            System.out.println(entry);
        }
    }

    private void createSimulatedData(ArrayList<LogEntry> data)
    {
        LogfileCreator creator = new LogfileCreator();
        for(int i = 0; i < 100; i++) {
            data.add(creator.createEntry());
        }
    }
}
