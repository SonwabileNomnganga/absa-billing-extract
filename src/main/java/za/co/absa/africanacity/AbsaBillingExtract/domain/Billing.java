package za.co.absa.africanacity.AbsaBillingExtract.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "billing")
public class Billing implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @Column(name = "transaction_reference")
    private String transactionReference;

    @NonNull
    @Column(name = "client_swift_address")
    private String clientSwiftAddress;

    @NonNull
    @Column(name = "message_satus")
    private String messageStatus;

    @NonNull
    @Column(name = "currency")
    private String currency;

    @NonNull
    @Column(name = "amount")
    private String amount;

    @NonNull
    @Column(name = "date_time_created")
    private String dateTimeCreated;
}
