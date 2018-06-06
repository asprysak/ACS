package anna.controller;

import anna.service.StatesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
public class PopulationRestController {

    @Autowired
    private StatesServiceImpl service;

    @RequestMapping("/makeHist")
    public Map<String, Integer> makeHist() throws IOException {

        return service.makeHist("2016", "Nevada");

    }
}
