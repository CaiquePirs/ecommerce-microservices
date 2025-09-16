package com.caiquepirs.orders.mapper;

import com.caiquepirs.orders.controller.dto.OrderItemRequestDTO;
import com.caiquepirs.orders.model.OrderItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    OrderItem toEntity(OrderItemRequestDTO dto);
}
