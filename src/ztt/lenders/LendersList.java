package ztt.lenders;


import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

/**
 * Created by invisible on 12.07.2018.
 */
public class LendersList {

    private int sumOfPool = 0;
    private ArrayList<Lender> lenders = new ArrayList<>();

    public LendersList(String fileName) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(fileName), StandardCharsets.UTF_8)) {
            for (String line : (Iterable<String>) lines::iterator) {
                if (!line.contains("Lender")) {
                    String[] splitArray = line.split(",");
                    lenders.add(new Lender(splitArray[0], Double.parseDouble(splitArray[1]), Integer.parseInt(splitArray[2])));
                    sumOfPool += Integer.parseInt(splitArray[2]);
                }
            }
        }
        Collections.sort(lenders);
    }

    public int getSumOfPool() {
        return sumOfPool;
    }

    public ArrayList<Lender> getLendersList() {
        return lenders;

    }


}
