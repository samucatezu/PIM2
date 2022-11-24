package com.samucatezu.pim2.payload.response;

import com.samucatezu.pim2.models.Address;
import com.samucatezu.pim2.models.Insurance;
import com.samucatezu.pim2.models.Role;
import com.samucatezu.pim2.models.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserValor {

    private Long id;

    private String username;

    private String clientSalary;

    private String clientSex;

    private boolean first_access;

    private String clientPhone;

    private String clientDependents;

    private String clientBirthDate;

    private String clientDegree;

    private String clientIdentification;

    private String email;

    private Set<Role> roles = new HashSet<>();

    private List<Address> address;

    private List<Insurance> insurances;

    public UserValor(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.clientSalary = user.getClientSalary();
        this.clientSex = user.getClientSex();
        this.first_access = user.isFirst_access();
        this.clientPhone = user.getClientPhone();
        this.clientDependents = user.getClientDependents();
        this.clientBirthDate = user.getClientBirthDate();
        this.clientDegree = user.getClientDegree();
        this.clientIdentification = user.getClientIdentification();
        this.email = user.getEmail();
        this.roles = user.getRoles();
        this.address = user.getAddress();
        this.insurances = user.getInsurances();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClientSalary() {
        return clientSalary;
    }

    public void setClientSalary(String clientSalary) {
        this.clientSalary = clientSalary;
    }

    public String getClientSex() {
        return clientSex;
    }

    public void setClientSex(String clientSex) {
        this.clientSex = clientSex;
    }

    public boolean isFirst_access() {
        return first_access;
    }

    public void setFirst_access(boolean first_access) {
        this.first_access = first_access;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getClientDependents() {
        return clientDependents;
    }

    public void setClientDependents(String clientDependents) {
        this.clientDependents = clientDependents;
    }

    public String getClientBirthDate() {
        return clientBirthDate;
    }

    public void setClientBirthDate(String clientBirthDate) {
        this.clientBirthDate = clientBirthDate;
    }

    public String getClientDegree() {
        return clientDegree;
    }

    public void setClientDegree(String clientDegree) {
        this.clientDegree = clientDegree;
    }

    public String getClientIdentification() {
        return clientIdentification;
    }

    public void setClientIdentification(String clientIdentification) {
        this.clientIdentification = clientIdentification;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public List<Insurance> getInsurances() {
        return insurances;
    }

    public void setInsurances(List<Insurance> insurances) {
        this.insurances = insurances;
    }
}
