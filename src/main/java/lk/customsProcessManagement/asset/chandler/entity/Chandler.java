package lk.customsProcessManagement.asset.chandler.entity;


import com.fasterxml.jackson.annotation.JsonFilter;
import lk.customsProcessManagement.asset.chandlerLicense.entity.ChandlerLicense;
import lk.customsProcessManagement.util.audit.AuditEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonFilter( "Chandler" )
public class Chandler extends AuditEntity {

    @Size( min = 5, message = "Your Company name cannot be accepted" )
    private String name;

    @Column( nullable = false, unique = true )
    private String code;

    @Size( min = 2, message = "Your BRN cannot be accepted" )
    private String brn;

    @Size( max = 10, min = 9, message = "Mobile number length should be contained 10 and 9" )
    private String contactOne;

    private String contactTwo;

    @Column( unique = true )
    private String email;

    @Column( columnDefinition = "VARCHAR(255) CHARACTER SET utf8 COLLATE utf8_bin NULL", length = 255 )
    private String address;

    @OneToMany(mappedBy = "chandler", cascade = CascadeType.PERSIST )
    private List< ChandlerLicense > chandlerLicenses;


   /* @Enumerated( EnumType.STRING )
    private ItemSupplierStatus itemSupplierStatus;

    @OneToMany( mappedBy = "supplier" )
    private List< PurchaseOrder > purchaseOrders;

    @OneToMany( mappedBy = "supplier" )
    private List< SupplierItem > supplierItems;*/

}
