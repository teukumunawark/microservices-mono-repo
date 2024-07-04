package com.ecommer.customer.error.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CostumerNotFoundException extends RuntimeException {
    private final String message;
}
