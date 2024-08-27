package org.starsyndrome.view;

import org.starsyndrome.model.Order;
import org.starsyndrome.model.Product;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.starsyndrome.repository.ProductRepository.PRODUCTS;

public class MenuView {
    public static void mainView() {
        System.out.println("-----------------------------------------------");
        System.out.println("\t\tSelamat datang di Stars Coffee!");
        System.out.println("-----------------------------------------------");
        System.out.println("Silakan pilih menu yang ingin dipesan:");
        PRODUCTS.forEach(product -> System.out.println(product.getId() + ". " +
                product.getName() + "\t | \t" + product.getPrice()));
        System.out.println("99. Konfirmasi Pesanan");

        System.out.println("0. Keluar Aplikasi");
        System.out.println();
        System.out.print("Inputkan nomor yang tersedia pada menu: ");
    }

    public static void orderDetailsView(Product product) {
        System.out.println("---------------------------------------");
        System.out.println("\t\t\tPesanan Anda");
        System.out.println("---------------------------------------");
        System.out.println("Pesanan: " + product.getName());
        System.out.println("Harga: Rp." + product.getPrice());
        System.out.print("Jumlah (Pilih 0 jika ingin kembali): ");
    }

    public static void confirmationOrdersView(List<Order> orders, Integer totalQty, Integer totalPembayaran) {
        System.out.println("================================");
        System.out.println("\t  Konfirmasi Pesanan\t\t");
        System.out.println("================================");
        System.out.println("ID" + "\t  Pesanan" + "\t  Qty" + "  Harga");
        orders.forEach(order -> System.out.println(order.getProduct().getId() + "  | " +
                order.getProduct().getName() + "\t" + " | " + order.getQty() + " | " + order.getTotalPrice()));
        System.out.println("------------------------------+");
        System.out.println("Total\t\t" + "\t   " + totalQty + "   " + totalPembayaran);
        System.out.println();
        System.out.println("1. Pembayaran");
        System.out.println("2. Kembali Ke Menu Utama");
        System.out.println("0. Keluar Aplikasi");
        System.out.print("=> ");
    }

    public static void paymentView(List<Order> orders, Integer totalQty, Integer totalPembayaran) {
        System.out.println("===============================");
        System.out.println("\t\tStars Coffee");
        System.out.println("===============================");
        System.out.println("Di bawah ini adalah pesanan Anda:");
        System.out.println();
        System.out.println("ID" + "\t  Pesanan" + "\t  Qty" + "  Harga");
        orders.forEach(order -> System.out.println(order.getProduct().getId() + "  | " +
                order.getProduct().getName() + "\t" + " | " + order.getQty() + " | " + order.getTotalPrice()));
        System.out.println("------------------------------+");
        System.out.println("Total\t\t" + "\t   " + totalQty + "   " + totalPembayaran);
        System.out.println();
        System.out.println("Pembayaran: Stars Coffee Card");
        System.out.println();
        System.out.println("Thank you for coming!");
    }

    public static void receipt(List<Order> orders, Integer totalQty, Integer totalPembayaran) throws IOException {
        FileWriter fw = new FileWriter("C:/Stars-Coffee/src/main/resources/struk.txt");
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("===========================================\n");
        bw.write("\t\t\t   Stars Coffee\n");
        bw.write("===========================================\n");
        bw.write("Terima kasih sudah memesan di Stars Coffee!\n");
        bw.write("Di bawah ini adalah pesanan Anda:\n");
        bw.newLine();
        bw.write("No" + "\t  Pesanan" + "\t  Qty" + "  Harga\n");
        orders.forEach(order -> {
            try {
                bw.write(order.getProduct().getId() + "  | " +
                        order.getProduct().getName() + "\t" + " | " + order.getQty() + " | " + order.getTotalPrice() + "\n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        bw.write("------------------------------+\n");
        bw.write("Total\t\t" + "\t   " + totalQty + "   " + totalPembayaran + "\n");
        bw.newLine();
        bw.write("Pembayaran: Stars Coffee Card\n");
        bw.newLine();
        bw.write("===========================================\n");
        bw.write("Simpan struk ini sebagai bukti pembayaran\n");
        bw.write("===========================================\n");
        bw.flush();
        bw.close();
    }
}