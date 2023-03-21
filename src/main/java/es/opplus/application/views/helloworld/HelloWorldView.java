package es.opplus.application.views.helloworld;

import com.vaadin.flow.router.RouteAlias;
import es.opplus.application.MainLayout;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;

@PageTitle("Hello World")
@Route(value = "hello", layout = MainLayout.class)
//@AnonymousAllowed
@RolesAllowed("USER")

public class HelloWorldView extends VerticalLayout {

    private TextField name;
    private Button sayHello;

    public HelloWorldView() {

        name = new TextField("Your name");
        sayHello = new Button("Say hello");
        sayHello.addClickListener(e -> {
            Notification.show("Hello " + name.getValue());
        });
        sayHello.addClickShortcut(Key.ENTER);

        //setVerticalComponentAlignment(Alignment.END, name, sayHello);

        add(name, sayHello);
    }
}
