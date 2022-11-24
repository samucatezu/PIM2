//package com.samucatezu.pim2.security;
//
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.sql.DataSource;
//import java.net.URI;
//import java.net.URISyntaxException;
//
//@Configuration
//public class DataBaseUriConfiguration {
//
//
//    @Bean
//    public DataSource getDataSource() throws Exception {
//        URI uri = getDbUri();
//
//        String[] userAndPassword = uri.getUserInfo().split(":");
//        String user = userAndPassword[0];
//        String password = userAndPassword[1];
//        String url = String.format("jdbc:mysql://%s:%d/%s", uri.getHost(), uri.getPort(), uri.getPath().substring(1));
//
//        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
//        dataSourceBuilder.driverClassName("org.hibernate.dialect.MySQL8Dialect");
//        dataSourceBuilder.url(url);
//        dataSourceBuilder.username(user);
//        dataSourceBuilder.password(password);
//        return dataSourceBuilder.build();
//    }
//
//    private URI getDbUri() throws Exception {
//        try {
//            String uriString = System.getenv("CLEARDB_DATABASE_URL");
//            return new URI(uriString);
//        } catch (URISyntaxException e) {
//            throw new Exception("Error trying to get data base uri from environment");
//        }
//    }
//}
