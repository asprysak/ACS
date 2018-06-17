package anna.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class PopulationServiceImpl implements PopulationService {

    private ApiResponder responder;

    @Autowired
    public PopulationServiceImpl(ApiResponder responder) {
        this.responder = responder;
    }

    @Override
    public String[][] findAll() throws IOException {

        return responder.findAll();
    }

    @Override
    public Map<String, Integer> makeHist(String year, String state) throws IOException {

        List<Integer> population = responder.getHistData(year, state);
        Map<String, Integer> result = new LinkedHashMap<>();

        int x = 0;
        int y = 4;
        for (int i = 0; i < population.size(); i ++) {
            if (i == 4) {
                int num = result.get("15-19");
                result.put("15-19", num+population.get(i));
            } else if (i == 6 || i == 7) {
                int num = result.get("20-24");
                result.put("20-24", num+population.get(i));
            } else if (i == 16) {
                int num = result.get("60-64");
                result.put("60-64", num+population.get(i));
            } else if (i == 18) {
                int num = result.get("65-69");
                result.put("65-69", num+population.get(i));
            } else {
                String key = x + "-" + y;
                if (x == 85) {
                    key = "higher than 85";
                }
                result.put(key, population.get(i));
                x += 5;
                y += 5;
            }
        }
        return result;
    }
}
