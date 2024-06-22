package com.joaobarbosa.msavaliadorCredito.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class ReturnCustomerReviewDTO implements Serializable {

    private List<ApprovedCard> approvedCards;

    public ReturnCustomerReviewDTO() {}


}
