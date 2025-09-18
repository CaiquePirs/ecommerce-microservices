package com.caiquepirs.orders.publisher.representation;

import com.caiquepirs.orders.model.enums.StatusOrder;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record OrderRepresentation(Long id,
                                  Long customerId,
                                  String name,
                                  String cpf,
                                  String email,
                                  String phone,
                                  String neighborhood,
                                  String number,
                                  String street,
                                  LocalDateTime orderDate,
                                  BigDecimal total,
                                  StatusOrder status,
                                  List<ItemOrderRepresentation> items) {
}
