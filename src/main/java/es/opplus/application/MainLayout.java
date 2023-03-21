package es.opplus.application;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import es.opplus.application.components.InboxNavigationTab;
import es.opplus.application.components.layout.OppAppLayout;
import es.opplus.application.components.layout.drawer.NavigationTab;
import es.opplus.application.components.layout.drawer.NavigationTabs;
import es.opplus.application.components.settings.SettingsComponent;
import com.flowingcode.vaadin.addons.fontawesome.FontAwesome;
import com.vaadin.flow.component.*;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.server.auth.AccessAnnotationChecker;
import com.vaadin.flow.shared.Registration;
import es.opplus.application.views.DebugView;
import es.opplus.application.views.components.ComponentsView;
import es.opplus.application.views.folders.FoldersView;
import es.opplus.application.views.settings.SettingsView;
import es.opplus.application.views.tasks.TasksView;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends OppAppLayout {

    public MainLayout(AccessAnnotationChecker accessChecker) {
        super(accessChecker);
        //this.authenticatedUser = authenticatedUser;

        this.accessChecker = accessChecker;

        //setPrimarySection(Section.DRAWER);
        addDrawerContent();
        setSettingsView(new SettingsView());
    }


    private void addDrawerContent() {

        drawer.add(getNewOpperationButton());

        drawer.add(
                new InboxNavigationTab()
                        .icon(FontAwesome.Solid.INBOX.create())
                        .label("Bandeja Tareas")
                        .counter("124.564")
                        .subTabs(
                                new NavigationTab()
                                        .icon(FontAwesome.Solid.INBOX.create())
                                        .label("Pendientes")
                                        .counter("124.564")
                                        .trash()
                                        .path(TasksView.class)
                                        .build(),
                                new NavigationTab()
                                        .icon(FontAwesome.Solid.INBOX.create())
                                        .label("Personal")
                                        .counter("124.564")
                                        .path(DebugView.class)
                                        .build()
                        )
                        .build(),
                new NavigationTab()
                        .icon(FontAwesome.Solid.FOLDER.create())
                        .label("Operaciones")
                        .counter("124.564")
                        .subTabs(
                                new NavigationTab()
                                        .icon(FontAwesome.Solid.FOLDER.create())
                                        .label("Filtro 1")
                                        .counter("12.564")
                                        .trash()
                                        .path(FoldersView.class)
                                        .build(),
                                new NavigationTab()
                                        .icon(FontAwesome.Solid.FOLDER.create())
                                        .label("Filtro 2")
                                        .counter("4.564")
                                        .trash()
                                        .path(FoldersView.class)
                                        .build()
                        )
                        .build(),
                new NavigationTab()
                        .icon(FontAwesome.Solid.FOLDER.create())
                        .label("Expedientes")
                        .counter("124.564")
                        .subTabs(
                                new NavigationTab()
                                        .icon(FontAwesome.Solid.FOLDER.create())
                                        .label("Filtro 1")
                                        .counter("12.564")
                                        .trash()
                                        .path(FoldersView.class)
                                        .build(),
                                new NavigationTab()
                                        .icon(FontAwesome.Solid.FOLDER.create())
                                        .label("Filtro 2")
                                        .counter("4.564")
                                        .trash()
                                        .path(FoldersView.class)
                                        .build()
                        )
                        .build(),
                new NavigationTab()
                        .icon(FontAwesome.Solid.BUILDING.create())
                        .label("Componentes")
                        .path(ComponentsView.class)
                        .build()

        );

        if (accessChecker.hasAccess(DebugView.class)) {
            drawer.add(
                    new NavigationTab()
                            .icon(FontAwesome.Solid.BUG.create())
                            .label("Debug")
                            .path(DebugView.class)
                            .build()
            );
        }

    }

    private Component getNewOpperationButton() {

        Button button = new Button("Nueva operación");
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setWidthFull();
        horizontalLayout.setJustifyContentMode(FlexComponent.JustifyContentMode.CENTER);
        horizontalLayout.setSpacing(true);
        horizontalLayout.setPadding(true);
        horizontalLayout.add(button);

        return horizontalLayout;
    }

    private Component createNavigation() {

        NavigationTabs tabs = new NavigationTabs();
        /*
        //tabs.setOrientation(Tabs.Orientation.VERTICAL);

        if (accessChecker.hasAccess(TasksView.class)) {

            Button settingButton = new Button(FontAwesome.Solid.GEAR.create());
            settingButton.addThemeVariants(ButtonVariant.LUMO_TERTIARY, ButtonVariant.LUMO_SMALL);

            settingButton.addClickListener(listener -> {

                ConfirmDialog dialog = new ConfirmDialog();
                dialog.setHeader("Unsaved changes");
                dialog.setText(
                        "There are unsaved changes. Do you want to discard or save them?");

                dialog.setCancelable(true);
                dialog.addCancelListener(event -> setStatus("Canceled"));

                dialog.setRejectable(true);
                dialog.setRejectText("Discard");
                dialog.addRejectListener(event -> setStatus("Discarded"));

                dialog.setConfirmText("Save");
                dialog.addConfirmListener(event -> setStatus("Saved"));

                dialog.open();
            });

            filtersTabs = new NavigationTabs(
                    new com.acp.application.components.layout.drawer.NavigationTab()
                            .label("Pendientes")
                            .icon(FontAwesome.Solid.FILTER.create())
                            .counter("12.455")
                            .indent(1)
                            .path(TasksView.class).build(),
                    new com.acp.application.components.layout.drawer.NavigationTab()
                            .label("Personal")
                            .icon(FontAwesome.Solid.FILTER.create())
                            .indent(1)
                            .path(HelloWorldView.class).build()
            );


            tabs.add(new com.acp.application.components.layout.drawer.NavigationTab()
                    .icon(FontAwesome.Solid.INBOX.create())
                    .label("Bandeja Tareas")
                    .counter("124.564")
                    .subTab(filtersTabs)
                    .build()
            );

            com.acp.application.components.layout.drawer.NavigationTab folder = new com.acp.application.components.layout.drawer.NavigationTab()
                    .icon(FontAwesome.Solid.FOLDER.create())
                    .label("Expedientes")
                    .counter("357")
                    //.subTab(filtersTabs)
                    .path(FoldersView.class)
                    .build();

            // tasks.addClassName("new-app-layout-drawer-item");
            tabs.add(folder);
        }

        if (accessChecker.hasAccess(DebugComponent.class)) {
            tabs.add(
                    new com.acp.application.components.layout.drawer.NavigationTab()
                            .icon(FontAwesome.Solid.BUG.create())
                            .label("Debug")
                            .path(DebugComponent.class)
                            .build()
            );
        }

        tabs.add(new com.acp.application.components.layout.drawer.NavigationTab()
                .icon(FontAwesome.Solid.LAYER_GROUP.create())
                .label("Componentes")
                .path(ComponentsView.class)
                .build()
        );

        if (accessChecker.hasAccess(AboutView.class)) {
            tabs.add(new com.acp.application.components.layout.drawer.NavigationTab()
                    .icon(FontAwesome.Solid.INFO_CIRCLE.create())
                    .label("About")
                    .path(AboutView.class)
                    .build()
            );
        }
         */
        return tabs;
    }

    /*
    private AppNav createNavigation() {
        // AppNav is not yet an official component.
        // For documentation, visit https://github.com/vaadin/vcf-nav#readme
        AppNav nav = new AppNav();

        if (accessChecker.hasAccess(TasksView.class)) {
            AppNavItem gridItem = new AppNavItem("Tareas");
            gridItem.setIconClass("la la-filter");
            AppNavItem child1 = new AppNavItem("Pendientes", TasksView.class, "la la-filter");
            AppNavItem child2 = new AppNavItem("Asignadas", "http://localhost:8081/hello", "la la-filter");
            gridItem.addItem(child1, child2);
            nav.addItem(gridItem);
        }

        if (accessChecker.hasAccess(HelloWorldView.class)) {
            nav.addItem(new AppNavItem("Hello World", HelloWorldView.class, "lab la-google"));

        }
        if (accessChecker.hasAccess(AboutView.class)) {
            nav.addItem(new AppNavItem("About", AboutView.class, "la la-file"));

        }

        if (accessChecker.hasAccess(DebugComponent.class)) {
            nav.addItem(new AppNavItem("Debug", DebugComponent.class, "la la-bug"));

        }

        if (accessChecker.hasAccess(ChatView.class)) {
            nav.addItem(new AppNavItem("Chat", ChatView.class, "la la-comments"));

        }

        return nav;
    }
     */

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        //viewTitle.setText(getCurrentPageTitle().toUpperCase(Locale.ROOT));
    }
}
