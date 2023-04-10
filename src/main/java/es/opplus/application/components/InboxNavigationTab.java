package es.opplus.application.components;

import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.ComponentUtil;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.shared.Registration;
import es.opplus.application.components.layout.drawer.NavigationTab;
import es.opplus.application.components.layout.events.AddPersonFilterEvent;
import es.opplus.application.components.views.filter.FilterNavigationTab;
import es.opplus.application.components.views.filter.FilterView;
import es.opplus.application.data.PersonFilterData;
import es.opplus.application.views.folders.FoldersView;

public class InboxNavigationTab extends NavigationTab {

    private Registration registration;
    private PersonFilterData personFilterData;

    public InboxNavigationTab() {
    }

    public InboxNavigationTab filter(PersonFilterData personFilterData) {
        this.personFilterData = personFilterData;
        return this;
    }

    @Override
    public void onClick() {
        setSelected(!selected);
        if (path != null) {
            if (UI.getCurrent().navigate(path).get() instanceof FilterView filterView)
                filterView.setFilter(new PersonFilterData());
        }
    }

    @Override
    protected void onAttach(AttachEvent attachEvent) {
        super.onAttach(attachEvent);
        // Register to events from the event bus
        registration =
                ComponentUtil.addListener(
                        attachEvent.getUI(),
                        AddPersonFilterEvent.class,
                        event -> {
                            if (event.getSource() != this) {
                                Icon icon = event.getFilter().getIconClass().create();
                                icon.setColor(event.getFilter().getColor());
                                this.subTabs(
                                        new FilterNavigationTab()
                                                .filter(event.getFilter().getFilterData())
                                                .label(event.getFilter().getName())
                                                .icon(icon)
                                                .trash()
                                                .counter("123")
                                                .path(FoldersView.class).build()
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
