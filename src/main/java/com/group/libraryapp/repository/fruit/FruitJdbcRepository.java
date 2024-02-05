package com.group.libraryapp.repository.fruit;

import com.group.libraryapp.domain.fruit.Fruit;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FruitJdbcRepository {
    private final JdbcTemplate jdbcTemplate;

    public FruitJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveAllBatch(List<Fruit> fruits) {
        for (List<Fruit> chunk : chunks(fruits, 10_000)) {
            jdbcTemplate.batchUpdate("INSERT INTO fruit(name, price) VALUES (?, ?)", new BatchPreparedStatementSetter() {
                @Override
                public void setValues(PreparedStatement ps, int i) throws SQLException {
                    ps.setString(1, chunk.get(i).getName());
                    ps.setInt(2, chunk.get(i).getPrice());
                }

                @Override
                public int getBatchSize() {
                    return chunk.size();
                }
            });
        }
    }

    private static List<List<Fruit>> chunks(List<Fruit> inputs, int chunkSize) {
        List<List<Fruit>> result = new ArrayList<>();
        int size = inputs.size();
        int currStep = 0;

        while (currStep + chunkSize < size) {
            result.add(new ArrayList<>(inputs.subList(currStep, currStep + chunkSize)));
            currStep += chunkSize;
        }

        result.add(new ArrayList<>(inputs.subList(currStep, size)));
        return result;
    }
}
