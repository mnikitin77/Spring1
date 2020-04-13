package com.mvnikitin.hiberexamp.dao;

import com.mvnikitin.hiberexamp.domain.Customer;
import com.mvnikitin.hiberexamp.domain.Product;
import com.mvnikitin.hiberexamp.domain.Purchase;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class HibernateExampleDataManager {
    private EntityManagerFactory factory;

    public HibernateExampleDataManager() {
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        populateDBwithInitialData();
    }

    public void close() {
        factory.close();
    }

    public List<Customer> getCustomers() {
        EntityManager em = factory.createEntityManager();
        return em.createNamedQuery("Customer.findAll", Customer.class)
                .getResultList();
    }

    public List<Customer> getCustomer(Long id) {
        List<Customer> result = new ArrayList<>();
        EntityManager em = factory.createEntityManager();
        result.add(em.find(Customer.class, id));
        return result;
    }

    public List<Product> getProducts() {
        EntityManager em = factory.createEntityManager();
        return em.createNamedQuery("Product.findAll", Product.class)
                .getResultList();
    }

    public List<Product> getProduct(Long id) {
        List<Product> result = new ArrayList<>();
        EntityManager em = factory.createEntityManager();
        result.add(em.find(Product.class, id));
        return result;
    }

    public List<Purchase> getPurchases() {
        EntityManager em = factory.createEntityManager();
        return em.createNamedQuery("Purchase.findAll", Purchase.class)
                .getResultList();
    }

    public List<Purchase> getPurchases(Long customerId) {
        List<Customer> list = getCustomer(customerId);
        EntityManager em = factory.createEntityManager();
        return em.createNamedQuery("Purchase.findByCustomer", Purchase.class)
                .setParameter("customer", list.get(0)).getResultList();
    }

    public List<Purchase> getPurchase(Long id) {
        List<Purchase> result = new ArrayList<>();
        EntityManager em = factory.createEntityManager();
        result.add(em.find(Purchase.class, id));
        return result;
    }

    public Long addCustomer(String name) {
        Customer customer = new Customer(name);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(customer);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return -1L;
        }
        return customer.getId();
    }

    public Long addProduct(String name, double price) {
        Product product = new Product(name, price);
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(product);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return -1L;
        }
        return product.getId();
    }

    public Long addPurchase(Long customerId, List<Long> purchaseItems) {
        Purchase purchase = new Purchase();
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        try {
            Customer customer = em.find(Customer.class, customerId);
            for (long id: purchaseItems) {
                purchase.addProduct(em.find(Product.class, id));
            }
            customer.addPurchase(purchase);
            em.persist(purchase);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return -1L;
        }
        return purchase.getId();
    }

    public boolean removeProduct(Long id) {
        EntityManager em = factory.createEntityManager();
        Product product = em.find(Product.class, id);
        em.getTransaction().begin();
        try {
            em.remove(product);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean removeCustomer(Long id) {
        EntityManager em = factory.createEntityManager();
        Customer customer = em.find(Customer.class, id);
        em.getTransaction().begin();
        try {
            List<Purchase> purchases = customer.getPurchases();
            for (Purchase p: purchases) {
                em.remove(p);
            }
            em.remove(customer);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean removePurchase(Long id) {
        EntityManager em = factory.createEntityManager();
        Purchase purchase = em.find(Purchase.class, id);
        em.getTransaction().begin();
        try {
            em.remove(purchase);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void populateDBwithInitialData() {
        List<Product> products = new ArrayList<>();
        List<Customer> customers = new ArrayList<>();

        products.add(new Product("Морковь, пакет 1 кг", 67.0));
        products.add(new Product("Лук, пакет 1кг", 89.0));
        products.add(new Product("Апельсины крупные, пакет 1кг", 135.0));
        products.add(new Product("Соль морская крупная, упакока 250г", 299.0));
        products.add(new Product("Картофель, пакет 1кг", 59.0));

        Customer customer;
        Purchase purchase;

        customer = new Customer("Иванов Сергей Викторович");
        purchase = new Purchase();
        purchase.addProduct(products.get(0));
        purchase.addProduct(products.get(1));
        purchase.addProduct(products.get(2));
        customer.addPurchase(purchase);
        customers.add(customer);

        customer = new Customer("Andy Ruis");
        purchase = new Purchase();
        purchase.addProduct(products.get(0));
        purchase.addProduct(products.get(1));
        purchase.addProduct(products.get(2));
        purchase.addProduct(products.get(3));
        purchase.addProduct(products.get(4));
        customer.addPurchase(purchase);
        customers.add(customer);

        customer = new Customer("Пушкин Александр Сергеевич");
        purchase = new Purchase();
        purchase.addProduct(products.get(2));
        purchase.addProduct(products.get(3));
        purchase.addProduct(products.get(4));
        customer.addPurchase(purchase);
        customers.add(customer);

        customer = new Customer("Arturo Gatti");
        customers.add(customer);

        customer = new Customer("Michael `IRON` Tyson");
        purchase = new Purchase();
        purchase.addProduct(products.get(0));
        purchase.addProduct(products.get(2));
        purchase.addProduct(products.get(4));
        customer.addPurchase(purchase);
        customers.add(customer);

        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();

        try {
            for (Product p : products) {
                em.persist(p);
            }

            for (Customer c : customers) {
                em.persist(c);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
