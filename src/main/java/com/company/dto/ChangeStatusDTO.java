package com.company.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChangeStatusDTO {
    private Boolean status;
    private String id;
}
