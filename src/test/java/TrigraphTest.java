import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class TrigraphTest {
    @Test
    public void getTrigraphsTest() {
        Map<String, Long> map = new HashMap<>();
        map.put("BLU", (long) 1);
        map.put("FIS", (long) 4);
        map.put("ISH", (long) 4);
        map.put("LUE", (long) 1);
        map.put("ONE", (long) 1);
        map.put("RED", (long) 1);
        map.put("TWO", (long) 1);
        Map<String, Long> result = map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        Map<String, Long> expected = Trigraph.getTrigraphs("One fish, two fish, red fish, blue fish.")
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        assertThat(result, is(expected));
    }
}