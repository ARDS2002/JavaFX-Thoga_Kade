package dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {

    private String customerID;
    private String customerTitle;
    private String customerName;
    private String customerAddress;
    private String customerContactNumber;
    private LocalDate customerDOB;

    public String getCustomerName() {
        return this.customerTitle + " " + this.customerName;
    }
}
