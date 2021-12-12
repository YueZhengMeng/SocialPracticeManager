package com.shou.socialpracticemanager.controller;

import com.shou.socialpracticemanager.po.Practice;
import com.shou.socialpracticemanager.service.PracticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/practice")
public class PracticeController {

    @Autowired
    private PracticeService practiceService;

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    int createPractice(@RequestParam String practiceName)
    {
        return practiceService.creatPractice(practiceName);
    }

    @GetMapping("/my")
    @ResponseStatus(HttpStatus.OK)
    List<Practice> getMyPractice()
    {
        return practiceService.getMyPractice();
    }

    @PostMapping("/join")
    @ResponseStatus(HttpStatus.CREATED)
    int joinPractice(@RequestParam int practiceID,@RequestParam String groupName)
    {
        return practiceService.joinPractice(practiceID, groupName);
    }

}
