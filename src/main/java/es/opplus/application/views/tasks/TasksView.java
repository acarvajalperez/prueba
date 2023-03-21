package es.opplus.application.views.tasks;

import es.opplus.application.components.filters.FolderFilterForm;
import es.opplus.application.data.entity.SamplePerson;
import es.opplus.application.data.service.SamplePersonService;
import es.opplus.application.MainLayout;
import com.flowingcode.vaadin.addons.fontawesome.FontAwesome;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.springframework.data.domain.PageRequest;

import javax.annotation.security.RolesAllowed;

@PageTitle("Tareas")
@Route(value = "tasks", layout = MainLayout.class)
@RolesAllowed("USER")

@Uses(Icon.class)
public class TasksView extends Div {

    private Grid<SamplePerson> grid;

    private FolderFilterForm taskFilterForm;
    private final SamplePersonService samplePersonService;

    public TasksView(SamplePersonService SamplePersonService) {
        this.samplePersonService = SamplePersonService;
        setSizeFull();
        addClassNames("gridwith-taskFilterForm-view");

        taskFilterForm = new FolderFilterForm(() -> refreshGrid());

        VerticalLayout layout = new VerticalLayout(createMobileFilters(), taskFilterForm, createGrid());
        layout.setSizeFull();
        layout.setPadding(false);
        layout.setSpacing(false);
        add(layout);
    }

    private HorizontalLayout createMobileFilters() {
        // Mobile version
        HorizontalLayout mobileFilters = new HorizontalLayout();
        mobileFilters.setWidthFull();
        mobileFilters.addClassNames(LumoUtility.Padding.MEDIUM, LumoUtility.BoxSizing.BORDER,
                LumoUtility.AlignItems.CENTER);
        mobileFilters.addClassName("mobile-taskFilterForm");

        Icon mobileIcon = FontAwesome.Solid.PLUS.create();
        Span filtersHeading = new Span(getTranslation("filters"));

        Icon columsIcon = FontAwesome.Solid.PLUS.create();
        Span columnsHeading = new Span(getTranslation("columns"));

        mobileFilters.add(mobileIcon, filtersHeading, columsIcon, columnsHeading);

        mobileFilters.setFlexGrow(1, filtersHeading);
        mobileFilters.addClickListener(e -> {
            if (taskFilterForm.getClassNames().contains("visible")) {
                taskFilterForm.removeClassName("visible");
                mobileIcon.getElement().setAttribute("icon", "lumo:plus");
            } else {
                taskFilterForm.addClassName("visible");
                mobileIcon.getElement().setAttribute("icon", "lumo:minus");
            }
        });
        return mobileFilters;
    }



    private Component createGrid() {
        grid = new Grid<>(SamplePerson.class, false);
        grid.addColumn("firstName").setAutoWidth(true);
        grid.addColumn("lastName").setAutoWidth(true);
        grid.addColumn("email").setAutoWidth(true);
        grid.addColumn("phone").setAutoWidth(true);
        grid.addColumn("dateOfBirth").setAutoWidth(true);
        grid.addColumn("occupation").setAutoWidth(true);
        grid.addColumn("role").setAutoWidth(true);

        grid.setItems(query -> samplePersonService.list(
                PageRequest.of(query.getPage(), query.getPageSize(), VaadinSpringDataHelpers.toSpringDataSort(query)),
                taskFilterForm).stream());
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER);
        grid.addClassNames(LumoUtility.Border.TOP, LumoUtility.BorderColor.CONTRAST_10);

        return grid;
    }

    private void refreshGrid() {
        grid.getDataProvider().refreshAll();
    }

}
