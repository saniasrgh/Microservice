package com.sania.order.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sania.order.model.Order;
import com.sania.order.repository.OrderRepository;
import com.sania.order.vo.Produk;
import com.sania.order.vo.ResponseTemplate;

import jakarta.transaction.Transactional;
@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }
    
    public Order creatOrder(Order order){
        return orderRepository.save(order);
    }

    @Transactional
    public void update(Long orderId, Integer jumlah, String tanggal, String status) {
        // Auto generated method s
        Order order = orderRepository.findById(orderId).orElseThrow(()
                -> new IllegalStateException("Order tidak ada"));
        if (jumlah != null) {
            order.setJumlah(jumlah);
        } if (tanggal !=null && tanggal.length() > 0 && !Objects.equals(order.getTanggal(),tanggal)){
            order.setTanggal(tanggal);
        }
    }
    

    public Order getOrderById(Long id){
        return orderRepository.findById(id).orElse(null);
    }

    public List<ResponseTemplate> getOrderWithProdukById(Long id){
    List<ResponseTemplate> responseList = new ArrayList<>();

    Order order = getOrderById(id);
    if (order == null) {
        throw new RuntimeException("Order tidak ditemukan");
    }

    ServiceInstance serviceInstance = discoveryClient.getInstances("PRODUK").get(0);

    Produk produk = restTemplate.getForObject(
        serviceInstance.getUri() + "/api/produk/" + order.getProdukId(),
        Produk.class
    );

    ResponseTemplate vo = new ResponseTemplate();
    vo.setOrder(order);
    vo.setProduk(produk);

    responseList.add(vo);

    return responseList;
 }

    public void deleteOrder (Long id){
        orderRepository.deleteById(id);
    }

}
