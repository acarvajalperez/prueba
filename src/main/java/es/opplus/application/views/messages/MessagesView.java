package es.opplus.application.views.messages;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import es.opplus.application.MainLayout;
import es.opplus.application.components.views.AbstractMessagesView;
import es.opplus.application.data.entity.SamplePerson;

import javax.annotation.security.RolesAllowed;

@PageTitle("messages")
@Route(value = "messages", layout = MainLayout.class)
@RolesAllowed("USER")
public class MessagesView extends AbstractMessagesView {

    private Grid<SamplePerson> grid;

    public MessagesView() {
        createGrid();
        add(grid);
    }

    private Component createGrid() {
        grid = new Grid<>(SamplePerson.class, false);
        /*
        grid.addColumn("Leido").setAutoWidth(true);
        grid.addColumn("Aplicación").setAutoWidth(true);
        grid.addColumn("Asunto").setAutoWidth(true);
        grid.addColumn("Fecha").setAutoWidth(true);
         */
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.addClassNames(LumoUtility.Border.TOP, LumoUtility.BorderColor.CONTRAST_10);

        /*
        grid.setItems(query -> samplePersonService.list(
                PageRequest.of(
                        query.getPage(),
                        query.getPageSize(),
                        VaadinSpringDataHelpers.toSpringDataSort(query)
                ),
                filters
        ).stream());
         */

        return grid;
    }

    private void refreshGrid() {
        grid.getDataProvider().refreshAll();
    }

}
