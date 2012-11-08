package spoon.examples.tracing.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target({ElementType.METHOD,ElementType.CONSTRUCTOR})
public @interface Log {
	
	public String p() default "default value for p";
	public Class WidgetClass();
}
