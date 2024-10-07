package com.nikhil.modelmapping.example.entity.target;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TargetEntity {
    private int id;
    private String name;
    private String email;
    private TargetAddress address;

}

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
class TargetAddress{
    private String address;
}