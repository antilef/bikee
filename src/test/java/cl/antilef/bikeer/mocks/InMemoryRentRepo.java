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
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;

public class InMemoryRentRepo implements RentRepository {

    private CopyOnWriteArrayList<Rent> rents;

    public InMemoryRentRepo(CopyOnWriteArrayList<Rent> rents){
        this.rents = rents;
    }


    @Override
    public List<Rent> findByUserId(String userId) {
        return List.of();
    }

    @Override
    public <S extends Rent> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends Rent> List<S> insert(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public <S extends Rent> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Rent> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends Rent> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends Rent> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Rent> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Rent> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Rent, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Rent> S save(S entity) {
        this.rents.add(entity);
        return entity;
    }

    @Override
    public <S extends Rent> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<Rent> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public List<Rent> findAll() {
        return List.of();
    }

    @Override
    public List<Rent> findAllById(Iterable<String> strings) {
        return List.of();
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(String s) {

    }

    @Override
    public void delete(Rent entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends Rent> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Rent> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<Rent> findAll(Pageable pageable) {
        return null;
    }
}