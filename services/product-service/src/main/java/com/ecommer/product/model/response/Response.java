package com.ecommer.product.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public interface Response extends Serializable {
}
