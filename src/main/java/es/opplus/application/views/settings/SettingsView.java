package es.opplus.application.views.settings;

import com.flowingcode.vaadin.addons.fontawesome.FontAwesome;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.dom.ThemeList;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.Lumo;
import es.opplus.application.components.ColorSelect;
import es.opplus.application.components.layout.events.AddFilterEvent;
import es.opplus.application.components.layout.events.ChangeTitleColorEvent;
import es.opplus.application.components.views.AbstractSettingsView;
import es.opplus.application.MainLayout;
import es.opplus.application.data.ChangeTitleColorData;

import javax.annotation.security.RolesAllowed;
import java.text.MessageFormat;

@PageTitle("Settings")
@Route(value = "settings", layout = MainLayout.class)
@RolesAllowed("USER")
//@AnonymousAllowed
public class SettingsView extends AbstractSettingsView {

    private Select<String> themeSelect;

    public SettingsView() {
        super();

        Checkbox darkTheme = new Checkbox();
        darkTheme.setValue(true);

        darkTheme.addValueChangeListener(e -> {
            setTheme(e.getValue());
        });

        ThemeList themes = UI.getCurrent().getElement().getThemeList();
        themeSelect = new Select();
        //themeSelect.setItems(getTranslation("dark"), getTranslation("light"));
        themeSelect.setItems(themes);

        if (UI.getCurrent().getElement().getThemeList().contains(Lumo.DARK))
            themeSelect.setValue(getTranslation("dark"));
        else
            themeSelect.setValue(getTranslation("light"));

        themeSelect.addValueChangeListener(listener -> {
            ThemeList themeList = UI.getCurrent().getElement().getThemeList();

            if (listener.getValue().contains("dark"))
                themeList.add(Lumo.DARK);
            else
                themeList.remove(Lumo.DARK);
            System.out.println(themeList);

        });

        ColorSelect colorTitle = new ColorSelect(FontAwesome.Solid.PALETTE);
        colorTitle.addValueChangeListener(listener -> {
            ComponentUtil.fireEvent(
                    UI.getCurrent(),
                    new ChangeTitleColorEvent(
                            this,
                            false,
                            new ChangeTitleColorData(colorTitle.getValue())
                    )
            );
        });

        FormLayout formLayout = new FormLayout();
        formLayout.setSizeFull();
        // Use addFormItem instead of add, to wrap fields into form items,
        // which displays labels on the side by default
        formLayout.addFormItem(darkTheme, "Tema oscuro");
        formLayout.addFormItem(themeSelect, "Tema");
        formLayout.addFormItem(colorTitle, "Color del titulo");
        add(formLayout);
    }

    private void setTheme(boolean dark) {
        var js = MessageFormat.format(
                "document.documentElement.setAttribute(\"theme\", \"{0}\")",
                dark ? Lumo.DARK : Lumo.LIGHT
        );
        getElement().executeJs(js);
    }

}
