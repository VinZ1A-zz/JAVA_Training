package springSnipplets.advanced;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

// Will be applied to all beans automatically
public class DefaultComponentNamePostProcessor implements BeanPostProcessor {

	@Override
	// will happen just before the init-method
	// Note: called for ALL beans
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("PostProcess BEFORE init: " + bean);
		if (bean instanceof PostProcessComponent) {
			((PostProcessComponent) bean).setComponentName("SOME-DEFAULT");
		}
		return bean; // return modified object
	}

	@Override
	// will happen just just after the init-method
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		System.out.println("PostProcess AFTER init: " + bean);
		if (bean instanceof PostProcessComponent) {
			System.out.println("Default Property: " + ((PostProcessComponent) bean).getComponentName());
		}
		return bean;
	}

}
