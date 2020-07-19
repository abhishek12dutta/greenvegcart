package com.cart.greenveg.authserver.dao;

import com.cart.greenveg.authserver.model.CartUser;
import com.cart.greenveg.authserver.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
public class OAuthDAOImpl implements OAuthDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public UserEntity getUserDetails(String username) {
        Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();

        List<UserEntity> list = jdbcTemplate.query("SELECT id,firstName,middleName,lastName,mobile,email,passwordHash FROM USER WHERE EMAIL=?",
                new String[]{username},
                (ResultSet rs, int rowNum) -> {
                    UserEntity user = new UserEntity();
                    user.setId(BigInteger.valueOf(rs.getInt("ID")));
                    user.setFirstName(rs.getString("firstName"));
                    user.setMiddleName(rs.getString("middleName"));
                    user.setLastName(rs.getString("lastName"));
                    user.setMobile(rs.getString("mobile"));
                    user.setEmail(rs.getString("email"));
                    user.setPassword(rs.getString("passwordHash"));
                    return user;
                });

        if (!list.isEmpty()) {

            UserEntity userEntity = list.get(0);

            List<String> permissionList = jdbcTemplate.query("SELECT DISTINCT P.PERMISSION_NAME FROM PERMISSION P \n" +
                            "INNER JOIN ASSIGN_PERMISSION_TO_ROLE P_R ON P.ID=P_R.PERMISSION_ID\n" +
                            "INNER JOIN ROLE R ON R.ID=P_R.ROLE_ID\n" +
                            "INNER JOIN ASSIGN_USER_TO_ROLE U_R ON U_R.ROLE_ID=R.ID\n" +
                            "INNER JOIN USER U ON U.ID=U_R.USER_ID\n" +
                            "WHERE U.EMAIL=?", new String[]{userEntity.getEmail()},
                    (ResultSet rs, int rowNum) -> {
                        return "ROLE_" + rs.getString("PERMISSION_NAME");
                    });

            if (permissionList != null && !permissionList.isEmpty()) {
                for (String permission : permissionList) {
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission);
                    grantedAuthoritiesList.add(grantedAuthority);
                }
                userEntity.setGrantedAuthoritiesList(grantedAuthoritiesList);
            }
            return userEntity;
        }

        return null;
    }


    public void createUser(CartUser cartUser) {

        String INSERT_USER_SQL = "INSERT INTO USER(firstName,middleName,lastName,mobile,email,passwordHash,admin,vendor," +
                "registeredAt,lastLogin,intro,profile) " +
                "VALUES (:firstName,:middleName,:lastName,:mobile,:email,:passwordHash,:admin," +
                ":vendor,:registeredAt,:lastLogin,:intro,:profile)";

        String INSERT_ROLE_SQL = "INSERT INTO assign_user_to_role (USER_ID,ROLE_ID) VALUES(:userid,:roleid)";

        SqlParameterSource cartUserParameters = new MapSqlParameterSource()
                .addValue("firstName", cartUser.getFirstName())
                .addValue("middleName", cartUser.getMiddleName())
                .addValue("lastName", cartUser.getLastName())
                .addValue("mobile", cartUser.getMobile())
                .addValue("email", cartUser.getEmail())
                .addValue("passwordHash", cartUser.getPassword())
                .addValue("admin", false)
                .addValue("vendor", false)
                .addValue("registeredAt", LocalDateTime.now())
                .addValue("lastLogin", null)
                .addValue("intro", "Test Intro")
                .addValue("profile", "Test Profile");

        KeyHolder holder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(INSERT_USER_SQL, cartUserParameters, holder);

        SqlParameterSource rolesParameters = new MapSqlParameterSource()
                .addValue("userid", holder.getKey().intValue())
                .addValue("roleid", 1);

        namedParameterJdbcTemplate.update(INSERT_ROLE_SQL, rolesParameters);

    }


}
