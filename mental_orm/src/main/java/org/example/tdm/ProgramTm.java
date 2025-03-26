package org.example.tdm;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProgramTm {
    private String code;
    private String name;
    private double price;
    private int duration;
}
