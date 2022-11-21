package com.cisteam.Repository;

import com.cisteam.models.User;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.Date;

public class UserDAOImpl implements UserDAO{
private JdbcTemplate template;
private DataSource dataSource;

    @Override
    public void register(User user) {
        user.setDate(new Date());
      String sql="insert into users (username,fullname,password,date) values (?,?,?,?)";
      template.update(sql,new Object[]{user.getUsername(),user.getFullname(),user.getPassword(),user.getDate()});

        String sql2="insert into user_roles (username,role) values (?,?)";
        template.update(sql2,new Object[]{user.getUsername(),"ROLE_USER"});
    }

    @Override
    public void setDataSource(DataSource dataSource) {
     this.dataSource=dataSource;
     this.template=new JdbcTemplate(dataSource);
    }

}
