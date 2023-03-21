package es.opplus.application.components.dialogs.events;

import es.opplus.application.components.dialogs.AbstractDialogForm;
import com.vaadin.flow.component.ComponentEvent;

public class FormDialogOkEvent extends ComponentEvent<AbstractDialogForm> {
    public FormDialogOkEvent(AbstractDialogForm source, boolean fromClient) {
        super(source, fromClient);
    }
}
