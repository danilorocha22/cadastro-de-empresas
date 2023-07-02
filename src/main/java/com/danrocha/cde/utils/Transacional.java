package com.danrocha.cde.utils;

import jakarta.interceptor.InterceptorBinding;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/*@Target({ElementType.METHOD, ElementType.TYPE}), ela indica que a
anotação pode ser aplicada tanto em métodos quanto em tipos (classes,
interfaces, enumerações, anotações).*/
@Target({ElementType.METHOD, ElementType.TYPE})

/* @Retention(RetentionPolicy.RUNTIME) indica que a anotação será retida e
estará disponível durante a execução do programa, permitindo que seja
acessada em tempo de execução por meio de reflexão, por exemplo. Essa é a
 política de retenção mais ampla e permite o uso dinâmico da anotação em
 tempo de execução.*/
@Retention(RetentionPolicy.RUNTIME)

/*@InterceptorBinding em uma anotação personalizada, você está indicando
que essa anotação pode ser usada como um ponto de ligação para interceptores.
 Isso permite que você defina ações adicionais a serem executadas antes ou
 depois dos métodos anotados com essa anotação personalizada.*/
@InterceptorBinding
public @interface Transacional {
}
