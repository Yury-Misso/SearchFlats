package by.itacademy.user.aop;



import by.itacademy.user.aop.eaudit.EssenceType;
import by.itacademy.user.aop.eaudit.Text;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Audit {

    Text text();

    EssenceType essenceType();

}
