package es.opplus.application.views.components;

import es.opplus.application.components.dialogs.AddFilterDialogForm;
import es.opplus.application.components.dialogs.FormDialog;
import es.opplus.application.data.DebugData;
import es.opplus.application.data.PersonFilterData;
import es.opplus.application.services.counter.RandomCounterService;
import es.opplus.application.MainLayout;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.security.RolesAllowed;
import java.time.LocalDate;

@PageTitle("Componentes")
@Route(value = "components", layout = MainLayout.class)
@RolesAllowed("USER")
public class ComponentsView extends VerticalLayout {

    @Autowired
    RandomCounterService randomCounterService;

    @Data
    public static class Person {
        private String name;
        private LocalDate dateOfBirth;
        private double sleep;
        private double memory;
    }

    static private Button dialogButton;
    private Button startCounterButton;
    private Button stopCounterButton;
    private NumberField sleepField;
    private TextField textField;
    private NumberField memoryLabel;

    public ComponentsView() throws ValidationException {

        DebugData debugData = new DebugData();

        String userId = System.identityHashCode(UI.getCurrent()) + "";

        createDialogButton();

        sleepField = new NumberField();
        sleepField.setLabel("Tiempo de espera");
        sleepField.setValue(500D);
        sleepField.setMin(200);

        textField = new TextField();
        textField.setLabel("Campo de prueba");
        memoryLabel = new NumberField();
        //sleepField.setValue(VaadinInitListener.);

        startCounterButton = new Button("Start");

        startCounterButton.addClickListener(e -> {
            if (!randomCounterService.isAlive())
                randomCounterService.start();

            if (sleepField.getValue() != null) {
                randomCounterService.setDelay(sleepField.getValue().intValue());
            }
            startCounterButton.setEnabled(false);
            stopCounterButton.setEnabled(true);
        });
        startCounterButton.addClickShortcut(Key.ENTER);

        stopCounterButton = new Button("Stop");
        stopCounterButton.addClickListener(e -> {
            randomCounterService.para();
            stopCounterButton.setEnabled(false);
            startCounterButton.setEnabled(true);
        });
        stopCounterButton.addClickShortcut(Key.ENTER);


        add(dialogButton, sleepField, startCounterButton, stopCounterButton, textField, memoryLabel);

        // addChart();
    }

    private static void createDialogButton() {
        dialogButton = new Button();
        dialogButton.setText("Cuadro dialogo");
        dialogButton.addClickListener(e -> {
            new FormDialog(new AddFilterDialogForm(new PersonFilterData())).open();
        });
        dialogButton.addClickShortcut(Key.ENTER);
    }
}
