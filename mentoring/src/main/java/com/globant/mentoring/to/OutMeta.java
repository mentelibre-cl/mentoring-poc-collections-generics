package com.globant.mentoring.to;

import lombok.*;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class OutMeta {
    private String name;
    private Date date;
}
