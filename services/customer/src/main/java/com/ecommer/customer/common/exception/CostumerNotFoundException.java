package com.ecommer.customer.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CostumerNotFoundException extends RuntimeException {
    private final String message;
}
