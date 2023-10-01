package org.binarfud.service;

import org.binarfud.model.Pesanan;

import java.util.List;

public class PesananService {

    public Integer totalPembayaran(List<Pesanan> pesananList){
        return pesananList.stream()
                .reduce(0, (total, pesanan) -> total + pesanan.getTotalHarga(),Integer::sum);
    }

    public Integer totalQty(List<Pesanan> pesananList){
        return pesananList.stream()
                .reduce(0, (total, pesanan) -> total + pesanan.getQty(),Integer::sum);
    }
}
