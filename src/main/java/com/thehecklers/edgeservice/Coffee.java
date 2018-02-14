package com.thehecklers.edgeservice;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Coffee {
    private Long id;
    @NonNull
    private String name, description;
}