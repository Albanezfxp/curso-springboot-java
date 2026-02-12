package curso_spring.controllers;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/math")
public class MathController {

    @RequestMapping("/{number1}/{number2}")
    public double sum(
            @PathVariable String number1,
            @PathVariable String number2
            ) {
        return 1D;
    }
}
