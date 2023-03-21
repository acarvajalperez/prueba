package es.opplus.application.components.dialogs;

import com.flowingcode.vaadin.addons.fontawesome.FontAwesome;
import es.opplus.application.components.ColorSelect;
import es.opplus.application.components.IconSelect;
import es.opplus.application.components.dialogs.events.FormDialogOkEvent;
import es.opplus.application.components.layout.events.AddFilterEvent;
import es.opplus.application.data.AddPersonFilterEventData;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import es.opplus.application.data.PersonFilterData;

public class AddFilterDialogForm extends AbstractDialogForm  {

    Binder<AddPersonFilterEventData> binder;

    public TextField filterName;
    public ColorSelect colorSelect = new ColorSelect(FontAwesome.Solid.PALETTE);
    public IconSelect iconSelect = new IconSelect("black");
    PersonFilterData personFilterData;

    public AddFilterDialogForm(PersonFilterData personFilterData) {

        this.personFilterData = personFilterData;

        filterName = new TextField("Nombre");
        filterName.setPlaceholder("Introduce nombre del filtro");
        filterName.setMaxLength(15);
        filterName.setMinLength(3);
        filterName.setWidthFull();
        filterName.setRequired(true);
        filterName.addKeyPressListener(event -> {
            if (event.getKey().equals(Key.ENTER)) {
                ComponentUtil.fireEvent(
                        UI.getCurrent(),
                        new FormDialogOkEvent(this,false)
                );
            }
        });
        filterName.focus();

        binder = new Binder<>(AddPersonFilterEventData.class);
        binder
                .forField(filterName)
                .withValidator(
                        name -> name.length() >= 3,
                        "Debe contener al menos 3 caracteres"
                )
                .bind(AddPersonFilterEventData::getName, AddPersonFilterEventData::setName)
        ;

        add(iconSelect, colorSelect, filterName);
    }

    public AddPersonFilterEventData getValue() {
        return new AddPersonFilterEventData(
                colorSelect.getValue(),
                filterName.getValue(),
                iconSelect.getValue(),
                personFilterData
        );
    }

    public boolean isValid() {
        return binder.isValid();
    }

    @Override
    public void ok() {
        if (isValid()) {
            ComponentUtil.fireEvent(
                    UI.getCurrent(),
                    new AddFilterEvent(
                            this,
                            false,
                            getValue()
                    )
            );
        }
    }
}
