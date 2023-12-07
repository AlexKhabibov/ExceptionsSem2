import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        String path = "/Users/a04/IdeaProjects/ExceptionsLec2/src/main/java/names.txt";
        List<String[]> list = readFile(path);
        modificationArray(list);
        writeFile(list, path);
    }

    public static List<String[]> readFile(String path) {
        List<String[]> listNames = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = "";
            while((line = reader.readLine()) != null){
                listNames.add(line.split("="));
        }
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return listNames;
    }

    public static void modificationArray(List<String[]> listNames){
        for (String[] item : listNames) {
            if (!item[1].equals("?") && !checkNumber(item[1]))
                throw new IllegalArgumentException("Элемент не ? и не число");
            if (item[1].equals("?"))
                item[1] = String.valueOf(item[0].length());
        }
    }

    public static boolean checkNumber (String value) {
        try{
            Integer.parseInt(value);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    public static void writeFile(List<String[]> listNames,String path) {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(path));
            for (String[] item : listNames) {
                writer.write(item[0] + "=" + item[1] + "\n");
            }
            writer.close();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

}
