package com.RestaurantAppV2.table.repository;

import com.RestaurantAppV2.table.RestaurantTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SqlTableRepository extends TableRepository, JpaRepository<RestaurantTable, Integer>
{
//    TODO uniwersalny zapis statusu
//    TODO jak wykluczyć stoliki z rezerwacją o danej godzinie?
//    @Override
//    @Query(value = "SELECT * FROM tables JOIN (SELECT * FROM reservations WHERE  ) ON r.table_id = tables.id WHERE status = 'Wolny' AND seats >= :seats", nativeQuery = true)
//    List<RestaurantTable> getFreeTablesByGivenHourAndSeats(LocalDateTime time, Integer seats);

    @Override
    @Query(value = "FROM RestaurantTable  t JOIN FETCH t.reservations WHERE t.seats >= :seats ")
    List<RestaurantTable> getRestaurantTablesBySeats(Integer seats);

    @Override
    @Query(value = "FROM RestaurantTable  t JOIN FETCH t.reservations WHERE t.seats >= :seats AND t.status = 'Wolny'")
    List<RestaurantTable> getAvailableRestaurantTablesBySeats(Integer seats);

    @Override
    @Query(value = "UPDATE RestaurantTable t SET t.status = :status WHERE t.name = :name")
    void updateTableStatusByName(String name, String status);


}
