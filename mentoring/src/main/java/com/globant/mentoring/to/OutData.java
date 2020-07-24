package com.globant.mentoring.to;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class OutData<T> {

    private int status;
    private T info;
}