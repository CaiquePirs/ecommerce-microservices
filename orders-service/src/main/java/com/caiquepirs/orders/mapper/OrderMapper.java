package com.caiquepirs.orders.mapper;

import com.caiquepirs.orders.controller.dto.OrderRequestDTO;
import com.caiquepirs.orders.model.Order;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toEntity(OrderRequestDTO dto);

}
