package by.itclass.model.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Receipt {
    private Order order;
    private List<ReceiptItem> receiptItems;
    private double total;
}
