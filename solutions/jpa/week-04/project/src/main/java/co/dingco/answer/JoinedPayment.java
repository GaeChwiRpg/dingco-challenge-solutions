package co.dingco.answer;
import jakarta.persistence.*;
@Entity @Inheritance(strategy=InheritanceType.JOINED)
abstract class JoinedPayment { @Id @GeneratedValue Long id; long amount; protected JoinedPayment(){} JoinedPayment(long amount){this.amount=amount;} }
@Entity class JoinedCardPayment extends JoinedPayment {
  String approvalCode; protected JoinedCardPayment(){} JoinedCardPayment(long amount,String code){super(amount);this.approvalCode=code;}
}
