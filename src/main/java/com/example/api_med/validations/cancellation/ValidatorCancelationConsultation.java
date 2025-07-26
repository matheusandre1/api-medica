package com.example.api_med.validations.cancellation;

import com.example.api_med.dto.CancellationDateDto;

public interface ValidatorCancelationConsultation {

    void validate(CancellationDateDto cancellationDateDto);
}
