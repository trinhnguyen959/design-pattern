package com.java.state;

public class LightSwitchApplication {
	public static void main(String[] args) {
		LightSwitch lightSwitch = new LightSwitch();
		lightSwitch.on();
		lightSwitch.off();
		lightSwitch.off();
	}
}

class State {
	void on(LightSwitch lightSwitch) {
		System.out.println("Light is already on");
	}

	void off(LightSwitch lightSwitch) {
		System.out.println("Light is already off");
	}
}

class OnState extends State {
	public OnState() {
		System.out.println("Light turned on");
	}

	@Override
	void off(LightSwitch lightSwitch) {
		System.out.println("Switch light off...");
		lightSwitch.setState(new OffState());
	}
}

class OffState extends State {
	public OffState() {
		System.out.println("Light turn off");
	}

	@Override
	void on(LightSwitch lightSwitch) {
		System.out.println("Switch light on...");
		lightSwitch.setState(new OnState());
	}
}

class LightSwitch {
	private State state;

	public LightSwitch() {
		state = new OffState();
	}

	void on() {
		state.on(this);
	}

	void off() {
		state.off(this);
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
}