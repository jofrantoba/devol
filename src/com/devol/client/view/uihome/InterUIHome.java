package com.devol.client.view.uihome;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

public interface InterUIHome extends IsWidget {
	void setUIToken(String token);
	void setPresenter(Presenter presenter);

    public interface Presenter {
        void goTo(Place place);
    }
}
