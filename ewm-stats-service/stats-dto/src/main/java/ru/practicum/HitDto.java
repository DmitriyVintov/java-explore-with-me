package ru.practicum;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class HitDto {
    private String app;
    private String uri;
    private String ip;
    private String timestamp;
}
