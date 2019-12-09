package za.co.absa.africanacity.AbsaBillingExtract.domain;

import com.github.ffpojo.metadata.positional.annotation.PositionalField;
import com.github.ffpojo.metadata.positional.annotation.PositionalRecord;
import lombok.*;

import java.io.Serializable;

@PositionalRecord
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BillingFileRecord implements Serializable {

    private String clientSwiftAddress;
    private String serviceName;
    private String subServiceName;
    private String date;
    private String usageStatus;

    @PositionalField(initialPosition = 1, finalPosition = 12)
    public String getClientSwiftAddress() {
        return clientSwiftAddress;
    }

    @PositionalField(initialPosition = 13, finalPosition = 20)
    public String getServiceName() {
        return serviceName;
    }

    @PositionalField(initialPosition = 21, finalPosition = 25)
    public String getSubServiceName() {
        return subServiceName;
    }

    @PositionalField(initialPosition = 26, finalPosition = 33)
    public String getDate() {
        return date;
    }

    @PositionalField(initialPosition = 34, finalPosition = 40)
    public String getUsageStatus() {
        return usageStatus;
    }
}
