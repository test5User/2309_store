package by.itclass.model.entities;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
public class OrderItem {
    private String orderId;
    @NonNull private int itemType;
    @NonNull private int itemId;
    @NonNull private String itemVendor;
    @NonNull private String itemModel;
    @NonNull private double itemPrice;
    @Setter
    @NonNull private int quantity;
}
