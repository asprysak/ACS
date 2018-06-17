package anna.controller;

import anna.domain.HistArgs;
import anna.service.PopulationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class PopulationController {

    private PopulationServiceImpl service;

    @Autowired
    public PopulationController(PopulationServiceImpl service) {
        this.service = service;
    }

    @RequestMapping("/home")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/histForm")
    public String makeHist(Model model) {

        model.addAttribute("args", new HistArgs());
        return "histForm";
    }

    @PostMapping("/histForm")
    public String showFom(Model model, @ModelAttribute @Valid HistArgs args, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "histForm";
        } else {
            model.addAttribute("args", args);
            return "hist";
        }
    }

}
