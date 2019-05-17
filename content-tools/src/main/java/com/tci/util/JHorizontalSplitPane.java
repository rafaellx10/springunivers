package com.tci.util;

public class JHorizontalSplitPane extends JVerticalSplitPane {
	protected JHorizontalSplitPane() {
	}

	public JHorizontalSplitPane(boolean constantSize) {
		super();
		this.addComponentListener(this);
		//mConstantSize = constantSize;
	}

	public void restoreDivider() {
		if (bottomSize.width > 0) {
			int newPos = this.getSize().width - bottomSize.width - this.getDividerSize() - 2;
			if (newPos <= 0)
				newPos = this.getSize().width / 2;
			if (newPos >= this.getSize().width)
				newPos = this.getSize().width - 50;
			this.setDividerLocation(newPos);
		}
	}
}
