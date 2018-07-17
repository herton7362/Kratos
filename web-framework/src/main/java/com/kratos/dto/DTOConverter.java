package com.kratos.dto;

import com.google.common.base.Function;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * DTO转换基类，可以将 A 转换为 B 同时又可以通过调用reverse()来将 B 转换为 A
 * @author He
 */
public abstract class DTOConverter<A, B> implements Function<A, B> {
    private transient DTOConverter<B, A> reverse;
    protected abstract B doForward(A a);
    protected abstract A doBackward(B b);

    public final B convert(A a) {
        return correctedDoForward(a);
    }

    public final Iterable<B> convert(Iterable<A> aList) {
        List<B> bList = new ArrayList<>();
        for (A a : aList) {
            bList.add(correctedDoForward(a));
        }
        return bList;
    }


    public DTOConverter<B, A> reverse() {
        DTOConverter<B, A> result = reverse;
        return (result == null) ? reverse = new DTOConverter.ReverseConverter<>(this) : result;
    }

    B correctedDoForward(A a) {
        return doForward(a);
    }

    A correctedDoBackward(B b) {
        return doBackward(b);
    }

    public final B apply(A a) {
        return convert(a);
    }

    private static final class ReverseConverter<A, B>
            extends DTOConverter<B, A> implements Serializable {
        final DTOConverter<A, B> original;

        ReverseConverter(DTOConverter<A, B> original) {
            this.original = original;
        }

        @Override
        protected A doForward(B b) {
            throw new AssertionError();
        }

        @Override
        protected B doBackward(A a) {
            throw new AssertionError();
        }

        @Override
        A correctedDoForward(B b) {
            return original.correctedDoBackward(b);
        }

        @Override
        B correctedDoBackward(A a) {
            return original.correctedDoForward(a);
        }

        @Override
        public DTOConverter<A, B> reverse() {
            return original;
        }

        @Override
        public boolean equals(Object object) {
            if (object instanceof DTOConverter.ReverseConverter) {
                DTOConverter.ReverseConverter<?, ?> that = (DTOConverter.ReverseConverter<?, ?>) object;
                return this.original.equals(that.original);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return ~original.hashCode();
        }

        @Override
        public String toString() {
            return original + ".reverse()";
        }

        private static final long serialVersionUID = 0L;
    }
}
