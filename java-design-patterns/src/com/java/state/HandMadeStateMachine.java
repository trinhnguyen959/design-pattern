package com.java.state;

import org.javatuples.Pair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HandMadeStateMachine {
	private static Map<HandState, List<Pair<Trigger, HandState>>> rules = new HashMap<>();

	static {
		rules.put(HandState.OFF_HOOK, List.of(
				new Pair<>(Trigger.CALL_DIALED, HandState.CONNECTING),
				new Pair<>(Trigger.STOP_USING_PHONE, HandState.ON_HOOK)
		));
		rules.put(HandState.CONNECTING, List.of(
				new Pair<>(Trigger.HUNG_UP, HandState.OFF_HOOK),
				new Pair<>(Trigger.CALL_CONNECTED, HandState.CONNECTED)
		));
		rules.put(HandState.CONNECTED, List.of(
				new Pair<>(Trigger.LEFT_MESSAGE, HandState.OFF_HOOK),
				new Pair<>(Trigger.HUNG_UP, HandState.OFF_HOOK),
				new Pair<>(Trigger.PLACED_ON_HOLD, HandState.ON_HOLD)
		));
		rules.put(HandState.ON_HOLD, List.of(
				new Pair<>(Trigger.TAKEN_OFF_HOLD, HandState.CONNECTED),
				new Pair<>(Trigger.HUNG_UP, HandState.OFF_HOOK)
		));
	}

	private static HandState currentState = HandState.OFF_HOOK;
	private static final HandState exitState = HandState.ON_HOOK;

	public static void main(String[] args) {
		BufferedReader console = new BufferedReader(
				new InputStreamReader(System.in)
		);

		do {
			System.out.println("The phone is currently " + currentState);
			System.out.println("Select a trigger: ");

			for (int i = 0; i < rules.get(currentState).size(); ++i) {
				Trigger trigger = rules.get(currentState).get(i).getValue0();
				System.out.println("" + i + ". " + trigger);
			}

			boolean parseOK = true;
			int choice = 0;

			do {
				try {
					System.out.println("Please enter your choice: ");
					choice = Integer.parseInt(console.readLine());
					parseOK = choice >= 0 && choice < rules.get(currentState).size();
				} catch (Exception e) {
					parseOK = false;
				}
			} while (!parseOK);
			currentState = rules.get(currentState).get(choice).getValue1();
		} while (currentState != exitState);
		System.out.println("And we are done!");
	}
}

enum HandState {
	OFF_HOOK, // starting
	ON_HOOK, // terminal
	CONNECTING,
	CONNECTED,
	ON_HOLD
}

enum Trigger {
	CALL_DIALED,
	HUNG_UP,
	CALL_CONNECTED,
	PLACED_ON_HOLD,
	TAKEN_OFF_HOLD,
	LEFT_MESSAGE,
	STOP_USING_PHONE
}