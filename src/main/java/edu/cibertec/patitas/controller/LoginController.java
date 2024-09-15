package edu.cibertec.patitas.controller;

import edu.cibertec.patitas.viewmodel.LoginModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/login")
@Controller
public class LoginController {
    LoginModel loginModel;

    @GetMapping("/")
    public ModelAndView login (){

        loginModel = new LoginModel("00", "", "");

        return new ModelAndView("login", "tipo", loginModel);
    }

    @PostMapping("/authenticar")
    public ModelAndView authenticar (@RequestParam("numeroDocumento") String tipoDocumento) {
        if(tipoDocumento.trim().isBlank()){
            System.out.println("TIPO VAIO");
        }

        System.out.println("TIPO DOUMENTO ES" + tipoDocumento);

        return new ModelAndView();
    }
}
