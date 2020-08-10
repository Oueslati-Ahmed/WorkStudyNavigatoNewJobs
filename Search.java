import java.io.*;
import java.util.regex.*;
import java.util.Iterator;
import java.util.Set;
import java.util.HashMap;
import java.util.Properties;
import java.util.Map;

public class Search {
    public static void main(String[] args) throws IOException {
        int[] jobs_old = read("C:\\Users\\Oueslati.A\\Downloads\\Jobs.csv");
        HashMap<Integer, Integer> hash1 = new HashMap<Integer, Integer>();
        for (int c : jobs_old) {
            if (!hash1.containsKey(c)) {
                hash1.put(c, 1);
            }
        }
        boolean found,print;
        found=print=false;
        HashMap<Integer, Integer> hash2 = load();
        Set<Integer> keys = hash1.keySet();
        Iterator<Integer> it = keys.iterator();
        while(it.hasNext()){
            int x = it.next();
            if (!hash2.containsKey(x)) {
                if (!print) {
                    System.out.println("Jobs u didnt apply to:");
                    print=true;
                }
                System.out.println(x);
                hash2.put(x, 1);
                found = true;
            }
         }

        if (!found) {
            System.out.println("you've seen every job already");
        }

        save(hash2);
        Delete();
    }

    public static int[] read(String file) throws IOException {
        BufferedReader csvReader = new BufferedReader(new FileReader(file));
        String row;
        String[] data;
        int i = 0;
        String var;
        int[] job_nums1 = new int[200];
        while ((row = csvReader.readLine()) != null) {
            data = row.split(",");
            var = data[0];
            Pattern p = Pattern.compile("\"([^\"]*)\"");
            Matcher m = p.matcher(var);
            while (m.find()) {
                var = m.group(1);
            }
            if (i > 0) {
                job_nums1[i] = Integer.parseInt(var);
            }
            i += 1;
        }
        csvReader.close();
        return job_nums1;
    }

    public static void save(HashMap<Integer, Integer> hash) throws IOException {
        Map<Integer, Integer> ldapContent = hash;
        Properties properties = new Properties();

        for (Map.Entry<Integer, Integer> entry : ldapContent.entrySet()) {
            properties.put(Integer.toString(entry.getKey()), Integer.toString(entry.getValue()));
        }

        properties.store(new FileOutputStream("data.properties"), null);

    }

    public static HashMap<Integer, Integer> load() throws IOException {
        Map<Integer, Integer> ldapContent = new HashMap<Integer, Integer>();
        Properties properties = new Properties();
        properties.load(new FileInputStream("data.properties"));

        for (String key : properties.stringPropertyNames()) {
            ldapContent.put(Integer.parseInt(key), Integer.parseInt(properties.get(key).toString()));
        }
        return (HashMap<Integer, Integer>) ldapContent;
    }

    public static void Delete() {
        try {
            File f = new File("C:\\Users\\Oueslati.A\\Downloads\\Jobs.csv"); // file to be delete
            f.delete();
        } catch (Exception e) {
        }  
    }

}
