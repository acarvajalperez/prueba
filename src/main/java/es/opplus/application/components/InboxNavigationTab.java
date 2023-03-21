package es.opplus.application.components;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.shared.Registration;
import es.opplus.application.components.layout.drawer.NavigationTab;
import es.opplus.application.components.layout.events.AddFilterEvent;
import es.opplus.application.views.tasks.TasksView;

public class InboxNavigationTab extends NavigationTab {

    private Registration registration;

    public InboxNavigationTab() {

    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        // Register to events from the event bus
        registration =
                ComponentUtil.addListener(
                        attachEvent.getUI(),
                        AddFilterEvent.class,
                        event -> {
                            if (event.getSource() != this) {
                                Icon icon = event.getFilter().getIconClass().create();
                                icon.setColor(event.getFilter().getColor());
                                this.subTabs(
                                        new NavigationTab()
                                                .label(event.getFilter().getName())
                                                .icon(icon)
                                                .trash()
                                                .counter("123")
                                                .path(TasksView.class).build()
                                );
                            }
                        }
                );

    }

    @Override
    protected void onDetach(DetachEvent detachEvent) {
        super.onDetach(detachEvent);
        // Unregister from the event bus
        if (registration != null)
            registration.remove();
    }
}
