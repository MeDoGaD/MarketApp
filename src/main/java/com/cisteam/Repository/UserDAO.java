package com.cisteam.Repository;

import com.cisteam.models.User;

import javax.sql.DataSource;

public interface UserDAO {
void register(User user);
public void setDataSource(DataSource dataSource);
}
