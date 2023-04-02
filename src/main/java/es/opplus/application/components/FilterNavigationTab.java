package es.opplus.application.components;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import es.opplus.application.components.layout.drawer.NavigationTab;
import es.opplus.application.components.views.FilterView;

import java.util.logging.Logger;

public class FilterNavigationTab<T extends Filter> extends NavigationTab {
    private final static Logger LOG = Logger.getLogger(FilterNavigationTab.class.getName());

    private Filter filter;

    public FilterNavigationTab() {
    }

    public FilterNavigationTab filter(T filter) {
        LOG.info("filter: " + filter);
        this.filter = filter.clone();
        return this;
    }


    @Override
    public void onClick() {
        LOG.info("onClick");
        setSelected(!selected);
        if (path != null) {
            Component component = UI.getCurrent().navigate(path).get();
            if (component instanceof FilterView<?> filterView) {
                LOG.info(component.toString());
                ((FilterView<Filter>) filterView).setFilter(filter);
            }
        }
    }
}
