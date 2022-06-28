package com.auk.order.service.domain.dto.track;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Builder
@AllArgsConstructor
@Getter
public class TrackOrderQuery {
    @NotNull
    private final UUID orderTrackingId;
}
