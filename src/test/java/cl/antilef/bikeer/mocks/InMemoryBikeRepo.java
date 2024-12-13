package cl.antilef.bikeer.mocks;

import cl.antilef.bikeer.bike.entity.Bike;
import cl.antilef.bikeer.bike.repository.BikeRepository;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

public class InMemoryBikeRepo implements BikeRepository {

    private CopyOnWriteArrayList<Bike> bikes;

    public InMemoryBikeRepo(CopyOnWriteArrayList<Bike> bikes){
        this.bikes = bikes;
    }

    @Override
    public List<Bike> findAllByRent(Integer rentId) {
        return bikes;
        //return bikes.stream().filter(bike -> bike.getRentsIds().contains(rentId)).toList();
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
    public <S extends Bike> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Bike> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public Iterable<Bike> findAll() {
        return null;
    }

    @Override
    public Iterable<Bike> findAllById(Iterable<Integer> integers) {
        return bikes;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Bike entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Bike> entities) {

    }

    @Override
    public void deleteAll() {

    }


}