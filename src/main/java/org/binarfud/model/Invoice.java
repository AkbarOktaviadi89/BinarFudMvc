package org.binarfud.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Invoice {
    private List<Pesanan> pesananList;
    private Integer totalQty;
    private Integer totalPembayaran;
}
