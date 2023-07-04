package com.danrocha.cde.utils;

import javax.annotation.Priority;
import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.io.Serializable;

@Interceptor
@Transacional
@Priority(Interceptor.Priority.APPLICATION)
public class TransacionalInterceptor implements Serializable {
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
