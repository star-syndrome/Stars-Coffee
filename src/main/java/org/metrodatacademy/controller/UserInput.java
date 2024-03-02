package org.metrodatacademy.controller;

import org.metrodatacademy.model.Order;
import org.metrodatacademy.model.Product;
import org.metrodatacademy.repository.ProductRepository;
import org.metrodatacademy.service.OrderService;
import org.metrodatacademy.service.OrderServiceImplement;
import org.metrodatacademy.view.MenuView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserInput {

    Scanner MBA = new Scanner(System.in);
    List<Order> orderList = new ArrayList<>();
    OrderService orderService = new OrderServiceImplement();

    public UserInput() throws IOException {
        try {
            this.mainInput();
        } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            System.out.println("Error: Masukkan angka yang tersedia!");
        } catch (InputMismatchException inputMismatchException) {
            System.out.println("Error: Silakan mengulangi program, terima kasih.");
        }
    }

    public void mainInput() throws InputMismatchException, ArrayIndexOutOfBoundsException, IOException {
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
    }

    public void orderDetailsInput(int input) throws IOException, InputMismatchException, ArrayIndexOutOfBoundsException {
        Product product = ProductRepository.PRODUCTS.get(input - 1);
        MenuView.orderDetailsView(product);
        int userInput = MBA.nextInt();
        if (userInput > 0){
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
    }

    public void confirmationOrdersInput() throws InputMismatchException, ArrayIndexOutOfBoundsException, IOException {
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
    }
}