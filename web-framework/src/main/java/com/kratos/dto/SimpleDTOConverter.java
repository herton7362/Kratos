package com.kratos.dto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.ParameterizedType;

public class SimpleDTOConverter<A, B> extends DTOConverter<A, B> {
    private final Logger LOG = LoggerFactory.getLogger(SimpleDTOConverter.class);
    private Class<B> clazzB;
    private Class<A> clazzA;

    @SuppressWarnings("unchecked")
    public SimpleDTOConverter() {
        clazzB = (Class<B>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        clazzA = (Class<A>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    protected B doForward(A a) {
        try {
            B b = clazzB.newInstance();
            BeanUtils.copyProperties(a, b);
            return b;
        } catch (InstantiationException | IllegalAccessException e) {

            LOG.error("DTO转换出现错误", e);
        }
        return null;
    }

    @Override
    protected A doBackward(B b) {

        try {
            A a = clazzA.newInstance();
            BeanUtils.copyProperties(b, a);
            return a;
        } catch (InstantiationException | IllegalAccessException e) {
            LOG.error("DTO转换出现错误", e);
        }
        return null;
    }
}
