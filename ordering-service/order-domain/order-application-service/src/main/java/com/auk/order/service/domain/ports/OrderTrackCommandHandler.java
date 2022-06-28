package com.auk.order.service.domain.ports;

import com.auk.order.service.domain.dto.track.TrackOrderQuery;
import com.auk.order.service.domain.dto.track.TrackOrderResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OrderTrackCommandHandler {
    public TrackOrderResponse trackOrder(TrackOrderQuery trackOrderQuery){}
}
