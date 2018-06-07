package anna.service;

import java.io.IOException;
import java.util.Map;

public interface PopulationService {

    String[][] findAll() throws IOException;
    Map<String, Integer> makeHist(String year, String state) throws IOException;
}
