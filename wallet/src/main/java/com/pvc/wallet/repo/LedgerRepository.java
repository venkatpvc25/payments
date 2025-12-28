package com.pvc.wallet.repo;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.pvc.wallet.entity.Ledger;

@Repository
public interface LedgerRepository extends JpaRepository<Ledger, UUID> {

    @Query("""
                SELECT COALESCE(SUM(
                    CASE
                        WHEN l.type = 'CREDIT' THEN l.amount
                        ELSE -l.amount
                    END
                ), 0)
                FROM Ledger l
                WHERE l.userId = :userId
            """)
    BigDecimal calculateBalance(@Param("userId") String userId);

    boolean existsByClientTxnIdAndType(String clientTxnId, String type);

}
