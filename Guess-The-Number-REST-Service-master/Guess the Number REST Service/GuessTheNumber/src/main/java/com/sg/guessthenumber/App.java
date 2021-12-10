/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.guessthenumber;

import java.util.Collections;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author ivaylomaslev
 */
@SpringBootApplication
public class App {
    public static void main(String[] args) {
        //SpringApplication.run(App.class, args);
        SpringApplication app = new SpringApplication(com.sg.guessthenumber.App.class);
        app.setDefaultProperties(Collections
          .singletonMap("server.port", "8083"));
        app.run(args);
    }
}
