package com.danrocha.cde.utils;

import jakarta.annotation.Priority;
import jakarta.inject.Inject;
import jakarta.interceptor.AroundInvoke;
import jakarta.interceptor.Interceptor;
import jakarta.interceptor.InvocationContext;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.io.Serial;
import java.io.Serializable;

@Interceptor
@Transacional
@Priority(Interceptor.Priority.APPLICATION)
public class TransacionalInterceptor implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Inject
    private transient EntityManager em;

    @AroundInvoke
    public Object invoke(InvocationContext context) throws Exception {

        EntityTransaction trx = em.getTransaction();
        boolean criado = false;

        try {
            if (!trx.isActive()) {
                /*Truque para fazer rollback no que já passou, para evitar que
                 * um futuro commit confirme até operações sem transação*/
                trx.begin();
                trx.rollback();

                //Agora de fato a transação é iniciada
                trx.begin();

                criado = true;
            }
            return context.proceed();
        } catch (Exception e) {
            if (trx != null && criado) {
                trx.rollback();
            }
            throw e;
        } finally {
            if (trx != null && trx.isActive() && criado) {
                trx.commit();
            }
        }
    }

}
