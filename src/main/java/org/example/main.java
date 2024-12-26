package org.example;

import org.example.controller.WiseSayingController;
import org.example.service.WiseSayingService;

import java.io.IOException;

public class main {


    public static void main(String[] args) throws IOException {
        WiseSayingService service=new WiseSayingService();
        WiseSayingController controller=new WiseSayingController(service);
        App app=new App(controller,service);
        app.run();
    }

}
