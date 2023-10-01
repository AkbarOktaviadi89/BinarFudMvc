package org.binarfud.view;

import javafx.scene.control.Separator;
import org.binarfud.model.Invoice;
import org.binarfud.model.Product;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuView {

    public static final String SEPARATOR = "=============================";
    public static List<Product> product = new ArrayList<>(Arrays.asList(
            Product.builder().nameProduct("Nasi Goreng").price(12000).build(),
            Product.builder().nameProduct("Mie Goreng ").price(10000).build(),
            Product.builder().nameProduct("Es Teh   ").price(5000).build()
    ));

    public static void showMenu(){
        System.out.println(SEPARATOR + "\n" + "Selamat datang di BinarFud" + "\n" + SEPARATOR +
                "\n\nSilahkan Pilih Menu : ");
        product.forEach(val -> {
            System.out.println((product.indexOf(val) + 1) + ". " + val.getNameProduct() + " \t|\t Rp. " + val.getPrice());
        });
        System.out.println("99. Pesan dan Bayar\n0. Keluar");
        System.out.print("=> ");
    }

    public static void showMenuDetail(Product product){
        System.out.println(SEPARATOR + "\n" + "Detail Pesanan Anda" + "\n" + SEPARATOR +
                "\n" + product.getNameProduct() + "\t|\t Rp. " + product.getPrice() + "\n");
    }

    public static void showPembayaran(Invoice invoice){
        System.out.println(SEPARATOR + "\n" + "Konfirmasi dan Pembayaran" + "\n" + SEPARATOR + "\n");
        invoice.getPesananList().forEach(val ->{
            System.out.println(val.getNameProduct() + " \t "+ val.getQty() +" \t Rp. "+ val.getTotalHarga());
        });
        System.out.println("----------------------------------+" +
                "\nTotal  \t\t" + invoice.getTotalQty() + " \t Rp. " + invoice.getTotalPembayaran());
        System.out.println("\n1. Konfirmasi dan Bayar\n2. Kembali ke Menu Utama\n0. Keluar Aplikasi\n");
        System.out.print("=> ");
    }

    public static void showError(){
        System.out.println(SEPARATOR + "\n" + "Mohon Masukkan \nInput Yang Benar" + "\n" + SEPARATOR +
                "\n(Y) untuk lanjut \n(N) untuk keluar");
        System.out.print("=> ");
    }

}
