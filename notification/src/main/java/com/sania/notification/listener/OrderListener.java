package com.sania.notification.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sania.notification.model.Order;
import com.sania.notification.service.NotificationService;



@Component
public class OrderListener {
    @Autowired
    private NotificationService notificationService;

    @RabbitListener(queues = "order.notification.queue")
    public void receiveOrder(Order order){
        System.out.println("Horasss.... Order baru diterima");
        System.out.println("ID: " + order.getId());
        System.out.println("Email: " + order.getEmail());
        notificationService.sendEmail(order);

    }

}
