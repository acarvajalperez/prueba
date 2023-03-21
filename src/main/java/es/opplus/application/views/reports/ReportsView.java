package es.opplus.application.views.reports;

import es.opplus.application.MainLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

@PageTitle("Reports")
@Route(value = "reports", layout = MainLayout.class)
@AnonymousAllowed
public class ReportsView extends VerticalLayout {

    public ReportsView() {

        add(new H2("Informes"));
    }

}
