package es.opplus.application.components.layout.events;

import es.opplus.application.data.AddPersonFilterEventData;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;

public class AddPersonFilterEvent extends ComponentEvent<Component> {

    AddPersonFilterEventData filter;

    public AddPersonFilterEvent(Component source, boolean fromClient, AddPersonFilterEventData filter) {
        super(source, fromClient);
        this.filter = filter;
    }

    public AddPersonFilterEventData getFilter() {
        return filter;
    }
}
