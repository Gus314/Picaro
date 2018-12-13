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
            scanner.useDelimiter(";");
            // Skip header line.
            if(scanner.hasNextLine())
            {
                scanner.nextLine();
            }
            while(scanner.hasNextLine())
            {
                java.util.Map<String, Object> next = new HashMap<String, Object>();
                for(String name: parameters.keySet())
                {
                    Object value = null;
                    if(parameters.get(name).equals(Integer.TYPE))
                    {
                        value = Integer.parseInt(scanner.next());
                    }
                    else
                    {
                        value = scanner.next();
                    }
                    next.put(name, value);
                }
                result.add(next);
                scanner.nextLine();
            }
        }
        catch(FileNotFoundException fnfex)
        {
            System.out.println("CsvLoader::load() - unable to find file.");
        }

        return result;
    }
}
