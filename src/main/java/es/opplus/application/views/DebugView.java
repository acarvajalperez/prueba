package es.opplus.application.views;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import es.opplus.application.MainLayout;
import es.opplus.application.components.debug.DebugComponent;

import javax.annotation.security.RolesAllowed;

@PageTitle("Debug")
@Route(value = "debug", layout = MainLayout.class)
//@RouteAlias(value = "", layout = MainLayout.class)
//@AnonymousAllowed
@RolesAllowed("DEBUG")

public class DebugView extends VerticalLayout {

    public DebugView() {
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        add(new DebugComponent());
    }
}
