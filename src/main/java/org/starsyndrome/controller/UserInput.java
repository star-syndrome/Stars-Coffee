package org.starsyndrome.controller;

import org.starsyndrome.model.Order;
import org.starsyndrome.model.Product;
import org.starsyndrome.repository.ProductRepository;
import org.starsyndrome.service.OrderService;
import org.starsyndrome.service.OrderServiceImplement;
import org.starsyndrome.view.MenuView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInput {
    List<Order> orderList = new ArrayList<>();
    OrderService orderService = new OrderServiceImplement();

    public UserInput() {
        this.mainInput();
    }

    public void mainInput() {
        try {
            Scanner MBA = new Scanner(System.in);

            MenuView.mainView();
            int userInput = MBA.nextInt();
            switch (userInput) {
                case 0:
                    System.exit(0);
                    System.out.println("Terima kasih sudah datang ke Stars Coffee!");
                    break;
                case 99:
                    this.confirmationOrdersInput();
                    break;
                default:
                    if (userInput >= 1 && userInput <= ProductRepository.PRODUCTS.size()) {
                        this.orderDetailsInput(userInput);
                    } else {
                        System.out.println("Error: Masukkan angka yang tersedia!");
                        this.mainInput();
                    }
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            this.mainInput();
        }
    }

    public void orderDetailsInput(int input) {
        try {
            Scanner MBA = new Scanner(System.in);

            Product product = ProductRepository.PRODUCTS.get(input - 1);
            MenuView.orderDetailsView(product);
            int userInput = MBA.nextInt();
            if (userInput > 0) {
                orderList.add(Order.builder()
                        .product(Product.builder()
                                .id(product.getId())
                                .name(product.getName())
                                .build())
                        .qty(userInput)
                        .totalPrice(userInput * product.getPrice())
                        .build()
                );
            }
            this.mainInput();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            this.orderDetailsInput(input);
        }
    }

    public void confirmationOrdersInput() {
        try {
            Scanner MBA = new Scanner(System.in);

            MenuView.confirmationOrdersView(orderList, orderService.totalQuantity(orderList), orderService.totalPayment(orderList));
            int userInput = MBA.nextInt();
            switch (userInput) {
                case 0:
                    System.exit(0);
                    System.out.println("Terima kasih sudah datang ke Stars Coffee!");
                    break;
                case 1:
                    MenuView.paymentView(orderList, orderService.totalQuantity(orderList), orderService.totalPayment(orderList));
                    MenuView.receipt(orderList, orderService.totalQuantity(orderList), orderService.totalPayment(orderList));
                    break;
                case 2:
                    this.mainInput();
                    break;
                default:
                    this.confirmationOrdersInput();
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            this.confirmationOrdersInput();
        }
    }
}