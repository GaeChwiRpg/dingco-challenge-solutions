package co.dingco.answer;
import jakarta.persistence.*;
@Entity @Inheritance(strategy=InheritanceType.SINGLE_TABLE) @DiscriminatorColumn(name="kind")
abstract class SinglePayment { @Id @GeneratedValue Long id; long amount; protected SinglePayment(){} SinglePayment(long amount){this.amount=amount;} }
@Entity @DiscriminatorValue("CARD") class SingleCardPayment extends SinglePayment {
  String approvalCode; protected SingleCardPayment(){} SingleCardPayment(long amount,String code){super(amount);this.approvalCode=code;}
}
