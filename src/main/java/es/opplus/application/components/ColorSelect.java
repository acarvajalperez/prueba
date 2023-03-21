package es.opplus.application.components;

import com.flowingcode.vaadin.addons.fontawesome.FontAwesome;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.data.renderer.ComponentRenderer;

public class ColorSelect extends Select<String> {

    private FontAwesome.Solid iconClass;

    public ColorSelect(FontAwesome.Solid iconClass) {
        this.iconClass = iconClass;
        setLabel("Color");
        setMaxWidth("4.5em");
        setItems("gray","black","red","maroon","yellow","olive","lime","green","aqua","teal","blue","navy","fuchsia","purple");
        setValue("red");
        setRenderer(new ComponentRenderer<Component, String>(status -> {
            HorizontalLayout userStateLayout = new HorizontalLayout();
            Icon icon = iconClass.create();
            icon.setSize("1.3em");
            icon.setColor(status);
            userStateLayout.add(icon);
            return userStateLayout;
        }));
    }

    public void setIcon(FontAwesome.Solid icon) {
        this.iconClass = icon;
    }
}
