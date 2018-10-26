package control;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Formatter;
import java.util.Scanner;

public class GraveStore
{
    private static final String graveFilename = "graveyard";

    public static Collection<Grave> getGraves()
    {
        Collection<Grave> result = new ArrayList<Grave>();

        File graveFile = new File(graveFilename);
        if(graveFile.exists())
        {
            try
            {
                Scanner scanner = new Scanner(graveFile);
                while(scanner.hasNextLine())
                {
                    Grave grave = new Grave(scanner.nextLine());
                    result.add(grave);
                }
                scanner.close();
            }
            catch (FileNotFoundException e)
            {
                System.out.println("GraveStore::getGraves() - failed to read graves.");
            }

        }

        return result;
    }

    public static void addGrave(Grave grave)
    {
        try
        {
            FileWriter graveFile = new FileWriter(graveFilename, true);
            Formatter formatter = new Formatter(graveFile);
            formatter.format(grave.toString() + "\n");
            formatter.close();
        }
        catch (IOException e)
        {
                System.out.println("GraveStore::addGrave() - Failed to write grave file.");
        }
    }
}
