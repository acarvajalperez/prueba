package es.opplus.application.components;

import com.flowingcode.vaadin.addons.fontawesome.FontAwesome;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.data.renderer.ComponentRenderer;

public class IconSelect extends Select<FontAwesome.Solid> {

    String color = "black";

    public IconSelect(String color) {
        this.color = color;
        setLabel("Icono");
        setMaxWidth("4.5em");
        setItems(FontAwesome.Solid.FOLDER, FontAwesome.Solid.TAG, FontAwesome.Solid.FILTER);
        setValue(FontAwesome.Solid.FOLDER);
        setRenderer(new ComponentRenderer<Component, FontAwesome.Solid>(status -> {
            HorizontalLayout userStateLayout = new HorizontalLayout();
            Icon icon = status.create();
            icon.setSize("1.3em");
            icon.setColor(color);
            userStateLayout.add(icon);
            return userStateLayout;
        }));
    }

    public void setColor(String color) {
        this.color = color;
    }
}
