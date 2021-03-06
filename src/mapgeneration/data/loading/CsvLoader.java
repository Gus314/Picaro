package mapgeneration.data.loading;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CsvLoader
{
    public static Collection<Map<String, Object>> load(String filePath, java.util.Map<String, Class> parameters)
    {
        Collection<java.util.Map<String, Object>> result = new ArrayList<Map<String, Object>>();

        Scanner scanner = new Scanner(CsvLoader.class.getClassLoader().getResourceAsStream(filePath));
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
                    if(value.equals("_"))
                    {
                        continue;
                    }
                }
                next.put(name, value);
            }
            result.add(next);
        }

        return result;
    }
}
