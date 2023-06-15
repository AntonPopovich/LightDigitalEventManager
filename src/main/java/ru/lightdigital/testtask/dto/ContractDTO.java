package ru.lightdigital.testtask.dto;

import ru.lightdigital.testtask.models.Principal;
import ru.lightdigital.testtask.util.ContractStatus;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class ContractDTO {
    @NotEmpty(message = "Principal should not be empty")
    private String principalName;

    @NotEmpty(message = "Cost should not be empty")
    @Min(value = 0, message = "Cost should be greater than 0")
    private float cost;

    private Principal principal;

    public ContractDTO() {
    }

    public ContractDTO(ContractStatus status, float cost, String principalName) {
        this.cost = cost;
        this.principalName = principalName;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getPrincipalName() {
        return principalName;
    }

    public void setPrincipalName(String principalName) {
        this.principalName = principalName;
    }

    public Principal getPrincipal() {
        return principal;
    }

    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }
}
