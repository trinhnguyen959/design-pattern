package solid;

import java.util.List;
import java.util.stream.Stream;

enum Color {
	RED, GREEN, BLUE
}

enum Size {
	SMALL, MEDIUM, LARGE, YUGE
}

/*SOLUTION*/
interface Specification<T> {
	boolean isSatisfied(T item);
}

interface Filter<T> {
	Stream<T> filter(List<T> items, Specification<T> spec);
}

public class OpenClosed {

	public static void main(String[] args) {
		Product apple = new Product("Apple", Color.GREEN, Size.SMALL);
		Product tree = new Product("Tree", Color.GREEN, Size.LARGE);
		Product house = new Product("House", Color.BLUE, Size.LARGE);
		List<Product> products = List.of(apple, tree, house);

		ProductFilter productFilter = new ProductFilter();
		System.out.println("Green products (old):");
		productFilter.filterByColor(products, Color.GREEN)
				.forEach(product -> System.out.println(" - " + product.name + " is green"));

		BetterFilter betterFilter = new BetterFilter();
		System.out.println("\nGreen products (new):");
		betterFilter.filter(products, new ColorSpecification(Color.GREEN))
				.forEach(product -> System.out.println(" - " + product.name + " is green"));

		System.out.println("\n Large blue items: ");
		betterFilter.filter(products, new AndSpecification<>(
						new ColorSpecification(Color.BLUE), new SizeSpecification(Size.LARGE)))
				.forEach(product -> System.out.println(" - " + product.name + " is blue and large!"));
	}
}

class Product {
	public String name;
	public Color color;
	public Size size;

	public Product(String name, Color color, Size size) {
		this.name = name;
		this.color = color;
		this.size = size;
	}
}

class ProductFilter {
	public Stream<Product> filterByColor(List<Product> products, Color color) {
		return products.stream().filter(product -> product.color == color);
	}

	public Stream<Product> filterBySize(List<Product> products, Size size) {
		return products.stream().filter(product -> product.size == size);
	}

	public Stream<Product> filterBySizeAndColor(List<Product> products, Size size, Color color) {
		return products.stream().filter(product -> product.size == size && product.color == color);
	}
}

class ColorSpecification implements Specification<Product> {
	private final Color color;

	public ColorSpecification(Color color) {
		this.color = color;
	}

	@Override
	public boolean isSatisfied(Product item) {
		return item.color == color;
	}
}

class SizeSpecification implements Specification<Product> {
	private final Size size;

	public SizeSpecification(Size size) {
		this.size = size;
	}

	@Override
	public boolean isSatisfied(Product item) {
		return item.size == size;
	}
}

class BetterFilter implements Filter<Product> {

	@Override
	public Stream<Product> filter(List<Product> items, Specification<Product> spec) {
		return items.stream().filter(spec::isSatisfied);
	}
}

class AndSpecification<T> implements Specification<T> {
	private final Specification<T> first, second;

	public AndSpecification(Specification<T> first, Specification<T> second) {
		this.first = first;
		this.second = second;
	}

	@Override
	public boolean isSatisfied(T item) {
		return first.isSatisfied(item) && second.isSatisfied(item);
	}
}