import org.yaml.snakeyaml.Yaml;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;


public class Basic {
    static List<String> s = new ArrayList<String>();
    static Map<List<String>, List<String>> m1 = new HashMap<List<String>, List<String>>();

    public void listAllFiles(String path) {
        System.out.println("In listAllfiles(String path) method");
        try (Stream<Path> paths = Files.walk(Paths.get(path))) {
            paths.forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    try {
                        //readContent(filePath);
                        Path p = filePath;
                        //String s=p.toString();
                        s.add(p.toString());
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(s.size());
    }
    public void display1() {
        System.out.println(m1.size());
    }

    public static void main(String[] args) throws IOException {
        Basic l = new Basic();
        File folder = new File("/home/ravi/Downloads/device-signature");
        l.listAllFiles("/home/ravi/Downloads/device-signature");
        Yaml yaml = new Yaml();
        Map<List<String>, List<String>> m = new HashMap<List<String>, List<String>>();
        for (int i = 0; i < s.size(); i++) {
            String path = s.get(i);
            m = yaml.load(new FileInputStream(new File(path)));
            if(m!=null)
            {
                for (Map.Entry<List<String>,List<String>> entry : m.entrySet()) {
                    m1.put(m.get(entry.getKey()), m.get(entry.getValue()));
                }
            }
        }
        System.out.println(m1.size());
        for (Map.Entry<List<String>,List<String>> entry : m1.entrySet())
            System.out.println(entry.getKey()+" "+entry.getValue());
        System.out.println(m1.size());
    }
}
