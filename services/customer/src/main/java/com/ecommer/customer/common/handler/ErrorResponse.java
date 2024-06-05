package com.ecommer.customer.common.handler;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}
