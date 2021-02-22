package hu.mi.agnos.jwttest.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {

    @RequestMapping("/hellouser")
    public String getUser(HttpServletRequest request, HttpServletResponse response) {
        String newToken = (String) request.getAttribute("newToken");
        response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + newToken);
        return "Hello User";
    }

    @RequestMapping("/helloadmin")
    public String getAdmin(HttpServletRequest request, HttpServletResponse response)  {
        String newToken = (String) request.getAttribute("newToken");
        response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + newToken);
        return "Hello Admin";

    }

}
