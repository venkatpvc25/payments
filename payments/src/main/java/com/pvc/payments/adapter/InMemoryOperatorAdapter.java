// package com.pvc.payments.adapter;

// import java.util.List;

// import org.springframework.stereotype.Component;

// import com.pvc.payments.entity.Operator;
// import com.pvc.payments.operator.OperatorPort;
// import com.pvc.payments.operator.OperatorType;

// @Component
// public class InMemoryOperatorAdapter implements OperatorPort {

// @Override
// public List<Operator> getOperators(OperatorType type) {
// return List.of(
// new Operator("AIRTEL", "Airtel", OperatorType.MOBILE),
// new Operator("JIO", "Jio", OperatorType.MOBILE),
// new Operator("VI", "Vodafone Idea", OperatorType.MOBILE));
// }
// }
