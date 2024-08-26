package net.weg.topcare.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ProductFilter {
    List<String> categories = new ArrayList<>();


}
