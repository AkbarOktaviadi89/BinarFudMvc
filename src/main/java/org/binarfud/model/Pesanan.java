package org.binarfud.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pesanan {
    private String nameProduct;
    private Integer qty;
    private Integer totalHarga;
}

