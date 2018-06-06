package anna.controller;

import anna.service.StatesServiceImpl;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class SController {

    @Autowired
    private StatesServiceImpl service;

//    @RequestMapping("/map")
//    public String[][] findAll() {
//
//        String[][] result = new String[1][1];
//        try {
//            result =  service.findAll();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }

    @RequestMapping("/histo")
    public String makeHist(Model model, @ModelAttribute @Valid LinkedHashMap<String, Integer> population) {

        //  Map<String, Integer> population = new HashMap<>();
        try {
            population = (LinkedHashMap) service.makeHist("2016", "Alabama");
            //     JSONObject jsonPop = new JSONObject(population);

//            Gson gson = new GsonBuilder().setLenient().create();
//            JsonParser jp = new JsonParser();
//            JsonElement je = jp.parse(population.toString());

            final JsonObject obj = new JsonObject();
            for (Map.Entry<String, Integer> entry : population.entrySet()) {
                obj.addProperty(entry.getKey(), entry.getValue());
            }
            System.out.println(obj.toString());
            Type integerObjectMapType = new TypeToken<Map<String, Integer>>(){}.getType();
            model.addAttribute("population", new Gson().toJson(population,integerObjectMapType));
//            model.addAttribute("population", obj.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "hist";

    }
}
