package springSnipplets.advanced;

public class PostProcessComponent {
	private String componentName = null;

	/* Invoked via Spring's init-method callback */
	public void initMe() {
		// Will see the value instanciated by DefaultComponentNamePostProcessor
		// (implements BeanPostProcessor)
		System.out.println("in PostProcessComponent::initMe / componentName = " + componentName);
	}

	public void sayHi() {
		System.out.println("Hi!");
	}

	public String getComponentName() {
		return componentName;
	}

	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
}
