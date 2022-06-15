package hu.wurfel.refference.school.controllers;

import hu.wurfel.refference.school.model.AddRequest;
import hu.wurfel.refference.school.model.SearchRequest;
import hu.wurfel.refference.school.services.requestServices.AddRequestService;
import hu.wurfel.refference.school.services.requestServices.SearchResponseCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class PageController {

    @Autowired
    SearchResponseCreator searchResponseCreator;
    @Autowired
    AddRequestService addRequestService;

    @RequestMapping("/")
    public String index() {
        return "home";
    }

    @RequestMapping("/tli")
    public String TLI() {
        return "TLI";
    }

    @RequestMapping("/sli")
    public String SLI() {
        return "SLI";
    }

    @PostMapping("/tli/search")
    public ResponseEntity<ArrayList> searchForStudent(@RequestBody SearchRequest request) {
        ArrayList answer = new ArrayList();
        answer = searchResponseCreator.create(request);
        System.out.println("the response:" + answer);
        return ResponseEntity.ok(answer);
    }

    @PostMapping("/tli/adding")
    public ResponseEntity<ArrayList> adding(@RequestBody AddRequest addRequest) {
        AddRequest input = addRequest;
        System.out.println(input);
        ArrayList answer = new ArrayList<>();
        answer = addRequestService.create(addRequest);
        System.out.println("The " + addRequest.getType() + "added!");
        System.out.println(answer);
        return ResponseEntity.ok(answer);
    }


}
