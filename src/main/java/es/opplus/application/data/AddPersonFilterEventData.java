package es.opplus.application.data;

import com.flowingcode.vaadin.addons.fontawesome.FontAwesome;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddPersonFilterEventData {
    private String color;
    private String name;
    private FontAwesome.Solid iconClass;
    private PersonFilterData filterData;
}
