package edu.cibertec.patitas.controller;

import edu.cibertec.patitas.dto.request.LoginRequestDTO;
import edu.cibertec.patitas.dto.response.LoginResponseDTO;
import edu.cibertec.patitas.viewmodel.LoginModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/login")
@Controller
public class LoginController {

    @Autowired
    private RestTemplate restTemplate;
    private String URL = "http://localhost:8080/auth";

    private LoginModel loginModel = null;

    @GetMapping("")
    public ModelAndView login (){
        loginModel = new LoginModel("01", "", "");
        return new ModelAndView("login", "loginModel", loginModel);
    }

    @PostMapping("/authenticar")
    public ModelAndView authenticar (
            @RequestParam("tipoDocumento") String tipoDocumento,
            @RequestParam("numeroDocumento") String numeroDocumento,
            @RequestParam("password") String password) {

        if(numeroDocumento.trim().isBlank() || password.strip().isBlank() ){
            loginModel = new LoginModel("00", "Error: Contraseña o usuario incorrecto", "");
            return new ModelAndView("login", "loginModel", loginModel);
        }

        LoginRequestDTO requestDTO = new LoginRequestDTO(tipoDocumento, numeroDocumento, password);
        LoginResponseDTO responseDTO = restTemplate.postForObject(URL, requestDTO, LoginResponseDTO.class);

        if(responseDTO.codigo().equals("00")){
            loginModel = new LoginModel("00", responseDTO.menssage(), responseDTO.usuario());
            return new ModelAndView("principal", "usuario", loginModel);
        }else{
            loginModel = new LoginModel("01", "Error: Usuario no existe", "");
            return new ModelAndView("login", "loginModel", loginModel);
        }
    }
}
