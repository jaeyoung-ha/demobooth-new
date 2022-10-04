package com.aws.demo.utils;

import org.modelmapper.ModelMapper;

public class CustomModelMapper extends ModelMapper {

    @Override
    public <D> D map(Object source, Class<D> destinationType) {
        Object tmpSource = source;
        if(source == null){
            tmpSource = new Object();
        }

        return super.map(tmpSource, destinationType);
    }
}
