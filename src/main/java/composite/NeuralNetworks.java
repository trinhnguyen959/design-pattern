package composite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class NeuralNetworks {
	public static void main(String[] args) {
		Neuron firstNeuron = new Neuron();
		Neuron secondNeuron = new Neuron();

		NeuronLayer firstNeuronLayer = new NeuronLayer();
		NeuronLayer secondNeuronLayer = new NeuronLayer();

		firstNeuron.connectTo(secondNeuron);
		firstNeuron.connectTo(firstNeuronLayer);
		firstNeuronLayer.connectTo(firstNeuron);
		firstNeuronLayer.connectTo(secondNeuronLayer);
	}
}

interface SomeNeurons extends Iterable<Neuron> {
	default void connectTo(SomeNeurons other){
		if (this ==other) return;
		for (Neuron from : this) {
			for (Neuron to : other) {
				from.out.add(to);
				to.in.add(from);
			}
		}
	}
}

class Neuron implements SomeNeurons {
	public ArrayList<Neuron> in, out;

	@Override
	public Iterator<Neuron> iterator() {
		return Collections.singleton(this).iterator();
	}

	@Override
	public void forEach(Consumer<? super Neuron> action) {
		action.accept(this);
	}

	@Override
	public Spliterator<Neuron> spliterator() {
		return Collections.singleton(this).spliterator();
	}

//	public void connectTo(Neuron other) {
//		out.add(other);
//		other.in.add(this);
//	}
}

class NeuronLayer extends ArrayList<Neuron> implements SomeNeurons {}
