package es.opplus.application.components;

import es.opplus.application.data.PersonFilterData;

public abstract class Filter implements Cloneable {
    public Filter clone() {
        Filter cloned;
        try {
            cloned = (Filter) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
        return cloned;
    }
}
