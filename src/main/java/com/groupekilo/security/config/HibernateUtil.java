package com.groupekilo.security.config;


import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.groupekilo.security.entities.UserEntity;

public class HibernateUtil {
	private static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				PropertiesReader reader=new PropertiesReader("database.properties");
				Configuration configuration = new Configuration();

 				Properties settings = new Properties();
				settings.put(AvailableSettings.DRIVER, "com.mysql.jdbc.Driver");
				settings.put(AvailableSettings.URL,reader.getProperties("db.urlDev"));
				//settings.put(Environment.USER, "user");
				//settings.put(Environment.PASS, "passer123@");
				//settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
				
				settings.put(AvailableSettings.USER, reader.getProperties("db.username"));
				settings.put(AvailableSettings.PASS, reader.getProperties("db.password"));
				settings.put(AvailableSettings.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
				//cette ligne est très importante
				settings.put(AvailableSettings.HBM2DDL_AUTO, "update");
				
				settings.put(AvailableSettings.SHOW_SQL, "true");
				settings.put(AvailableSettings.FORMAT_SQL, "true");

				settings.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS, "thread");

				configuration.setProperties(settings);
                configuration.addAnnotatedClass(UserEntity.class);
				//configuration.addAnnotatedClass(AppRole.class);
 
				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();
 				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
				return sessionFactory;

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}
}
