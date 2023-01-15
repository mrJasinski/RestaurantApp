package com.RestaurantAppV2.bill.repository;

import com.RestaurantAppV2.bill.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

@Repository
public interface SqlBillRepository extends BillRepository, JpaRepository<Bill, Integer>
{
    //    TODO - zapis status w sposób bardziej uiwersalny
    @Override
    @Query(value = "SELECT * FROM bills WHERE table_id = :tableId AND status = 'Otwarty'", nativeQuery = true)
    Optional<Bill> findOpenBillByTableId(Integer tableId);

    @Override
    @Query(value = "SELECT EXISTS(SELECT * FROM bills WHERE table_id = :tableId AND status = :status)", nativeQuery = true)
    Boolean existsByTableIdAndStatus(Integer tableId, String status);

    @Override
    @Query(value = "UPDATE bills SET status = :status WHERE number = :number ", nativeQuery = true)
    void updateBillStatus(String number, String status);

    @Override
    @Query(value = "SELECT COUNT(*) FROM bills WHERE closed_at = :date", nativeQuery = true)
    Integer countBillByClosedAtDate(LocalDate date);

    @Override
    @Query(value = "SELECT SUM(SELECT SUM(price) FROM meals AS m WHERE m.bill_id = b.id) FROM bills AS b WHERE closed_at = :date", nativeQuery = true)
    Double sumBillsByClosedAtDate(LocalDate date);

    @Override
    @Query(value = "SELECT MAX(b.number) FROM Bill b WHERE b.table.name = :tableName AND extract(MONTH FROM b.createdAt) = :month " +
            "AND extract(YEAR FROM b.createdAt) = :year")
    Optional<Integer> findLastBillNumberByTableNameAndMonthAndYear(String tableName, Month month, int year);

    @Override
    @Query(value = "UPDATE Bill b SET b.amount = :amount WHERE b.number = :number")
    void updateBillAmountInDb(String number, Double amount);

}
