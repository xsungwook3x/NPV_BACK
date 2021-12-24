package NPV.demo.controller;

import NPV.demo.service.SMSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping("/check")
public class SMSController {

    private final SMSService smsService;

    @Autowired
    public SMSController(SMSService smsService){
        this.smsService=smsService;
    }

    public @ResponseBody String sendSMS(@RequestParam String phoneNumber){

        Random rand = new Random();
        String numStr="";
        for(int index=0;index<4;index++){
            String randNum = Integer.toString(rand.nextInt(10));
            numStr+=randNum;
        }

        System.out.println("수신자 번호 :"+phoneNumber);
        System.out.println(("인증번호 : "+ numStr));
        smsService.certifiedPhoneNumber(phoneNumber,numStr);

        return numStr;
    }
}
