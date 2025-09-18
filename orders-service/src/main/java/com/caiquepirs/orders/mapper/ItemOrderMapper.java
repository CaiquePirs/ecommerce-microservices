package com.caiquepirs.orders.mapper;

import com.caiquepirs.orders.controller.dto.ItemOrderRequestDTO;
import com.caiquepirs.orders.model.OrderItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemOrderMapper {

    OrderItem toEntity(ItemOrderRequestDTO dto);
}
