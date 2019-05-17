package com.tci.util;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JSplitPane;

public class JVerticalSplitPane extends JSplitPane implements ComponentListener {
	protected Dimension bottomSize = new Dimension(0, 0);

	public JVerticalSplitPane() {
		super(JSplitPane.VERTICAL_SPLIT);
		this.addComponentListener(this);
	}

	/*
	 * protected JVerticalSplitPane() { }
	 */
	public void restoreDivider() {
		if (bottomSize == null)
			return;
		if (bottomSize.height > 0) {
			int newPos = this.getSize().height - bottomSize.height - this.getDividerSize() - 2;
			if (newPos <= 0)
				newPos = this.getSize().height / 2;
			if (newPos >= this.getSize().height)
				newPos = this.getSize().height - 50;
			this.setDividerLocation(newPos);
		}
	}

	public void componentResized(ComponentEvent e) {
		if (e.getSource() == this) {
			restoreDivider();
//        System.out.print(newPos);
//      System.out.println(" Main resize "+"Bottom height"+bottomSize.height);
		}
		if (e.getSource() == rightComponent) {
//        System.out.println("Right resize"+bottomSize.height);
			bottomSize = rightComponent.getSize();
		}
	}

	protected void addImpl(Component comp, Object constraints, int index) {
		super.addImpl(comp, constraints, index);
		if (comp == rightComponent) {
			rightComponent.addComponentListener(this);
			bottomSize = rightComponent.getSize();
		}
	}

	public void remove(Component component) {
		if (component == rightComponent)
			rightComponent.removeComponentListener(this);
		super.remove(component);
	}

	/**
	 * Dummy methods from Component Listener
	 */
	public void componentMoved(ComponentEvent e) {
	}

	public void componentShown(ComponentEvent e) {
	}

	public void componentHidden(ComponentEvent e) {
	}
}


