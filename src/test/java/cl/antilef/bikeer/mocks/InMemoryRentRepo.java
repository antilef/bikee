package cl.antilef.bikeer.mocks;

import cl.antilef.bikeer.rent.entity.Rent;
import cl.antilef.bikeer.rent.repository.RentRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;

public class InMemoryRentRepo implements RentRepository {

    private CopyOnWriteArrayList<Rent> rents;

    public InMemoryRentRepo(CopyOnWriteArrayList<Rent> rents){
        this.rents = rents;
    }
    @Override
    public <S extends Rent> S save(S entity) {
        entity.setId((int) Math.abs(UUID.randomUUID().getMostSignificantBits()));
        this.rents.add(entity);
        return entity;
    }
    @Override
    public Iterable<Rent> findAllById(Iterable<Integer> integers) {
        return rents;
    }


    @Override
    public List<Rent> findByUserId(Integer userId) {
        return List.of();
    }


    @Override
    public <S extends Rent> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Rent> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public Iterable<Rent> findAll() {
        return null;
    }


    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Rent entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Rent> entities) {

    }

    @Override
    public void deleteAll() {

    }
}