package anna.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface StatesService {

    String[][] findAll() throws IOException;
    Map<String, Integer> makeHist(String year, String state) throws IOException;
}
