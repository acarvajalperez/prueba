package es.opplus.application.views.about;

import es.opplus.application.MainLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;

@PageTitle("About")
@Route(value = "", layout = MainLayout.class)
//@RouteAlias(value = "", layout = MainLayout.class)
//@AnonymousAllowed
@RolesAllowed("USER")

public class AboutView extends VerticalLayout {

    public AboutView() {

        Image img = new Image("images/picasso.png", "Picasso");
        img.setWidth("200px");
        add(img);

        add(new H2("This place intentionally left empty"));
        add(new Paragraph("It’s a place where you can grow your own UI 🤗"));

        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

}
