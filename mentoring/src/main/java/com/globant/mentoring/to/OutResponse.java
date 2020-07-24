package com.globant.mentoring.to;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class OutResponse {

    private OutData data;
    private OutMeta meta;
    private OutLink link;
}
