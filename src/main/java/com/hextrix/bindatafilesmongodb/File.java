package com.hextrix.bindatafilesmongodb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class File {
    private String id;
    private String name;
    private String type;
    private Binary data;
}
