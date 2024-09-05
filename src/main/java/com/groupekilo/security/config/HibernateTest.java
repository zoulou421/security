package com.groupekilo.security.config;

import org.hibernate.Session;

public class HibernateTest {
    public static void main(String[] args) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            System.out.println("Hibernate session opened successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
            HibernateUtil.shutdown();
        }
    }
}

