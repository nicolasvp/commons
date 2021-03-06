package com.microservices.commons.models.services;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UtilServiceImpl implements IUtilService {

    @Override
    public List<String> listErrors(BindingResult result) {
        return result.getFieldErrors()
                    .stream()
                    .map(err -> err.getDefaultMessage())
                    .collect(Collectors.toList());
    }
}
