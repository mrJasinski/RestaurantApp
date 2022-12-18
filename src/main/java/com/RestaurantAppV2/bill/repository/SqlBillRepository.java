package com.RestaurantAppV2.bill.repository;

import com.RestaurantAppV2.bill.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface SqlBillRepository extends BillRepository, JpaRepository<Bill, Integer>
{
    //    TODO - zapis status w sposób bardziej uiwersalny
    @Override
    @Query(value = "SELECT * FROM bills WHERE table_id = :tableId AND status = 'Otwarty'", nativeQuery = true)
    Optional<Bill> findOpenBillByTableId(Integer tableId);

    @Override
    @Query(value = "", nativeQuery = true)
    Boolean existsByTableIdAndStatus(Integer tableId, String status);

    @Override
    @Query(value = "UPDATE bills SET status = :status WHERE number = :number ", nativeQuery = true)
    void updateBillStatus(String number, String status);

    @Override
    @Query(value = "SELECT COUNT(*) FROM bills WHERE closed_at = :date", nativeQuery = true)
    Integer countBillByClosedAtDate(LocalDate date);

//    TODO kompilator nie przepuszcza - dodać sqlkę

    @Override
    @Query(value = "", nativeQuery = true)
    Double sumBillsByClosedAtDate(LocalDate date);
}
