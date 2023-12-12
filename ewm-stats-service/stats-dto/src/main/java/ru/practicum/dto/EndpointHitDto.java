package ru.practicum.dto;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class EndpointHitDto {
    private String app;
    private String uri;
    private String ip;
    private String timestamp;
}
