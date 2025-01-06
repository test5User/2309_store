package by.itclass.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Order {
    private String id;
    private String date;
    private String address;
}
