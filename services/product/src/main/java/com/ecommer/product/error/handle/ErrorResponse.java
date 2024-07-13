package com.ecommer.product.error.handle;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {

}
