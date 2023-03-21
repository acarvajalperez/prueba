package es.opplus.application.components.dialogs;

import es.opplus.application.components.dialogs.events.FormDialogOkEvent;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.shared.Registration;

public class FormDialog extends ConfirmDialog {

    private AbstractDialogForm form;
    private Registration registration;


    public FormDialog(AbstractDialogForm form) {

        this.form = form;

        setHeader("Nuevo filtro");

        setCancelText("Cancelar");
        setCancelable(true);

        setConfirmText("Aceptar");
        addConfirmListener(event -> {
            form.ok();
        });

        add(form);
    }

    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        // Register to events from the event bus
        registration =
                ComponentUtil.addListener(
                        attachEvent.getUI(),
                        FormDialogOkEvent.class,
                        event -> {
                            form.ok();
                            close();
                        }
                );
    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        super.onDetach(detachEvent);
        // Unregister from the event bus
        registration.remove();
    }



}