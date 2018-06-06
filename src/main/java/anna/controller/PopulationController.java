package anna.controller;

import anna.service.StatesServiceImpl;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class PopulationController {

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

    @RequestMapping("/hist")
    public String makeHist(Model model) {

        model.addAttribute("population", new LinkedHashMap<>());
        return "hist";

    }

}
