package net.unesc.tcc.gabriel.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {

    private static ThreadLocal<EntityManager> threadLocal = new ThreadLocal<>();
    private static EntityManagerFactory emf;

    public static EntityManager openEM() {
        EntityManager em = getEMF().createEntityManager();
        return em;
    }

    public static EntityManager getEM() {
        if (threadLocal.get() == null) {
            threadLocal.set(openEM());
        }
        return (EntityManager) threadLocal.get();
    }

    public static void closeEM() {
        getEM().close();
        threadLocal.set(null);
    }

    public static EntityManagerFactory getEMF() {
        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("WebTCCPU");
        }
        return emf;
    }
}

