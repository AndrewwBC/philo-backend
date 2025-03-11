package com.andrew.filosofia.post.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record PostDTO (
        String content,
        LocalDateTime creationDate,
        UUID userId

)
{}
