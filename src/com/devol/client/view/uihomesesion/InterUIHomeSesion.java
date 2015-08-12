package com.devol.client.view.uihomesesion;

import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.IsWidget;

public interface InterUIHomeSesion extends IsWidget {
	void setUIToken(String token);
	void setPresenter(Presenter presenter);

    public interface Presenter {
        void goTo(Place place);
    }
}
