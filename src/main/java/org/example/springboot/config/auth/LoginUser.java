package org.example.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) // 이 어노테이션이 생성될 수 있는 위치를 지정 => 매개변수로 선언된 객체에만 사용 가능하게 한다.
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser { // @interface: 어노테이션 클래스로 지정, LoginUser라는 어노테이션을 생성했다고 보면 된다.
}
