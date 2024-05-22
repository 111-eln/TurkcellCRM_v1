package com.turkcell.TurkcellCRM.productService.core.crossCuttingConcerns.mapping;

import org.modelmapper.ModelMapper;

public interface ModelMapperService {

    ModelMapper forResponse();

    ModelMapper forRequest();
}
