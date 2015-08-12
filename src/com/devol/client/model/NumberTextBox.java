package com.devol.client.model;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.TextBox;

public class NumberTextBox extends FlowPanel {

	private TextBox textBox;

	public NumberTextBox() {
		init();
		style();
	}

	private void init() {
		textBox = new TextBox();
		textBox.getElement().setAttribute("type", "tel");
		add(textBox);
	}

	private void style() {
		textBox.setWidth("100%");
		textBox.getElement().getStyle().setBorderWidth(0, Unit.PX);
		textBox.getElement().getStyle().setColor("#777");
		textBox.getElement().getStyle()
				.setProperty("font", "normal 17px Helvetica");
	}

	public String getText() {
		return textBox.getText();
	}

	public void setText(String text) {
		textBox.setText(text);
	}

	public void setReadOnly(boolean readOnly) {
		textBox.setReadOnly(readOnly);
	}

	public TextBox getTextBox() {
		return textBox;
	}
	
	
}
