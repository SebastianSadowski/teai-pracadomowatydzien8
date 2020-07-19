package pl.sadowski.teaipracadomowatydzien8.db;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DBConfig {


    final String url = "jdbc:mysql://localhost:3306/teai_szkolenie?serverTimezone=UTC";
    final String driverClassName = "com.mysql.cj.jdbc.Driver";
   // @Value("${username}")
    String username ="tempuser";
   // @Value("${password}")
    String password = "DUPADUPA";

@Bean
    public DataSource getDataSource() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        return dataSourceBuilder.url(url)
                .username(username)
                .password(password)
                .driverClassName(driverClassName)
                .build();

    }

}
