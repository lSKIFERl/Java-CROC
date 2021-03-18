package com.skifer.hw3;

class Bike extends Transport implements Ground {

    private int defaultWheelCount;
    private int wheelCount;

    /**
     * Велосипед
     * @param state Физическое состояние
     * @param rent Состояние аренды
     * @param id идентификатор
     * @param rentPrice цена за аренду
     * @param defaultWheelCount нормальное количество колёс
     */
    public Bike(State state, Rent rent, int id, int rentPrice, int defaultWheelCount) {
        super(state, rent, id, rentPrice);
        this.defaultWheelCount = defaultWheelCount;
        this.wheelCount = defaultWheelCount;
    }

    public Bike(int id, int rentPrice) {
        this(State.PERFECT, Rent.FREE, id, rentPrice, 2);
    }

    @Override
    public int getWheelCount() {
        return this.wheelCount;
    }

    @Override
    public int getDefaultWheelCount() {
        return this.defaultWheelCount;
    }

    @Override
    public void setWheelCount(int count) {
        this.wheelCount = count;
    }

}


class HoverBoard extends Transport implements Ground, Motorized {

    private int defaultWheelCount, wheelCount, defaultNumberOfEngines, numberOfEngines;
    private Engine engine;

    /**
     * Гироскутер
     * @param state физическое состояние
     * @param rent статус аренды
     * @param id идентификатор
     * @param rentPrice цена за аренду
     * @param defaultWheelCount нормально количество колёс
     * @param defaultNumberOfEngines нормальное количество моторов
     * @param engine тип двигателя
     */
    public HoverBoard(State state, Rent rent, int id, int rentPrice,
                      int defaultWheelCount, int defaultNumberOfEngines, Engine engine) {
        super(state, rent, id, rentPrice);
        this.defaultWheelCount = defaultWheelCount;
        this.wheelCount = defaultWheelCount;
        this.defaultNumberOfEngines = defaultNumberOfEngines;
        this.numberOfEngines = defaultNumberOfEngines;
        this.engine = engine;
    }

    public HoverBoard(int id, int rentPrice) {
        this(State.PERFECT, Rent.FREE, id, rentPrice, 2, 1, Engine.ELECTRIC);
    }

    @Override
    public int getWheelCount() {
        return this.wheelCount;
    }

    @Override
    public int getDefaultWheelCount() {
        return this.defaultWheelCount;
    }

    @Override
    public void setWheelCount(int count) {
        this.wheelCount = count;
        if(getDefaultWheelCount() != count)
            setState(State.HARDLY_DAMAGED);
    }

    @Override
    public int getDefaultNumberOfEngines() {
        return this.defaultNumberOfEngines;
    }

    @Override
    public int getNumberOfEngines() {
        return this.numberOfEngines;
    }

    @Override
    public void setNumberOfEngines(int count) {
        this.numberOfEngines = count;
        if(getDefaultNumberOfEngines() != count)
            setState(State.BROKEN);
    }

    @Override
    public Engine getEngineType() {
        return this.engine;
    }

}

class PeekScooter extends Bike {

    private int parkingID;

    /**
     * Самокат
     * @param state физическое состояние
     * @param rent статус аренды
     * @param id идентификатор
     * @param rentPrice стоимость аренды
     * @param defaultWheelCount нормальное количество колёс
     * @param parkingID идентификатор парковки
     */
    public PeekScooter(State state, Rent rent, int id, int rentPrice, int defaultWheelCount, int parkingID) {
        super(state, rent, id, rentPrice, defaultWheelCount);
        this.parkingID = parkingID;
    }

    public PeekScooter(int id, int rentPrice, int parkingID) {
        this(State.PERFECT, Rent.FREE, id, rentPrice, 2, parkingID);
    }

    public int getParkingID() {
        return parkingID;
    }

    public void setParkingID(int parkingID) {
        this.parkingID = parkingID;
    }
}

class ElectricPeekScooter extends PeekScooter implements Motorized {


    private int defaultNumberOfEngines, numberOfEngines;
    private Engine engine;

    /**
     * Электро самокат
     * @param state физическое состояние
     * @param rent статус аренды
     * @param id идентификатор
     * @param rentPrice стоимость аренды
     * @param defaultWheelCount нормальное количество колёс
     * @param parkingID идентификатор парковки
     * @param defaultNumberOfEngines нормально количество двигателей
     * @param engine тип двигателя
     */
    public ElectricPeekScooter(State state, Rent rent, int id, int rentPrice,
                               int defaultWheelCount, int parkingID, int defaultNumberOfEngines, Engine engine) {
        super(state, rent, id, rentPrice, defaultWheelCount, parkingID);
        this.defaultNumberOfEngines = defaultNumberOfEngines;
        this.numberOfEngines = defaultNumberOfEngines;
        this.engine = engine;
    }

    public ElectricPeekScooter(int id, int rentPrice, int parkingID) {
        this(State.PERFECT, Rent.FREE, id, rentPrice, 2, parkingID, 1, Engine.ELECTRIC);
    }

    @Override
    public int getDefaultNumberOfEngines() {
        return this.defaultNumberOfEngines;
    }

    @Override
    public int getNumberOfEngines() {
        return this.numberOfEngines;
    }

    @Override
    public void setNumberOfEngines(int count) {
        this.numberOfEngines = count;
    }

    @Override
    public Engine getEngineType() {
        return this.engine;
    }
}

class Car extends HoverBoard {

    private String brand;

    /**
     * Ааааавтомобиль!
     * @param state физическое состояние
     * @param rent статус аренды
     * @param id идентификатор
     * @param rentPrice стоимость аренды
     * @param defaultWheelCount нормальное количество колёс
     * @param brand Марка автомобиля
     * @param defaultNumberOfEngines нормально количество двигателей
     * @param engine тип двигателя
     */
    public Car(State state, Rent rent, int id, int rentPrice,
               int defaultWheelCount, int defaultNumberOfEngines, Engine engine, String brand) {
        super(state, rent, id, rentPrice, defaultWheelCount, defaultNumberOfEngines, engine);
        this.brand = brand;
    }

    public Car(int id, int rentPrice, String brand) {
        this(State.PERFECT, Rent.FREE, id, rentPrice, 4, 1, Engine.PETROL, brand);
    }

    public String getBrand() {
        return brand;
    }
}

class Boat extends Transport implements Water {

    private int numberOfDecks, defaultNumberOfDecks;
    private boolean drowned;


    /**
     * Простая лодочка
     * @param state физическое состояние
     * @param rent статус аренды
     * @param id идентификатор
     * @param rentPrice стоимость аренды
     * @param defaultNumberOfDecks нормальное количество палуб
     * @param drowned статус "утонула" или "нет"
     */
    public Boat(State state, Rent rent, int id, int rentPrice, int defaultNumberOfDecks, boolean drowned) {
        super(state, rent, id, rentPrice);
        this.defaultNumberOfDecks = defaultNumberOfDecks;
        this.numberOfDecks = defaultNumberOfDecks;
        this.drowned = drowned;
    }

    public Boat(int id, int rentPrice) {
        this(State.PERFECT, Rent.FREE, id, rentPrice, 1, false);
    }

    @Override
    public int getDefaultNumberOfDecks() {
        return this.defaultNumberOfDecks;
    }

    @Override
    public int getNumberOfDecks() {
        return this.numberOfDecks;
    }

    @Override
    public boolean isDrowned() {
        return this.drowned;
    }

    @Override
    public void drowned(boolean state) {
        this.drowned = state;
        if(state) {
            setState(State.SLIGHTLY_DAMAGED);
            setRent(Rent.NOT_AVAILABLE);
        }
    }

    @Override
    public void setNumberOfDecks(int decks) {
        this.numberOfDecks = decks;
        if (this.defaultNumberOfDecks != decks)
            setState(State.BROKEN);
    }
}

class ElectricBoat extends Boat implements Motorized{

    private int defaultNumberOfEngines, numberOfEngines;
    private Engine engine;

    /**
     *
     * Простая моторная лодочка
     * @param state физическое состояние
     * @param rent статус аренды
     * @param id идентификатор
     * @param rentPrice стоимость аренды
     * @param defaultNumberOfDecks нормальное количество палуб
     * @param drowned статус "утонула" или "нет"
     * @param defaultNumberOfEngines нормальное количество моторов
     * @param engine тип мотора
     */
    public ElectricBoat(State state, Rent rent, int id, int rentPrice,
                        int defaultNumberOfDecks, boolean drowned, int defaultNumberOfEngines, Engine engine) {
        super(state, rent, id, rentPrice, defaultNumberOfDecks, drowned);
        this.defaultNumberOfEngines = defaultNumberOfEngines;
        this.numberOfEngines = defaultNumberOfEngines;
        this.engine = engine;
    }

    public ElectricBoat(int id, int rentPrice, int defaultNumberOfEngines, Engine engine) {
        this(State.PERFECT, Rent.FREE, id, rentPrice, 1, false, 1, Engine.DIESEL);
    }

    @Override
    public int getDefaultNumberOfEngines() {
        return this.defaultNumberOfEngines;
    }

    @Override
    public int getNumberOfEngines() {
        return this.numberOfEngines;
    }

    @Override
    public void setNumberOfEngines(int count) {
        this.numberOfEngines = count;
        if(this.defaultNumberOfEngines != count)
            setState(State.BROKEN);
    }

    @Override
    public Engine getEngineType() {
        return this.engine;
    }
}

class Yacht extends ElectricBoat {

    private String owner;
    private int dockID;

    /**
     * Яхта
     * @param state физическое состояние
     * @param rent статус аренды
     * @param id идентификатор
     * @param rentPrice стоимость аренды
     * @param defaultNumberOfDecks нормальное количество палуб
     * @param drowned статус "утонула" или "нет"
     * @param defaultNumberOfEngines нормальное количество моторов
     * @param engine тип мотора
     * @param owner владелец
     * @param dockID идентификатор доков
     */
    public Yacht(State state, Rent rent, int id, int rentPrice, int defaultNumberOfDecks, boolean drowned,
                 int defaultNumberOfEngines, Engine engine, String owner, int dockID) {
        super(state, rent, id, rentPrice, defaultNumberOfDecks, drowned, defaultNumberOfEngines, engine);
        this.owner = owner;
        this.dockID = dockID;
    }

    public Yacht(int id, int rentPrice, String owner, int dockID) {
        this(State.PERFECT, Rent.FREE, id, rentPrice, 2, false, 2, Engine.GAS, owner, dockID);
    }

    public int getDockID() {
        return dockID;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}

class Plane extends Car implements Air {

    int defaultWingCount, wingCount;
    String capitanName, brand;
    State tailState;

    /**
     * Самолёт
     * @param state                  физическое состояние
     * @param rent                   статус аренды
     * @param id                     идентификатор
     * @param rentPrice              стоимость аренды
     * @param defaultWheelCount      нормальное количество колёс
     * @param defaultNumberOfEngines нормально количество двигателей
     * @param engine                 тип двигателя
     * @param brand                  Марка автомобиля
     * @param defaultWingCount Нормально количество крыльев
     * @param capitanName Имя капитана экипажа
     * @param tailState состояние хвоста средства
     */
    public Plane(State state, Rent rent, int id, int rentPrice, int defaultWheelCount,
                 int defaultNumberOfEngines, Engine engine, String brand,
                 int defaultWingCount, String capitanName, State tailState) {
        super(state, rent, id, rentPrice, defaultWheelCount, defaultNumberOfEngines, engine, brand);
        this.defaultWingCount = defaultWingCount;
        this.wingCount = defaultWingCount;
        this.capitanName = capitanName;
        this.tailState = tailState;
    }

    public Plane(int id, int rentPrice, String brand, String capitanName) {
        this(State.PERFECT, Rent.FREE, id, rentPrice, 6, 4,
                Engine.DIESEL, "Boeing", 2 , capitanName, State.PERFECT);
    }

    @Override
    public int getDefaultWingCount() {
        return defaultWingCount;
    }

    @Override
    public State getTailState() {
        return this.tailState;
    }

    @Override
    public int getWingCount() {
        return wingCount;
    }

    public String getCapitanName() {
        return capitanName;
    }

    @Override
    public String getBrand() {
        return brand;
    }

    @Override
    public void setWingCount(int wingCount) {
        this.wingCount = wingCount;
    }

    @Override
    public void setTailState(State state) {
        this.tailState = state;
    }

    public void setCapitanName(String capitanName) {
        this.capitanName = capitanName;
    }
}

class Helicopter extends Plane {

    String takeOffAreaID;

    /**
     * Самолёт
     * @param state                  физическое состояние
     * @param rent                   статус аренды
     * @param id                     идентификатор
     * @param rentPrice              стоимость аренды
     * @param defaultWheelCount      нормальное количество колёс
     * @param defaultNumberOfEngines нормально количество двигателей
     * @param engine                 тип двигателя
     * @param brand                  Марка автомобиля
     * @param defaultWingCount Нормально количество крыльев
     * @param capitanName Имя капитана экипажа
     * @param tailState состояние хвоста средства
     * @param takeOffAreaID идентификатор домашней стоянки
     */
    public Helicopter(State state, Rent rent, int id, int rentPrice, int defaultWheelCount,
                      int defaultNumberOfEngines, Engine engine, String brand,
                      int defaultWingCount, String capitanName, State tailState, String takeOffAreaID) {
        super(state, rent, id, rentPrice, defaultWheelCount, defaultNumberOfEngines, engine, brand, defaultWingCount, capitanName, tailState);
    }

    public Helicopter(int id, int rentPrice, String brand, String capitanName, String takeOffAreaID) {
        this(State.PERFECT, Rent.FREE, id, rentPrice, 6, 4,
                Engine.DIESEL, "Boeing", 2 , capitanName, State.PERFECT, takeOffAreaID);
    }

    public String getTakeOffAreaID() {
        return takeOffAreaID;
    }
}
