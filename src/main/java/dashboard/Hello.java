package dashboard;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import java.util.Map;
import java.util.HashMap;

@RestController
public class Hello {

    @RequestMapping("/home")
    public ModelAndView index() {
        Map<String, String> tmp = new HashMap<>();
		    return new ModelAndView("allEmployees.html");
    }

}
