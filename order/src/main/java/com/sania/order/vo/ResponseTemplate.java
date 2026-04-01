package com.sania.order.vo;

import com.sania.order.model.Order;

import lombok.Data;


@Data
public class ResponseTemplate {
    Order order;
    Produk produk;
}
