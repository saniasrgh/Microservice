package com.sania.notification.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.sania.notification.model.Order;


@Service
public class NotificationService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(Order order){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(order.getEmail());
        message.setSubject("Order telah sukses");
        String isiEmail = "Halo " + order.getUsername() + ",\n\n"
        + "Order kamu berhasil dibuat!\n\n"
        + "ID Order: " + order.getId() + "\n"
        + "ID Produk: " + order.getProdukId() + "\n"
        + "ID Pelanggan: " + order.getPelangganId() + "\n"
        + "Harga: " + order.getHarga() + "\n"
        + "Jumlah: " + order.getJumlah() + "\n"
        + "Total: " + order.getTotal() + "\n"
        + "Tanggal: " + order.getTanggal();
        message.setText(isiEmail);
        mailSender.send(message);

        System.out.println("Email berhasil dikirim ke : " + order.getEmail());
        
    }
}
