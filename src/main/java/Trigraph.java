import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Trigraph {

    public static void main(String[] args) {
        readFile("text.txt");
        //System.out.println(getTrigraphs("One fish, two fish, red fish, blue fish"));
    }

    private static void readFile(String fileName) {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(fileName), StandardCharsets.UTF_8)) {
            String line;
            while ((line = reader.readLine()) != null) {
                writeMap(getTrigraphs(line));
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    protected static Map<String, Long> getTrigraphs(String text) {
        Matcher m = Pattern.compile("\\w{3}").matcher(text);
        return IntStream
                .iterate(0, m::find, i -> m.start() + 1).mapToObj(i -> m.group())
                .map(String::toUpperCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    private static void writeMap(Map<String, Long> hm) {
        hm.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> {
                    try (FileWriter writer = new FileWriter("trigraphs.txt", true)) {
                        writer.write(entry.getKey() + "\t" + entry.getValue() + "\n");
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                });
    }
}
