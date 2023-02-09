package ifrn.pi.projeto.models;

import java.io.Serializable;
import java.util.UUID;

import javax.management.relation.Role;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TB_ROLE")
public class Rolemodel implements GrantedAuthority, Serializable {
private static final long serialVersionUID = 1L;

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private UUID roleID;
@Enumerated(EnumType.STRING)
@Column(nullable = false, unique = true)
private Role roleName;



@Override
public String getAuthority() {
// TODO Auto-generated method stub
return this.roleName.toString();
}



public UUID getRoleID() {
return roleID;
}



public void setRoleID(UUID roleID) {
this.roleID = roleID;
}



public Role getRoleName() {
return roleName;
}



public void setRoleName(Role roleName) {
this.roleName = roleName;
}


}