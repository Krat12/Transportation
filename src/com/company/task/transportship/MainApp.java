package com.company.task.transportship;

import com.company.task.transportship.controller.TransportController;
import com.company.task.transportship.controller.TransportHandler;
import com.company.task.transportship.model.Transport;
import com.company.task.transportship.model.Truck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainApp {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        TransportHandler handler = new TransportController();

        String carrying = reader.readLine();
        Transport transport = new Truck();
        transport.setCarrying(Integer.valueOf(carrying));
        String data = reader.readLine();
        System.out.println(handler.getResult(data,transport));
    }

}
