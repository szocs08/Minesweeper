package minesweeper.model;

public class Field {
	private int value;
	private boolean mine=false;
	private boolean flaged=false;
	private boolean visible=false;
	
	public Field() {
		super();
	}
	

	public Field(int value, boolean mine) {
		super();
		this.value = value;
		this.mine = mine;
	}

	public int getValue() {
		return value;
	}

	public boolean isFlaged() {
		return flaged;
	}

	public boolean isMine() {
		return mine;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setFlaged(boolean flaged) {
		this.flaged = flaged;
	}

	public void setMine(boolean mine) {
		this.mine = mine;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	
}
