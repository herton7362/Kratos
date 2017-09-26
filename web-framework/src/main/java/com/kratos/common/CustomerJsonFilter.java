package com.kratos.common;

import com.fasterxml.jackson.annotation.JsonFilter;

public class CustomerJsonFilter {
    static final String CUSTOMER_FILTER = "CUSTOMER_FILTER";

    @JsonFilter(CUSTOMER_FILTER)
    public interface CustomerFilter {
    }
}
