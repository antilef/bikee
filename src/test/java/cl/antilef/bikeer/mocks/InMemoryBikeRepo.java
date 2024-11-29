package cl.antilef.bikeer.mocks;

import cl.antilef.bikeer.bike.entity.Bike;
import cl.antilef.bikeer.bike.repository.BikeRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;

public class InMemoryBikeRepo implements BikeRepository {

    private CopyOnWriteArrayList<Bike> bikes;

    public InMemoryBikeRepo(CopyOnWriteArrayList<Bike> bikes){
        this.bikes = bikes;
    }

    @Override
    public List<Bike> findAllIn(List<String> ids) {
        return this.bikes.stream().filter(elem -> ids.contains(elem.getId()) ).toList();
    }

    @Override
    public <S extends Bike> S insert(S entity) {
        return null;
    }

    @Override
    public <S extends Bike> List<S> insert(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public <S extends Bike> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Bike> List<S> findAll(Example<S> example) {
        return List.of();
    }

    @Override
    public <S extends Bike> List<S> findAll(Example<S> example, Sort sort) {
        return List.of();
    }

    @Override
    public <S extends Bike> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Bike> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Bike> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Bike, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Bike> S save(S entity) {
        int index = -1;

        for(int i = 0;i < this.bikes.toArray().length;i++){
            if(bikes.get(i).getId().equals(entity.getId())){
                index = i;
            }
        }
        if(index>-1){
            this.bikes.set(index,entity);
        }else{
            this.bikes.add(entity);
        }

        return entity;
    }

    @Override
    public <S extends Bike> List<S> saveAll(Iterable<S> entities) {
        return List.of();
    }

    @Override
    public Optional<Bike> findById(String s) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(String s) {
        return false;
    }

    @Override
    public List<Bike> findAll() {
        return List.of();
    }

    @Override
    public List<Bike> findAllById(Iterable<String> strings) {
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
    public void delete(Bike entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends String> strings) {

    }

    @Override
    public void deleteAll(Iterable<? extends Bike> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Bike> findAll(Sort sort) {
        return List.of();
    }

    @Override
    public Page<Bike> findAll(Pageable pageable) {
        return null;
    }
}