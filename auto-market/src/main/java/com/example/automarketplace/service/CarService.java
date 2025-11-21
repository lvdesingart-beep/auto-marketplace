@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public List<Car> findAll() {
        return carRepository.findAll();
    }

    public List<Car> filter(Map<String, String> params) {
        Stream<Car> stream = carRepository.findAll().stream();

        // фільтри
        if (params.containsKey("brand")) stream = stream.filter(c -> c.getBrand().toLowerCase().contains(params.get("brand").toLowerCase()));
        if (params.containsKey("model")) stream = stream.filter(c -> c.getModel().toLowerCase().contains(params.get("model").toLowerCase()));
        if (params.containsKey("yearMin")) stream = stream.filter(c -> c.getYear() >= Integer.parseInt(params.get("yearMin")));
        if (params.containsKey("yearMax")) stream = stream.filter(c -> c.getYear() <= Integer.parseInt(params.get("yearMax")));
        if (params.containsKey("priceMax")) stream = stream.filter(c -> c.getPrice() <= Double.parseDouble(params.get("priceMax")));
        if (params.containsKey("mileageMax")) stream = stream.filter(c -> c.getMileage() <= Integer.parseInt(params.get("mileageMax")));
        if (params.containsKey("city")) stream = stream.filter(c -> c.getCity().toLowerCase().contains(params.get("city").toLowerCase()));
        if (params.containsKey("type")) stream = stream.filter(c -> c.getType().equalsIgnoreCase(params.get("type")));

        // сортування
        String sortBy = params.getOrDefault("sortBy", "");
        switch (sortBy) {
            case "priceAsc": stream = stream.sorted(Comparator.comparing(Car::getPrice)); break;
            case "priceDesc": stream = stream.sorted(Comparator.comparing(Car::getPrice).reversed()); break;
            case "yearAsc": stream = stream.sorted(Comparator.comparing(Car::getYear)); break;
            case "yearDesc": stream = stream.sorted(Comparator.comparing(Car::getYear).reversed()); break;
            case "mileageAsc": stream = stream.sorted(Comparator.comparing(Car::getMileage)); break;
            case "mileageDesc": stream = stream.sorted(Comparator.comparing(Car::getMileage).reversed()); break;
        }

        return stream.collect(Collectors.toList());
    }

    public void save(Car car) {
        carRepository.save(car);
    }

    public int countUserAds(User user) {
        return carRepository.countByOwner(user);
    }
}
