package anna.domain;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ApiResponder {

    private  static final String key = ""; //Your key

    public String[][] findAll() throws IOException {

        String url = "https://api.census.gov/data/2014/pep/natstprc?get=STNAME,POP&DATE=7&for=state:%20*&key=";
        URL ad = new URL(url + key);
        InputStreamReader reader = new InputStreamReader(ad.openStream());
        String[][] response =  new Gson().fromJson(reader, String[][].class);
        return response;
    }

    public List<Integer> getHistData(String year, String state) throws IOException {

        List<Integer> population = new ArrayList<>();
        Map<String, String> codes = cityCodes();
        for (int i = 3; i <= 25; i++ ) {

            String no = String.format("%02d", i);
            String u = "https://api.census.gov/data/" + year + "/acs/acs1?get=NAME,B01001_0" + no + "E&for=state:"
                    + codes.get(state) + "&key=" + key;

            URL ad = new URL(u);
            InputStreamReader reader = new InputStreamReader(ad.openStream());
            String[][] response = new Gson().fromJson(reader, String[][].class);
            population.add(Integer.parseInt(response[1][1]));
        }
        return population;
    }

    public Map<String, String> cityCodes() throws IOException {

        URL ad = new URL("https://api.census.gov/data/2016/cbp?get=GEO_TTL&for=state");
        InputStreamReader reader = new InputStreamReader(ad.openStream());
        String[][] codes =  new Gson().fromJson(reader, String[][].class);
        return Arrays.stream(codes).skip(1).collect(Collectors.toMap(x -> x[0], x -> x[1]));
    }
}
