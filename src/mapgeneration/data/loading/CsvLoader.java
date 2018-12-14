package mapgeneration.data.loading;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CsvLoader
{
    public static Collection<Map<String, Object>> load(String filePath, java.util.Map<String, Class> parameters)
    {
        Collection<java.util.Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        try
        {
            Scanner scanner = new Scanner(new File(filePath));
            // Skip header line.
            if(scanner.hasNextLine())
            {
                scanner.nextLine();
            }
            while(scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                line = line.replace(' ', '_');
                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter(";");

                java.util.Map<String, Object> next = new HashMap<String, Object>();
                for(String name: parameters.keySet())
                {
                    Object value = null;
                    if(parameters.get(name).equals(Integer.TYPE))
                    {
                        value = Integer.parseInt(lineScanner.next());
                    }
                    else
                    {
                        value = lineScanner.next().replace('_', ' ');
                    }
                    next.put(name, value);
                }
                result.add(next);
            }
        }
        catch(FileNotFoundException fnfex)
        {
            System.out.println("CsvLoader::load() - unable to find file.");
        }

        return result;
    }
}
