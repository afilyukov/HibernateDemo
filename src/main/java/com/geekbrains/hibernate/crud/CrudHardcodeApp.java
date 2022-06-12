package com.geekbrains.hibernate.crud;

import com.geekbrains.hibernate.PrepareDataApp;
import com.geekbrains.hibernate.entites.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class CrudHardcodeApp {
    private static SessionFactory factory;

    public static void init() {
        PrepareDataApp.forcePrepareData();
        factory = new Configuration()
                .configure("configs/crud-hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public static void showAll() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            List<Product> products = session.createQuery("from Product").getResultList();
            System.out.println(products + "\n");

            session.getTransaction().commit();
        }
    }

    public static void shutdown() {
        factory.close();
    }

    public static void createProduct() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product pi = new Product("Product 6", 600);
            session.save(pi);
            session.getTransaction().commit();
        }
    }

    public static void readAndPrintProduct() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product pi = session.get(Product.class, 3L);
            System.out.println("Found the product: "+ pi);
            session.getTransaction().commit();
        }
    }

    public static void updateProduct() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product pi = session.get(Product.class, 2L);
            pi.setPrice(10);
            session.getTransaction().commit();
        }
    }

    public static void deleteProduct() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Product product = session.get(Product.class, 1L);
            session.delete(product);

            session.getTransaction().commit();
        }
    }

    public static void main(String[] args) {
        try {
            init();
            System.out.println("Inited");
            showAll();
            createProduct();
            System.out.println("Created");
            showAll();
            readAndPrintProduct();
            updateProduct();
            System.out.println("Updated");
            showAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            shutdown();
        }
    }
}
