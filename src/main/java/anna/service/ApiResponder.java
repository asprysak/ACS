package anna.service;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ApiResponder {

    private Environment environment;
    private String key;

    @Autowired
    public ApiResponder(Environment environment) {
        this.environment = environment;
        this.key = environment.getProperty("census.key");
    }

    public String[][] findAll() throws IOException {

        String url = "https://api.census.gov/data/2014/pep/natstprc?get=STNAME,POP&DATE=7&for=state:%20*&key=";
        return getResponse(url + key);
    }

    public List<Integer> getHistData(String year, String state) throws IOException {

        List<Integer> population = new ArrayList<>();
        Map<String, String> codes = cityCodes();
        for (int i = 3; i <= 25; i++ ) {

            String no = String.format("%02d", i);
            String url = "https://api.census.gov/data/" + year + "/acs/acs1?get=NAME,B01001_0" + no + "E&for=state:"
                    + codes.get(state) + "&key=" + key;

            String[][] response = getResponse(url);
            population.add(Integer.parseInt(response[1][1]));
        }
        return population;
    }

    public Map<String, String> cityCodes() throws IOException {

        String[][] codes = getResponse("https://api.census.gov/data/2016/cbp?get=GEO_TTL&for=state");
        return Arrays.stream(codes).skip(1).collect(Collectors.toMap(x -> x[0], x -> x[1]));
    }

    private String[][] getResponse(String urlString) throws IOException {

        URL url = new URL(urlString);
        InputStreamReader reader = new InputStreamReader(url.openStream());
        return new Gson().fromJson(reader, String[][].class);
    }

}
