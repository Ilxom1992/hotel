package com.example.demo.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    public String message;
    public boolean success;
    public Object object;

    public Result(String message, boolean success) {
        this.message = message;
        this.success = success;
    }
}
