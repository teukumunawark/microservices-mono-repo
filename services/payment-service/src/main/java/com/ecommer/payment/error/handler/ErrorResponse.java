package com.ecommer.payment.error.handler;

import java.util.Map;

public record ErrorResponse(
    Map<String, String> errors
) {

}
