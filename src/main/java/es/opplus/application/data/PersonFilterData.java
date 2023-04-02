package es.opplus.application.data;

import es.opplus.application.components.Filter;
import lombok.*;

@Data
public class PersonFilterData extends Filter {
    String name;
    String phone;

    public PersonFilterData() {
    }

    public PersonFilterData(PersonFilterData personFilterData) {
        super();
        this.name = personFilterData.name;
        this.phone = personFilterData.phone;
    }
    public PersonFilterData clone() {
        PersonFilterData cloned = new PersonFilterData();
        cloned.name = this.name;
        cloned.phone = this.phone;
        return cloned;
    }
}
