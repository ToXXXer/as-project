import com.bxrbasov.project.dao.PersonDao;
import com.bxrbasov.project.dto.CarDto;
import com.bxrbasov.project.dto.PersonDto;
import com.bxrbasov.project.entity.Person;
import com.bxrbasov.project.service.AdressService;
import com.bxrbasov.project.service.CarService;
import com.bxrbasov.project.util.ConnectionManager;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CheckRunner {

    private static final PersonDao personDao = PersonDao.getInstance();
    private static final AdressService adressService = AdressService.getInstance();

    public static PersonDto findByPK(String surname, Connection connection) {
        Person person = personDao.findByPK(surname, connection);
        return new PersonDto(
                person.getSurname(),
                person.getEmail(),
                person.getPhone(),
                person.getFirst_name(),
                person.getLast_name(),
                person.getPrivilege(),
                adressService.findByPK(person.getAdress().getAdress_id(), connection),
                person.getPicture(),
                person.getUser_info()
        );
    }

    @SneakyThrows
    public static void main(String[] args) {
//        ExecutorService executorService = Executors.newFixedThreadPool(10);
//        executorService.submit(() -> {
//            while (true) {
//                CopyOnWriteArrayList<CarDto> cars = new CopyOnWriteArrayList<>();
//                Connection connection = ConnectionManager.takeConnection();
//                for(CarDto carDto:  CarService.getInstance().findAllByParameters(1000000L, Long.MAX_VALUE, -1L, 20000L,
//                        2020, 2024, List.of("Бензин", "Дизель"), List.of("Bentley", "Porsche", "BMW"), connection)) {
//                    cars.add(carDto);
//                }
//                ConnectionManager.putConnection(connection);
//            }
//        });

        String surname = "xyz";
        Connection connection = ConnectionManager.takeConnection();
        PersonDto personDto = CheckRunner.findByPK(surname, connection);
        ConnectionManager.putConnection(connection);
    }
}
