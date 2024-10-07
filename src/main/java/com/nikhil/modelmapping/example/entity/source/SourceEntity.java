package com.nikhil.modelmapping.example.entity.source;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SourceEntity {
    private int id;
    private String name;
    private String email;
    private SourceAddress address;
}

