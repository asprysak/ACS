package anna.controller;

import anna.service.PopulationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
public class PopulationRestController {

    private PopulationServiceImpl service;

    @Autowired
    public PopulationRestController(PopulationServiceImpl service) {
        this.service = service;
    }

    @RequestMapping("/makeHist")
    public Map<String, Integer> makeHist(String year, String state) throws IOException {

        return service.makeHist(year, state);

    }

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
}
