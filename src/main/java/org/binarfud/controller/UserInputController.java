package org.binarfud.controller;

import org.binarfud.model.Invoice;
import org.binarfud.model.Pesanan;
import org.binarfud.model.Product;
import org.binarfud.service.PesananService;
import org.binarfud.view.MenuView;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class UserInputController {
    Scanner scanner = new Scanner(System.in);
    List<Pesanan> pesananList = new ArrayList<>();
    PesananService pesananService = new PesananService();


    public Invoice inputMainMenu(){
        Invoice invoice = new Invoice(pesananList,pesananService.totalQty(pesananList) ,pesananService.totalPembayaran(pesananList));
        MenuView.showMenu();
        try {
        int userInput = scanner.nextInt();
        scanner.nextLine();
        switch (userInput){
            case 99:
                MenuView.showPembayaran(invoice);
                int inputPembayaran = scanner.nextInt();
                if (inputPembayaran == 1){
                    this.konfirmasiDanBayar();
                }else if (inputPembayaran == 2){
                    this.inputMainMenu();
                }
                break;
            case 0:
                System.exit(0);
                break;
            default:
                this.inputMenuDetail(userInput);
                break;
        }
        }catch (Exception e){
            MenuView.showError();
            scanner.nextLine();
            String inputError = Optional.ofNullable(scanner.nextLine()).orElse("N");
            Optional.of(inputError)
                    .map(String::toUpperCase)
                    .filter(val -> val.equals("Y"))
                    .map(val -> {
                        return this.inputMainMenu();
                    });
        }
        return invoice;
    }

    public void inputMenuDetail(int input) {
        try {
            Product product = MenuView.product.get(input - 1);
            MenuView.showMenuDetail(product);
            System.out.print("qty => ");
            int userInput = scanner.nextInt();
            scanner.nextLine();

            if (userInput > 0) {
                boolean isProductExist = false;
                for (Pesanan pesanan : pesananList) {
                    if (pesanan.getNameProduct().equals(product.getNameProduct())) {
                        pesanan.setQty(userInput);
                        pesanan.setTotalHarga((product.getPrice() * userInput));
                        isProductExist = true;
                        break;
                    }
                }
                if (!isProductExist) {
                    pesananList.add(Pesanan.builder()
                            .nameProduct(product.getNameProduct())
                            .qty(userInput)
                            .totalHarga(product.getPrice() * userInput)
                            .build());
                }
                System.out.println("Pesanan berhasil ditambahkan/diubah!");
            } else {
                System.out.println("Jumlah pesanan harus lebih dari 0.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Input tidak valid. Masukkan angka yang benar.");
        }
        this.inputMainMenu();
    }

    public void konfirmasiDanBayar() {
        int counter = 1;
        String newFileName = "Struct.txt";

        while (new File(newFileName).exists()) {
            newFileName = "Struct_" + counter + ".txt";
            counter++;
        }
        File file = new File(newFileName);

        try(FileWriter fw = new FileWriter(file);
            BufferedWriter StructWr = new BufferedWriter(fw);
        )
        {
            StructWr.write("=============================\n");
            StructWr.write("Binar Food\n");
            StructWr.write("=============================\n\n");
            StructWr.write("Terima kasih sudah memesan \ndi Binar Food\n\n");
            StructWr.write("Dibawah ini adalah pesanan anda\n\n");

            pesananList.forEach(val -> {
                try {
                    StructWr.write(val.getNameProduct() + " \t "+ val.getQty() +" \t Rp. "+ val.getTotalHarga() + "\n");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

            Invoice invoice = new Invoice(pesananList,pesananService.totalQty(pesananList) ,pesananService.totalPembayaran(pesananList));
            StructWr.write("-------------------------------+\n");
            StructWr.write("Total \t\t\t" +  invoice.getTotalQty() + "\t" + "Rp. " + invoice.getTotalPembayaran());
            StructWr.newLine();
            StructWr.write("\n\nPembayaran : BinarCash\n\n");

            StructWr.write("=============================\n");
            StructWr.write("Simpan Struk ini sebagai \nbukti pembayaran\n");
            StructWr.write("=============================");

            StructWr.flush();

            System.out.println("Struk berhasil disimpan di " + newFileName);
        } catch (IOException e) {
            System.out.print("Terjadi kesalahan saat menyimpan struk : ");
            e.getCause();
        }
    }
}
