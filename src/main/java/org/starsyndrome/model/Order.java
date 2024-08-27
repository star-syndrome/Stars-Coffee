package org.starsyndrome.model;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Product product;
    private Integer qty;
    private Integer totalPrice;
}