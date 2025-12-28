# Recharge / Bill Payment Platform – Flow & Architecture

This document explains the **end-to-end flow**, **architecture**, and **design decisions** of the Recharge / Bill Payment backend we are building using **Spring Boot + Clean Architecture**.

---

## 1. What This System Does

The system supports:
- Mobile Recharge
- Electricity Bill Payment
- DTH / TV Recharge
- Broadband / Gas (future)

Key non-functional requirements:
- Money safety (no double debit)
- Refund on failure
- Provider-agnostic
- Async & scalable
- Easy to split into microservices later

---

## 2. High-Level Flow (Bird’s Eye View)

```
Client (Mobile / Web)
        |
        v
API Controller (recharge-api)
        |
        v
ProcessRechargeUseCase (recharge-core)
        |
        +--> WalletPort (Debit / Credit)
        |
        +--> RechargeStrategy
        |        |
        |        v
        |   ProviderPort (External Provider API)
        |
        +--> EventPort (Publish Events)
        |
        +--> NotificationPort (SMS / Email / Push)
        |
        v
Response to Client
```

**Important:**
- Controller is just an entry point
- **Use Case is the brain of the system**

---

## 3. Detailed Step-by-Step Execution Flow

### When a recharge request is received:

```
1. Client sends POST /recharge
2. Controller maps JSON → Request DTO
3. Controller calls ProcessRechargeUseCase.execute()

4. Use Case logic:
   4.1 Create RechargeTransaction (status = INITIATED)
   4.2 WalletPort.debit(userId, amount)
   4.3 Transaction status → PENDING
   4.4 RechargeStrategy.execute()
       4.4.1 ProviderPort.recharge(txn)
   4.5 If provider SUCCESS:
       - status → SUCCESS
       - EventPort.publish(RechargeSuccessEvent)
       - NotificationPort.notifyUser()
   4.6 If provider FAILURE:
       - status → FAILED
       - WalletPort.credit(userId, amount)  // refund
       - EventPort.publish(RechargeFailedEvent)

5. Controller returns response to client
```

---

## 4. Clean Architecture Layered View

```
┌──────────────────────────────┐
│        API Layer             │
│  Controllers, DTOs, Mappers  │
└──────────────┬───────────────┘
               │
               v
┌──────────────────────────────┐
│        USE CASE              │
│  ProcessRechargeUseCase      │
└──────────────┬───────────────┘
               │
     ┌─────────┴─────────┐
     v                   v
┌──────────────┐   ┌──────────────┐
│   DOMAIN     │   │    PORTS     │
│ (Txn, State) │   │ Wallet,     │
│              │   │ Provider…   │
└──────────────┘   └──────────────┘
               │
               v
┌──────────────────────────────┐
│        ADAPTER LAYER         │
│  DB, Kafka, REST, SMS, etc  │
└──────────────────────────────┘
```

**Rule:** Dependencies always point inward.

---

## 5. Strategy Pattern – Recharge Type Handling

Different recharge types behave differently.

```
RechargeType = "MOBILE"

StrategyFactory
        |
        v
MobileRechargeStrategy
        |
        v
ProviderPort.recharge()
```

Adding a new recharge type:
- Create a new Strategy class
- No changes to existing logic

---

## 6. Failure & Refund Flow (Money Safety)

```
Wallet Debit SUCCESS
        |
Provider Call FAILS
        |
Transaction status → FAILED
        |
Wallet Refund (credit)
        |
Event Published
        |
Notification Sent
```

This guarantees:
- No money loss
- Safe retries
- Auditability

---

## 7. Event-Driven Side Effects (Async Processing)

```
RechargeSuccessEvent
        |
        +--> Send SMS / Email
        |
        +--> Update Analytics
        |
        +--> Reconciliation / Reporting
```

The main flow does NOT wait for these operations.

---

## 8. Module Mapping

```
recharge-platform/
│
├── recharge-core           # Domain + Use Cases (no Spring)
├── recharge-api            # Controllers, DTOs
├── recharge-wallet         # Wallet & ledger implementation
├── recharge-provider       # External provider adapters
├── recharge-event          # Kafka / async events
├── recharge-notification   # SMS / Email / Push
└── recharge-app            # Spring Boot main application
```

---

## 9. Why Use Case Is Central

- Represents **one business operation** (Recharge)
- Orchestrates all steps in one place
- Independent of HTTP, DB, Kafka
- Easy to test
- Easy to move to microservices

**Think of it as:**
> Controller asks → Use Case decides → Adapters execute

---

## 10. Key Design Principles Followed

- Clean Architecture
- Hexagonal (Ports & Adapters)
- Strategy Pattern
- Event-driven design
- Refund-first safety
- Modular monolith (microservice-ready)

---

## 11. Next Implementation Steps

1. Wallet implementation (ledger-based)
2. Provider adapter (mock / real)
3. Spring Boot wiring
4. Idempotency handling
5. Kafka-based async processing

---

**This README represents the blueprint of the entire system.**
All code we write will strictly follow this flow.

