package lk.customsProcessManagement.asset.employee.entity.Enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmployeeStatus {
    WORKING("Working"),
    LEAVE("Leave"),
    SUSPENDED("Suspended"),
    NOPAY("No pay"),
    MEDICAL("Medical Leave"),
    BLOCK("Block"),
    RESIGNED("Resigned"),
    RETIRED("Retired");

    private final String employeeStatus;


}
