package calorify.calorify.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("calendar")
public class CalendarController {

    @GetMapping("/")
    public String Calendar(Model model){
        String word = "hello";
        model.addAttribute("text", word);
        return "calendar/calendar";
    }

    @GetMapping("/date")
    public String CalendarDate(){
        return "calendar/date";
    }

    @GetMapping("/list")
    public String CalendarList(){
        return "calendar/list";
    }

    @GetMapping("/add")
    public String CalendarAdd(){
        return "calendar/add";
    }

}
